package dtos;

import entities.TaskType;

public class TaskTypeDTO {
    private String name;

    public TaskTypeDTO() {
    }

    public TaskTypeDTO(String name) {
        this.name = name;
    }
    
     public TaskTypeDTO(TaskType tTask) {
        this.name = tTask.getName();      
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
     
}
