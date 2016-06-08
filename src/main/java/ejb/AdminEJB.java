
package ejb;

import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import entities.Admin;

/**
 *
 * A stateless enterprise java bean to handle the business logics related to admin object
 */
@Stateless
public class AdminEJB {

    //persistence context name for database persistence
    @PersistenceContext(unitName = "EASYSERVICES_PU")
    private EntityManager em;

    /**
     * Business logic to find all the persisted admin object and return the list of these admins
     * @return list of admin object 
     */
    public List<Admin> listAdmins() {
        TypedQuery<Admin> query = em.createNamedQuery("findAllAdmin", Admin.class);
        return query.getResultList();
    }

    /**
     *
     * @param admin to be passed to be persisted in the database
     * @return the currently persisted admin object
     */
    public Admin addAdmin(Admin admin){
        em.persist(admin);
        return admin;
    }

    /**
     * Business logic to update an admin object
     * @param admin to be passed to be updated its attributes
     * @return the currently edited admin object
     */
   public int editAdminCommit(Admin admin){
       TypedQuery<Admin> query = em.createNamedQuery("updateAdmin",Admin.class);
       query.setParameter("adEmail", admin.getEmail() );
       query.setParameter("adFirstName", admin.getFirstName() );
       query.setParameter("adLastName", admin.getLastName() );
       query.setParameter("adMiddleName", admin.getMiddleName() );
       query.setParameter("adPassword", admin.getPassword() );
       query.setParameter("adPhoneNo", admin.getPhoneNo() );
       query.setParameter("adQualification", admin.getQualification() );
       query.setParameter("adStatus", admin.getStatus() );
       query.setParameter("adUserType", admin.getUserType() );
       query.setParameter("adCity",admin.getAddress().getCity() );
       query.setParameter("adCountry",admin.getAddress().getCountry());
       query.setParameter("adState",admin.getAddress().getState() );
       query.setParameter("adStreet",admin.getAddress().getStreet() );
       query.setParameter("adZipCode",admin.getAddress().getZipCode() );
       query.setParameter("adId",admin.getId() );              
       return query.executeUpdate();
       
       
   }
    
    /**
     * Business logic to delete an admin object
     * @param id to be passed to find the admin object to be deleted from the database
     */
    public void deleteAdmin(Long id){
       em.remove(getAdmin(id));
    }

    /**
     *
     * @param adminId to be passed to find the admin object in the database
     * @return the currently found admin object
     */
    public Admin getAdmin(Long adminId) {
        Admin admin = em.find(Admin.class, adminId);
        return admin;
    }

    /**
     * Function to find an admin object by id passed as parameter
     * @return the currently found admin object
     */
    public Admin getAdminByParamId() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String id = params.get("adminId");
        Long adminId = Long.parseLong(id);
        return this.getAdmin(adminId);
    }

    /**
     * Business logic to search an admin object
     * @param search to passed to set the search text
     * @param searchBy to be passed to select the search method
     * @return list of admin object which follow the search condition
     */
    public List<Admin> search(String search, String searchBy) {
        TypedQuery<Admin> query = em.createNamedQuery("findAdminBy" + searchBy, Admin.class);
        search = search.toUpperCase();
        query.setParameter(searchBy, "%" + search + "%");
        return query.getResultList();
    }

}
