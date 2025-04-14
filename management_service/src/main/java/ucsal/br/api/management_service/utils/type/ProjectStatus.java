package ucsal.br.api.management_service.utils.type;

public enum ProjectStatus {
    PENDING_ANALYSIS("pending_analysis"),
    UNDER_ANALYSIS("under_analysis"),
    REJECTED("rejected"),
    IN_PROGRESS("in_progress"),
    FINISHED("finished");

    private String value;

    ProjectStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
