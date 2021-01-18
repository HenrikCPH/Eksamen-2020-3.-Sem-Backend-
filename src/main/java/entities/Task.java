package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String comment;
    private String dueDate;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Opportunity opportunity;

    @OneToMany(mappedBy = "tasks", cascade = CascadeType.PERSIST)
    private List<TaskStatus> TStatus;
    
    @OneToMany(mappedBy = "tasks", cascade = CascadeType.PERSIST)
    private List<TaskType> tType;
    
    public Task() {
    }

    public Task(String title, String comment, String dueDate) {
        this.title = title;
        this.comment = comment;
        this.dueDate = dueDate;
        TStatus = new ArrayList<>();
        tType = new ArrayList<>();
    }

    public void addTaskStatus(TaskStatus taskStatus) {
        if (taskStatus != null) {
            TStatus.add(taskStatus);
        }
    }
    
    public void addTaskType(TaskType taskType) {
        if (taskType != null) {
            tType.add(taskType);
        }
    }
    
    public Opportunity getOpportunity() {
        return opportunity;
    }

    public void setOpportunity(Opportunity opport) {
        if (opport != null) {
            this.opportunity = opport;
            opport.addTask(this);
        } else {
            this.opportunity = null;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
