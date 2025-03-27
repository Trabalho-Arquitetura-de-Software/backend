package ucsal.br.api.management_service.utils.type;

public enum UserRole {
    ADMIN("admin"),
    PROFESSOR("professor"),
    STUDENT("student");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
