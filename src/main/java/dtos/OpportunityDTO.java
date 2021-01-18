package dtos;

import entities.Opportunity;

public class OpportunityDTO {

    private int id;
    private String name;
    private String amount;
    private String closeDate;

    public OpportunityDTO() {
    }

    public OpportunityDTO(int id, String name, String amount, String closeDate) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.closeDate = closeDate;
    }

    public OpportunityDTO(Opportunity opp) {
        this.name = opp.getName();
        this.amount = opp.getAmount();
        this.closeDate = opp.getCloseDate();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
