package tdsm.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@RequiredArgsConstructor
@Document
public class Project {


    public Project(String name, String description) {
        this.name = name;
        this.description = description;
        this.taskList = new ArrayList<>();
        this.createdAt = new Date();
        this.setUpdatedAtToNow();
    }

    @Id
    private String projectId;

    private String name;
    private String description;

    private List<Task> taskList;

    private Integer numberOfTasks;
    private Integer numberOfCompletedTasks;

    @CreatedDate
    private Date createdAt;

    private Date updatedAt;

    public void addTask(Task task) {
        this.taskList.add(task);
        this.calculateNumberOfTasks();
    }

    public boolean removeTask(Task task) {
        boolean removeStatus = this.taskList.remove(task);
        this.calculateNumberOfTasks();
        return removeStatus;
    }

    public void setUpdatedAtToNow() {
        this.updatedAt = new Date();
    }

    public void calculateNumberOfTasks() {
        this.numberOfTasks = this.taskList.size();
        this.numberOfCompletedTasks = (int) this.taskList.stream().filter(Task::isCompleted).count();
    }

//    private TodoUser ownerUser;

}
