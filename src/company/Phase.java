package company;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Phase {

    private StringProperty gpn;
    private StringProperty id;
    private StringProperty phaseName;
    private PhaseStatus phaseStage;
    private Status phaseStatus;

    public Phase(String gpn, String id, String phaseName, PhaseStatus phaseStage, Status status) {
        this.gpn = new SimpleStringProperty(gpn);
        this.id = new SimpleStringProperty(id);
        this.phaseName = new SimpleStringProperty(phaseName);
        this.phaseStage = phaseStage;
        this.phaseStatus = status;
    }

    public String getGpn() {
        return gpn.get();
    }

    public StringProperty gpnProperty() {
        return gpn;
    }

    public void setGpn(String gpn) {
        this.gpn.set(gpn);
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

    public String getPhaseName() {
        return phaseName.get();
    }

    public StringProperty phaseNameProperty() {
        return phaseName;
    }

    public void setPhaseName(String phaseName) {
        this.phaseName.set(phaseName);
    }

    public PhaseStatus getPhaseStage() {
        return phaseStage;
    }

    public void setPhaseStage(PhaseStatus phaseStage) {
        this.phaseStage = phaseStage;
    }

    public Status getPhaseStatus() {
        return phaseStatus;
    }

    public void setPhaseStatus(Status phaseStatus) {
        this.phaseStatus = phaseStatus;
    }

    @Override
    public String toString() {
        return getId() + " - " + getPhaseName();
    }
}