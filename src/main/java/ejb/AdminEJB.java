/**
 * @author jagatrp<Jagat Ram Prajapati>
 * @email prajapatijagat2009@gmail.com
 */
package ejb;

import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import entities.Address;
import entities.Admin;

@Stateless
public class AdminEJB {

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
        TypedQuery<Staff> query = em.createNamedQuery("findAllAdmin", Staff.class);
        return query.getResultList();
    }

    /**
     *
     * @param customer
     * @param address
     * @return customer
     */
    public Staff addAdmin(Admin admin, Address address) {

        admin.setAddress(address);
        em.persist(admin);
        return admin;
    }

    /**
     *
     * @param customer_id
     * @return customer
     */
    public Staff getAdmin(Long adminId) {
        Staff staff = em.find(Admin.class, adminId);
        return staff;
    }

    /**
     *
     * @return customer
     */
    public Staff getStaffByParamId() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String id = params.get("staff_id");
        Long staff_id = Long.parseLong(id);
        return this.getStaff(staff_id);
    }

    /**
     * method to search customer by using different attribute
     *
     * @param search
     * @param searchBy
     * @return List Customer
     */
    public List<Staff> search(String search, String searchBy) {
        TypedQuery<Staff> query = em.createNamedQuery("findStaffBy" + searchBy, Staff.class);
        //changed all upper case as in query field, set field name to upper case
        search = search.toUpperCase();
        query.setParameter(searchBy, "%" + search + "%");
        return query.getResultList();
    }

}
