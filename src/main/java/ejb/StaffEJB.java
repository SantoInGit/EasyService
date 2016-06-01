
package ejb;


import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import entities.Staff;

@Stateless
public class StaffEJB {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "EASYSERVICES_PU")
    private EntityManager em;

    // ======================================
    // =           Public Methods           =
    // ======================================
    public List<Staff> listStaffs() {
        // TODO not implemented with eclipselink 2.0 TypedQuery query = em.createNamedQuery("findAllBooks", Book.class);
        TypedQuery<Staff> query = em.createNamedQuery("findAllStaff", Staff.class);
        return query.getResultList();
    }

   public int editStaffCommit(Staff staff){
       TypedQuery<Staff> query = em.createNamedQuery("updateStaff",Staff.class);

       query.setParameter("stEmail", staff.getEmail() );
       query.setParameter("stFirstName", staff.getFirstName() );
       query.setParameter("stFromDate", staff.getFromDate());
       query.setParameter("stJobTitle", staff.getJobTitle());
       query.setParameter("stLastName", staff.getLastName() );
       query.setParameter("stMiddleName", staff.getMiddleName() );
       query.setParameter("stPassword", staff.getPassword() );
       query.setParameter("stPhoneNo", staff.getPhoneNo() );
       query.setParameter("stQualification", staff.getQualification() );
       query.setParameter("stStatus", staff.getStatus() );
       query.setParameter("stToDate", staff.getToDate());
       query.setParameter("stUserType", staff.getUserType() );
       query.setParameter("stCity",staff.getAddress().getCity() );
       query.setParameter("stCountry",staff.getAddress().getCountry());
       query.setParameter("stState",staff.getAddress().getState() );
       query.setParameter("stStreet",staff.getAddress().getStreet() );
       query.setParameter("stZipCode",staff.getAddress().getZipCode() );
       query.setParameter("stId",staff.getId() );
              
       return query.executeUpdate();          
   }
   
   public List<Staff> getUnAssignedStaff(){
       TypedQuery<Staff> query = em.createNamedQuery("findAllUnAssignedStaffs", Staff.class);
       query.setParameter("stStatus","Not Assigned");
       return query.getResultList();
       
   }
   
    public Staff addStaff(Staff staff) {
        em.persist(staff);
        return staff;
    }
    
    public void deleteStaff(Long id){
       em.remove(getStaff(id));
    }

    public Staff getStaff(Long staff_id) {
        Staff staff = em.find(Staff.class, staff_id);
        return staff;
    }

    public Staff getStaffByParamId() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String id = params.get("staff_id");
        Long staff_id = Long.parseLong(id);
        return this.getStaff(staff_id);
    }

    public List<Staff> search(String search, String searchBy) {
        TypedQuery<Staff> query = em.createNamedQuery("findStaffBy" + searchBy, Staff.class);
        //changed all upper case as in query field, set field name to upper case
        search = search.toUpperCase();
        query.setParameter(searchBy, "%" + search + "%");
        return query.getResultList();
    }

}
