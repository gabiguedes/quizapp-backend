package br.com.quizapp.quizapp.models.dto;

public class UsuarioResponseDto {

    private String statusMessage;
    private Integer statusCode;

    public UsuarioResponseDto() {}

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}
