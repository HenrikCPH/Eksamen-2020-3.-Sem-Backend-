package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Opportunity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String amount;
    private String closeDate;
    
    @OneToMany(mappedBy = "opportunity", cascade = CascadeType.PERSIST)
    private List<Contact> contacts;
    
    @OneToMany(mappedBy = "opportunity", cascade = CascadeType.PERSIST)
    private List<OpportunityStatus> OpStatus;
    
    @OneToMany(mappedBy = "opportunity", cascade = CascadeType.PERSIST)
    private List<Task> tasks;

    public Opportunity() {
    }

    public Opportunity(String name, String amount, String closeDate) {
        this.name = name;
        this.amount = amount;
        this.closeDate = closeDate;
        contacts = new ArrayList<>();
        OpStatus = new ArrayList<>();
        tasks = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        if (contact != null) {
            contacts.add(contact);
        }
    }
    
    public void addOpportunityStatus(OpportunityStatus OpS) {
        if (OpS != null) {
            OpStatus.add(OpS);
        }
    }

    public void addTask(Task task) {
        if (task != null) {
            tasks.add(task);
        }
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
