package ucsal.br.api.management_service.dto;

public class LoginReturnDTO {
    private UserDTO user;
    private String token;

    public LoginReturnDTO() {}
    public LoginReturnDTO(UserDTO user, String token) {
        this.user = user;
        this.token = token;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
