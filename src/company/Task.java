package company;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.StringProperty;

public class Task {

    private StringProperty subProjectNumber;
    private StringProperty taskNumber;
    private StringProperty taskName;
    private FloatProperty estimatedDuration;
    private FloatProperty consumedTime;
    private StringProperty assignedWorker;
    private StringProperty taskStatus;

    public Task(StringProperty subProjectNumber, StringProperty taskNumber, StringProperty taskName, FloatProperty estimatedDuration, StringProperty assignedWorker, StringProperty taskStatus) {
        this.subProjectNumber = subProjectNumber;
        this.taskNumber = taskNumber;
        this.taskName = taskName;
        this.estimatedDuration = estimatedDuration;
        this.assignedWorker = assignedWorker;
        this.taskStatus = taskStatus;
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

    public String getTaskNumber() {
        return taskNumber.get();
    }

    public StringProperty taskNumberProperty() {
        return taskNumber;
    }

    public void setTaskNumber(String taskNumber) {
        this.taskNumber.set(taskNumber);
    }

    public String getTaskName() {
        return taskName.get();
    }

    public StringProperty taskNameProperty() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName.set(taskName);
    }

    public float getEstimatedDuration() {
        return estimatedDuration.get();
    }

    public FloatProperty estimatedDurationProperty() {
        return estimatedDuration;
    }

    public void setEstimatedDuration(float estimatedDuration) {
        this.estimatedDuration.set(estimatedDuration);
    }

    public float getConsumedTime() {
        return consumedTime.get();
    }

    public FloatProperty consumedTimeProperty() {
        return consumedTime;
    }

    public void setConsumedTime(float consumedTime) {
        this.consumedTime.set(consumedTime);
    }

    public String getAssignedWorker() {
        return assignedWorker.get();
    }

    public StringProperty assignedWorkerProperty() {
        return assignedWorker;
    }

    public void setAssignedWorker(String assignedWorker) {
        this.assignedWorker.set(assignedWorker);
    }

    public String getTaskStatus() {
        return taskStatus.get();
    }

    public StringProperty taskStatusProperty() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus.set(taskStatus);
    }
}
