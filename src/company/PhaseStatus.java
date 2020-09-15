package company;

public enum PhaseStatus {

    PRE_STUDY("Pre-Study"),
    CONCEPT_STUDY("Concept Study"),
    DETAILED_DEVELOPMENT("Detailed Development"),
    FINAL_VERIFICATION("Final Verification"),
    INDUSTRIALIZATION("Industrialization"),
    FOLLOW_UP("Follow Up");

    public final String phaseStatus;

    PhaseStatus(String phaseStatus) {
        this.phaseStatus = phaseStatus;
    }

    public String getPhaseStatus() {
        return phaseStatus;
    }

    @Override
    public String toString() {
        return phaseStatus;
    }
}