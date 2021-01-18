package facades;

import dtos.ContactDTO;
import dtos.OpportunityDTO;
import dtos.TaskDTO;
import entities.Opportunity;
import entities.Contact;
import entities.OpportunityStatus;
import entities.Task;
import entities.TaskStatus;
import entities.TaskType;
import errorhandling.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import utils.EMF_Creator;

public class OpportunityFacade {

    private static OpportunityFacade instance;
    private static EntityManagerFactory emf;

    private OpportunityFacade() {
    }

    public static OpportunityFacade getOpportunityFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new OpportunityFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<OpportunityDTO> getOpportunities() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Opportunity> query = em.createQuery("SELECT o FROM Opportunity o", Opportunity.class);
        List<Opportunity> opps = query.getResultList();
        List<OpportunityDTO> oppsDTO = new ArrayList();
        opps.forEach((Opportunity opportunity) -> {
            oppsDTO.add(new OpportunityDTO(opportunity));
        });
        return oppsDTO;
    }

    public List<ContactDTO> getContacts() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Contact> query = em.createQuery("SELECT c FROM Contact c", Contact.class);
        List<Contact> contacts = query.getResultList();
        List<ContactDTO> ContactDTOList = new ArrayList<>();
        contacts.forEach((Contact contact) -> ContactDTOList.add(new ContactDTO(contact)));
        return ContactDTOList;
    }

    public List<TaskDTO> getTasks() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Task> query = em.createQuery("SELECT t FROM Task t", Task.class);
        List<Task> tasks = query.getResultList();
        List<TaskDTO> TaskDTOList = new ArrayList<>();
        tasks.forEach((Task task) -> TaskDTOList.add(new TaskDTO(task)));
        return TaskDTOList;
    }

    public ContactDTO getContact(int id) throws NotFoundException {
        EntityManager em = getEntityManager();
        try {
            Contact contact = em.find(Contact.class, id);
            if (contact == null) {
                throw new NotFoundException(String.format("Contact with id: (%d) not found", id));
            } else {
                return new ContactDTO(contact);
            }
        } finally {
            em.close();
        }
    }
    
    public OpportunityDTO getOpportunity(int id) throws NotFoundException {
        EntityManager em = getEntityManager();
        try {
            Opportunity opportunity = em.find(Opportunity.class, id);
            if (opportunity == null) {
                throw new NotFoundException(String.format("Opportunity with id: (%d) not found", id));
            } else {
                return new OpportunityDTO(opportunity);
            }
        } finally {
            em.close();
        }
    }

    public OpportunityDTO addClient(String nameO, String amount, String closeDate, String nameC, String email, String company,
            String jobtitle, String phone, String nameOS, String title, String comment, String dueDate, String nameTT, String nameST) throws NotFoundException {
        EntityManager em = getEntityManager();

        Opportunity op1 = new Opportunity(nameO, amount, closeDate);
        Contact c1 = new Contact(nameC, email, company, jobtitle, phone);
        OpportunityStatus oS1 = new OpportunityStatus(nameOS);
        Task t1 = new Task(title, comment, dueDate);
        TaskType tt1 = new TaskType(nameTT);
        TaskStatus ts1 = new TaskStatus(nameST);

        t1.addTaskStatus(ts1);
        t1.addTaskType(tt1);
        op1.addContact(c1);
        op1.addTask(t1);
        op1.addOpportunityStatus(oS1);
        t1.setOpportunity(op1);
        oS1.setOpportunity(op1);
        tt1.setTask(t1);
        c1.setOpportunity(op1);
        ts1.setTask(t1);

        try {
            em.getTransaction().begin();
            em.persist(op1);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new OpportunityDTO(op1);
    }

    public OpportunityDTO editOpportunity(OpportunityDTO o) throws NotFoundException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Opportunity opp = em.find(Opportunity.class, o.getId());
            if (opp == null) {
                throw new NotFoundException(String.format("Opportunity with that id: (%d) not found", o.getId()));
            } else {
                opp.setName(o.getName());
                opp.setAmount(o.getAmount());
                opp.setCloseDate(o.getCloseDate());
            }
            em.getTransaction().commit();
            return new OpportunityDTO(opp);
        } finally {
            em.close();
        }
    }

    public ContactDTO editContact(ContactDTO c) throws NotFoundException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Contact contact = em.find(Contact.class, c.getId());
            if (contact == null) {
                throw new NotFoundException(String.format("Contact with that id: (%d) not found", contact.getId()));
            } else {
                contact.setName(c.getName());
                contact.setEmail(c.getEmail());
                contact.setCompany(c.getCompany());
                contact.setJobtitle(c.getJobtitle());
                contact.setPhone(c.getPhone());
            }
            em.getTransaction().commit();
            return new ContactDTO(contact);
        } finally {
            em.close();
        }
    }

    public TaskDTO editTask(TaskDTO t) throws NotFoundException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Task task = em.find(Task.class, t.getId());
            if (task == null) {
                throw new NotFoundException(String.format("Task with id: (%d) not found", task.getId()));
            } else {
                task.setTitle(t.getTitle());
                task.setComment(t.getComment());
                task.setDueDate(t.getDueDate());
            }
            em.getTransaction().commit();
            return new TaskDTO(task);
        } finally {
            em.close();
        }
    }

    public OpportunityDTO deleteOpportunity(int id) throws NotFoundException {
        EntityManager em = getEntityManager();
        Opportunity opp = em.find(Opportunity.class, id);
        if (opp == null) {
            throw new NotFoundException(String.format("Opportunity with id: (%d) not found", id));
        } else {
            try {
                em.getTransaction().begin();
                em.remove(opp);
                em.getTransaction().commit();
            } finally {
                em.close();
            }
            return new OpportunityDTO(opp);
        }
    }

    public ContactDTO deleteContact(int id) throws NotFoundException {
        EntityManager em = getEntityManager();
        Contact contact = em.find(Contact.class, id);
        if (contact == null) {
            throw new NotFoundException(String.format("Contact with id: (%d) not found", id));
        } else {
            try {
                em.getTransaction().begin();
                em.remove(contact);
                em.getTransaction().commit();
            } finally {
                em.close();
            }
            return new ContactDTO(contact);
        }
    }

    public TaskDTO deleteTask(int id) throws NotFoundException {
        EntityManager em = getEntityManager();
        Task task = em.find(Task.class, id);
        if (task == null) {
            throw new NotFoundException(String.format("Task with id: (%d) not found", id));
        } else {
            try {
                em.getTransaction().begin();
                em.remove(task);
                em.getTransaction().commit();
            } finally {
                em.close();
            }
            return new TaskDTO(task);
        }
    }

//    public static void main(String[] args) {
//
//        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
//        EntityManager em = emf.createEntityManager();
//        
//        Opportunity o1 = new Opportunity("Hula Bula", "500000", "On going");
//        Task t1 = new Task("Call", "We want all the happiness in the world", "20-01-2022");
//        Contact c1 = new Contact("King Kong", "Happy@KomGlad.com", "Happiness 2020", "Have fun", "12345678");
//        OpportunityStatus oS1 = new OpportunityStatus("Active"); // won - lost - active
//        TaskType tt1 = new TaskType("Call");
//        TaskStatus ts1 = new TaskStatus("Non started");
//        
//        t1.addTaskStatus(ts1);
//        t1.addTaskType(tt1);
//        o1.addContact(c1);
//        o1.addTask(t1);
//        o1.addOpportunityStatus(oS1);
//        t1.setOpportunity(o1);
//        oS1.setOpportunity(o1);
//        tt1.setTask(t1);
//        c1.setOpportunity(o1);
//        ts1.setTask(t1);
//        
//        try {
//            em.getTransaction().begin();
//            em.persist(t1);
//            em.persist(c1);
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
//
//    }
}
