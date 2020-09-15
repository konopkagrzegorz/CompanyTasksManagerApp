package company;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SubProject {

    private StringProperty phaseID;
    private StringProperty subProjectNumber;
    private StringProperty subProjectName;
    private StringProperty projectManager;
    private Status subProjectStatus;

    public SubProject(String phaseID, String subProjectNumber, String subProjectName, String projectManager, Status subProjectStatus) {
        this.phaseID = new SimpleStringProperty(phaseID);
        this.subProjectNumber = new SimpleStringProperty(subProjectNumber);
        this.subProjectName = new SimpleStringProperty(subProjectName);
        this.projectManager = new SimpleStringProperty(projectManager);
        this.subProjectStatus = subProjectStatus;
    }

    public String getPhaseID() {
        return phaseID.get();
    }

    public StringProperty phaseIDProperty() {
        return phaseID;
    }

    public void setPhaseID(String phaseID) {
        this.phaseID.set(phaseID);
    }

    public String getSubProjectNumber() {
        return subProjectNumber.get();
    }

    public StringProperty subProjectNumberProperty() {
        return subProjectNumber;
    }

    public void setSubProjectNumber(String subProjectNumber) {
        this.subProjectNumber.set(subProjectNumber);
    }

    public String getSubProjectName() {
        return subProjectName.get();
    }

    public StringProperty subProjectNameProperty() {
        return subProjectName;
    }

    public void setSubProjectName(String subProjectName) {
        this.subProjectName.set(subProjectName);
    }

    public String getProjectManager() {
        return projectManager.get();
    }

    public StringProperty projectManagerProperty() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager.set(projectManager);
    }

    public Status getSubProjectStatus() {
        return subProjectStatus;
    }

    public void setSubProjectStatus(Status subProjectStatus) {
        this.subProjectStatus = subProjectStatus;
    }
}
