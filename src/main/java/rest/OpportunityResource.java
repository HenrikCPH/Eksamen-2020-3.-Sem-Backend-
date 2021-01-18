package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.ContactDTO;
import dtos.OpportunityDTO;
import dtos.OpportunityStatusDTO;
import dtos.TaskDTO;
import dtos.TaskStatusDTO;
import dtos.TaskTypeDTO;
import utils.EMF_Creator;
import facades.OpportunityFacade;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import errorhandling.NotFoundException;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("opp")
public class OpportunityResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final OpportunityFacade OPPORTUNITYFACADE = OpportunityFacade.getOpportunityFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"It's working\"}";
    }

    @Path("/opportunities") 
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllOpportunities() {
        return Response.ok().entity(GSON.toJson(OPPORTUNITYFACADE.getOpportunities())).build();
    }
    
    @Path("/contacts") 
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllContacts() {
        return Response.ok().entity(GSON.toJson(OPPORTUNITYFACADE.getContacts())).build();
    }
    
    @Path("/tasks") 
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAllTasks() {
        return Response.ok().entity(GSON.toJson(OPPORTUNITYFACADE.getTasks())).build();
    }
    
    @Path("contact/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getContact(@PathParam("id") int id) throws NotFoundException {
        ContactDTO c = OPPORTUNITYFACADE.getContact((int) id);
        return GSON.toJson(c);
    }
    
        @Path("opportunity/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getOpportunity(@PathParam("id") int id) throws NotFoundException {
        OpportunityDTO o = OPPORTUNITYFACADE.getOpportunity((int) id);
        return GSON.toJson(o);
    }
    
    //Virker ikke - højst sandsynligt grundet at al information skal igennem kun en entitet.
    //Her kunne man have lavet en clientDTO og taget alle informationer fra alle andre dtoer og sat i clientDTOen
    //Det ville måske have løst problemet.
//    @POST 
//    @Produces({MediaType.APPLICATION_JSON})
//    @Consumes({MediaType.APPLICATION_JSON})
//    public String createClient(String nameO, String amount, String closeDate, String nameC, String email, String company,
//            String jobtitle, String phone, String nameOS, String title, String comment, String dueDate, String nameTT, String nameST) throws NotFoundException {
//        
//        OpportunityDTO on = GSON.fromJson(nameO, OpportunityDTO.class);
//        OpportunityDTO oa = GSON.fromJson(amount, OpportunityDTO.class);
//        OpportunityDTO oc = GSON.fromJson(closeDate, OpportunityDTO.class);
//        ContactDTO cn = GSON.fromJson(nameC, ContactDTO.class);
//        ContactDTO e = GSON.fromJson(email, ContactDTO.class);
//        ContactDTO c = GSON.fromJson(company, ContactDTO.class);
//        ContactDTO j = GSON.fromJson(jobtitle, ContactDTO.class);
//        ContactDTO p = GSON.fromJson(phone, ContactDTO.class);
//        OpportunityStatusDTO oS = GSON.fromJson(nameOS, OpportunityStatusDTO.class);
//        TaskDTO t = GSON.fromJson(title, TaskDTO.class);
//        TaskDTO tc = GSON.fromJson(comment, TaskDTO.class);
//        TaskDTO dd = GSON.fromJson(dueDate, TaskDTO.class);
//        TaskTypeDTO nt = GSON.fromJson(nameTT, TaskTypeDTO.class); 
//        TaskStatusDTO ns = GSON.fromJson(nameST, TaskStatusDTO.class);
//        
//        OpportunityDTO cNew = OPPORTUNITYFACADE.addClient(on.getName(), oa.getAmount(), oc.getCloseDate(), cn.getName(), e.getEmail(), c.getCompany(), j.getJobtitle(), 
//                p.getPhone(), oS.getName(), t.getTitle(), tc.getComment(), dd.getDueDate(), nt.getName(), ns.getName());
//        return GSON.toJson(cNew);
//    }
    
    @PUT 
    @Path("edtiopportunity/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String editOpportunities(@PathParam("id") int id, String opp) throws NotFoundException {
        OpportunityDTO oDTO = GSON.fromJson(opp, OpportunityDTO.class);
        oDTO.setId((int) id);
        OpportunityDTO oNew = OPPORTUNITYFACADE.editOpportunity(oDTO);
        return GSON.toJson(oNew);
    }

    @PUT 
    @Path("editcontact/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String editContacts(@PathParam("id") int id, String contact) throws NotFoundException {
        ContactDTO cDTO = GSON.fromJson(contact, ContactDTO.class);
        cDTO.setId((int) id);
        ContactDTO cNew = OPPORTUNITYFACADE.editContact(cDTO);
        return GSON.toJson(cNew);
    }
    
    @PUT 
    @Path("edittask/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String editTasks(@PathParam("id") int id, String task) throws NotFoundException {
        TaskDTO tDTO = GSON.fromJson(task, TaskDTO.class);
        tDTO.setId((int) id);
        TaskDTO tNew = OPPORTUNITYFACADE.editTask(tDTO);
        return GSON.toJson(tNew);
    }
    
    @DELETE
    @Path("deleteopportunity/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String deleteOpportunity(@PathParam("id") int id) throws NotFoundException {
        OpportunityDTO oDeleted = OPPORTUNITYFACADE.deleteOpportunity((int) id);
        return GSON.toJson(oDeleted);
    }
    
    @DELETE
    @Path("deletecontact/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String deleteContact(@PathParam("id") int id) throws NotFoundException {
        ContactDTO cDeleted = OPPORTUNITYFACADE.deleteContact((int) id);
        return GSON.toJson(cDeleted);
    }
    
    @DELETE
    @Path("deletetask/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public String deleteTask(@PathParam("id") int id) throws NotFoundException {
        TaskDTO tDeleted = OPPORTUNITYFACADE.deleteTask((int) id);
        return GSON.toJson(tDeleted);
    }
    

}
