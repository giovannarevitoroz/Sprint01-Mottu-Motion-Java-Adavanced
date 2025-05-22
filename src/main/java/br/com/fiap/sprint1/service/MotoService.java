package br.com.fiap.sprint1.service;

import br.com.fiap.sprint1.dto.MotoDTO;
import br.com.fiap.sprint1.exception.RecursoNaoEncontradoException;
import br.com.fiap.sprint1.model.Moto;
import br.com.fiap.sprint1.repository.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MotoService {

    @Autowired
    private MotoRepository motoRepository;

    @CachePut(value = "motos", key = "#result.idMoto")
    public MotoDTO salvar(MotoDTO motoDTO) {
        Moto moto = toEntity(motoDTO);
        return toDTO(motoRepository.save(moto));
    }

    @Cacheable(value = "motosAll")
    public List<MotoDTO> listarTodos() {
        return motoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Page<MotoDTO> listarPorModelo(String modelo, Pageable pageable) {
        return motoRepository.findByModeloContainingIgnoreCase(modelo, pageable)
                .map(this::toDTO);
    }

    @Cacheable(value = "motoPorId", key = "#id")
    public MotoDTO buscarPorId(Long id) {
        Moto moto = motoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Moto com ID " + id + " não encontrada."));
        return toDTO(moto);
    }

    @Cacheable(value = "motoPorPlaca", key = "#placa")
    public MotoDTO buscarPorPlaca(String placa) {
        Moto moto = motoRepository.findByPlacaIgnoreCase(placa)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Moto com placa " + placa + " não encontrada."));
        return toDTO(moto);
    }

    @Cacheable(value = "motoPorChassi", key = "#chassi")
    public MotoDTO buscarPorChassi(String chassi) {
        Moto moto = motoRepository.findByChassiMotoIgnoreCase(chassi)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Moto com chassi " + chassi + " não encontrada."));
        return toDTO(moto);
    }

    @CachePut(value = "motoPorId", key = "#id")
    public MotoDTO atualizar(Long id, MotoDTO motoDTO) {
        Moto motoExistente = motoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Moto com ID " + id + " não encontrada."));

        motoExistente.setModeloMoto(motoDTO.getModeloMoto());
        motoExistente.setPlacaMoto(motoDTO.getPlacaMoto());
        motoExistente.setSituacaoMoto(motoDTO.getSituacaoMoto());
        motoExistente.setChassiMoto(motoDTO.getChassiMoto());
        motoExistente.setCliente(motoDTO.getCliente());

        return toDTO(motoRepository.save(motoExistente));
    }

    @CacheEvict(value = { "motoPorId", "motoPorPlaca", "motoPorChassi", "motos", "motosAll" }, key = "#id")
    public void deletar(Long id) {
        if (!motoRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Moto com ID " + id + " não encontrada.");
        }
        motoRepository.deleteById(id);
    }

    private Moto toEntity(MotoDTO dto) {
        Moto moto = new Moto();
        moto.setIdMoto(dto.getIdMoto());
        moto.setModeloMoto(dto.getModeloMoto());
        moto.setPlacaMoto(dto.getPlacaMoto());
        moto.setSituacaoMoto(dto.getSituacaoMoto());
        moto.setChassiMoto(dto.getChassiMoto());
        moto.setCliente(dto.getCliente());
        return moto;
    }

    private MotoDTO toDTO(Moto moto) {
        MotoDTO dto = new MotoDTO();
        dto.setIdMoto(moto.getIdMoto());
        dto.setModeloMoto(moto.getModeloMoto());
        dto.setPlacaMoto(moto.getPlacaMoto());
        dto.setSituacaoMoto(moto.getSituacaoMoto());
        dto.setChassiMoto(moto.getChassiMoto());
        dto.setCliente(moto.getCliente());
        return dto;
    }
}
