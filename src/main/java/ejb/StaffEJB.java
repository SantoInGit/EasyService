package ejb;

import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import entities.Staff;

/**
 *
 * A stateless enterprise java bean to handle all the business logic related to staff object
 */
@Stateless
public class StaffEJB {

    //persistence context name for database persistence   
    @PersistenceContext(unitName = "EASYSERVICES_PU")
    private EntityManager em;

    /**
     * Business logic to find all the persisted staff objects and return the list of these objects
     * @return list of staff objects
     */
    public List<Staff> listStaffs() {
        // TODO not implemented with eclipselink 2.0 TypedQuery query = em.createNamedQuery("findAllBooks", Book.class);
        TypedQuery<Staff> query = em.createNamedQuery("findAllStaff", Staff.class);
        return query.getResultList();
    }

    /**
     * Business logic to update an staff object
     * @param staff to be passed to be updated its attributes
     * @return the currently edited staff object
     */
    public int editStaffCommit(Staff staff) {
        TypedQuery<Staff> query = em.createNamedQuery("updateStaff", Staff.class);

        query.setParameter("stEmail", staff.getEmail());
        query.setParameter("stFirstName", staff.getFirstName());
        query.setParameter("stFromDate", staff.getFromDate());
        query.setParameter("stJobTitle", staff.getJobTitle());
        query.setParameter("stLastName", staff.getLastName());
        query.setParameter("stMiddleName", staff.getMiddleName());
        query.setParameter("stPassword", staff.getPassword());
        query.setParameter("stPhoneNo", staff.getPhoneNo());
        query.setParameter("stQualification", staff.getQualification());
        query.setParameter("stStatus", staff.getStatus());
        query.setParameter("stToDate", staff.getToDate());
        query.setParameter("stUserType", staff.getUserType());
        query.setParameter("stCity", staff.getAddress().getCity());
        query.setParameter("stCountry", staff.getAddress().getCountry());
        query.setParameter("stState", staff.getAddress().getState());
        query.setParameter("stStreet", staff.getAddress().getStreet());
        query.setParameter("stZipCode", staff.getAddress().getZipCode());
        query.setParameter("stId", staff.getId());

        return query.executeUpdate();
    }

    /**
     * Business logic to return a list of staff who are not assigned yet in any service order
     * @param fromDate to set the fromDate parameter
     * @param toDate to set the toDate parameter
     * @return list of unassigned staff
     */
    public List<Staff> getUnAssignedStaff(String fromDate, String toDate) {
        TypedQuery<Staff> query = em.createNamedQuery("findAllUnAssignedStaffs", Staff.class);
        query.setParameter("stStatus", "Assigned");
        query.setParameter("orderStartDate", fromDate);
        query.setParameter("orderToDate", toDate);
        return query.getResultList();

    }

    /**
     *
     * @param staff to be passed to be persisted in the database
     * @return the currently persisted staff object
     */
    public Staff addStaff(Staff staff) {
        em.persist(staff);
        return staff;
    }

    /**
     * Business logic to delete an staff object
     * @param id to be passed to find the staff object to be deleted from the database
     */
    public void deleteStaff(Long id) {
        em.remove(getStaff(id));
    }

    /**
     *
     * @param staff_id to be passed to find the staff object in the database
     * @return the currently found staff object
     */
    public Staff getStaff(Long staff_id) {
        Staff staff = em.find(Staff.class, staff_id);
        return staff;
    }

    /**
     * Function to find a staff object by id passed as parameter
     * @return the currently found staff object
     */
    public Staff getStaffByParamId() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String id = params.get("staff_id");
        Long staff_id = Long.parseLong(id);
        return this.getStaff(staff_id);
    }

     /**
     * Business logic to search an staff object
     * @param search to passed to set the search text
     * @param searchBy to be passed to select the search method
     * @return list of staff object which follow the search condition
     */
    public List<Staff> search(String search, String searchBy) {
        TypedQuery<Staff> query = em.createNamedQuery("findStaffBy" + searchBy, Staff.class);
        //changed all upper case as in query field, set field name to upper case
        search = search.toUpperCase();
        query.setParameter(searchBy, "%" + search + "%");
        return query.getResultList();
    }

}
