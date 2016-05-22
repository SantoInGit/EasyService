
package ejb;

import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import entities.Admin;

@Stateless
public class AdminEJB {

    @PersistenceContext(unitName = "EASYSERVICES_PU")
    private EntityManager em;


    public List<Admin> listAdmins() {
        TypedQuery<Admin> query = em.createNamedQuery("findAllAdmin", Admin.class);
        return query.getResultList();
    }

    public Admin addAdmin(Admin admin){
        em.persist(admin);
        return admin;
    }
//    public Admin editAdmin(Long id){
//         return getAdmin(id);
//    }
   public int editAdminCommit(Admin admin){
       TypedQuery<Admin> query = em.createNamedQuery("updateAdmin",Admin.class);
//       query.setParameter(1,admin.getEmail());
//       query.setParameter(2,admin.getFirstName());
//       query.setParameter(3,admin.getId());
//       //query.setParameter(1, getAdmin(id).getId() );
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
       query.setParameter("adId",admin.getId() );;
       
       
        return query.executeUpdate();
       
       
   }
    
    public void deleteAdmin(Long id){
       em.remove(getAdmin(id));
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
