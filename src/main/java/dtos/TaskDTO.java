package dtos;

import entities.Task;

public class TaskDTO {
    private int id;
    private String title;
    private String comment;
    private String dueDate;

    public TaskDTO() {
    }

    public TaskDTO(int id,String title, String comment, String dueDate) {
        this.id = id;
        this.title = title;
        this.comment = comment;
        this.dueDate = dueDate;
    }
    
    public TaskDTO(Task task) {
        this.title = task.getTitle();
        this.comment = task.getComment();
        this.dueDate = task.getDueDate();
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
