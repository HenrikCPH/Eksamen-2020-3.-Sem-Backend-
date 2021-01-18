package dtos;

import entities.TaskStatus;

public class TaskStatusDTO {
    private String name;

    public TaskStatusDTO() {
    }

    public TaskStatusDTO(String name) {
        this.name = name;
    }
    
    public TaskStatusDTO(TaskStatus Tstatus) {
        this.name = Tstatus.getName();     
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
