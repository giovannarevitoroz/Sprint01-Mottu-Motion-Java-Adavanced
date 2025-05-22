package br.com.fiap.sprint1.dto;

public class PatioDTO {
        private Long idPatio;
        private String nomePatio;
        private String localizacaoPatio;
        private String descricaoPatio;

        public PatioDTO(Long idPatio, String nomePatio, String localizacaoPatio, String descricaoPatio) {
                this.idPatio = idPatio;
                this.nomePatio = nomePatio;
                this.localizacaoPatio = localizacaoPatio;
                this.descricaoPatio = descricaoPatio;
        }

        // Construtor vazio (caso precise)
        public PatioDTO() {}

        // Getters e Setters
        public Long getIdPatio() {
                return idPatio;
        }

        public void setIdPatio(Long idPatio) {
                this.idPatio = idPatio;
        }

        public String getNomePatio() {
                return nomePatio;
        }

        public void setNomePatio(String nomePatio) {
                this.nomePatio = nomePatio;
        }

        public String getLocalizacaoPatio() {
                return localizacaoPatio;
        }

        public void setLocalizacaoPatio(String localizacaoPatio) {
                this.localizacaoPatio = localizacaoPatio;
        }

        public String getDescricaoPatio() {
                return descricaoPatio;
        }

        public void setDescricaoPatio(String descricaoPatio) {
                this.descricaoPatio = descricaoPatio;
        }
}
