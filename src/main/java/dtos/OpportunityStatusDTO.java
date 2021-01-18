package dtos;

import entities.OpportunityStatus;

public class OpportunityStatusDTO {

    private String name;

    public OpportunityStatusDTO() {
    }

    public OpportunityStatusDTO(String name) {
        this.name = name;
    }

    public OpportunityStatusDTO(OpportunityStatus oppS) {
        this.name = oppS.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
