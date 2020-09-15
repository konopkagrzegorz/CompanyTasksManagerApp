package company;

public enum Status {

    Opened("Opened"),
    Closed("Closed");

    public final String description;

    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
