package company;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GlobalProjectNumber {

    private StringProperty id;
    private StringProperty globalProjectName;
    private Status status;

    public GlobalProjectNumber(String id, String globalProjectName, Status status) {
        this.id = new SimpleStringProperty(id);
        this.globalProjectName = new SimpleStringProperty(globalProjectName);
        this.status = status;
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getGlobalProjectName() {
        return globalProjectName.get();
    }

    public StringProperty globalProjectNameProperty() {
        return globalProjectName;
    }

    public void setGlobalProjectName(String globalProjectName) {
        this.globalProjectName.set(globalProjectName);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return getId() + " - " + getGlobalProjectName();
    }
}