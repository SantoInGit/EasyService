
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

    @PersistenceContext(unitName = "EASYSERVICES_PU")
    private EntityManager em;


    public List<Admin> listAdmins() {
        TypedQuery<Admin> query = em.createNamedQuery("findAllAdmin", Admin.class);
        return query.getResultList();
    }

    public Admin addAdmin(Admin admin, Address address) {
        admin.setAddress(address);
        em.persist(admin);
        return admin;
    }

    public Admin getAdmin(Long adminId) {
        Admin admin = em.find(Admin.class, adminId);
        return admin;
    }

    public Admin getAdminByParamId() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String id = params.get("adminId");
        Long adminId = Long.parseLong(id);
        return this.getAdmin(adminId);
    }

    public List<Admin> search(String search, String searchBy) {
        TypedQuery<Admin> query = em.createNamedQuery("findAdminBy" + searchBy, Admin.class);
        search = search.toUpperCase();
        query.setParameter(searchBy, "%" + search + "%");
        return query.getResultList();
    }

}
