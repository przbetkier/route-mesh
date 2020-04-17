package dev.przbetkier.routemesh.security.auth;

class SignupMessageResponse {
    private String message;

    public SignupMessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
