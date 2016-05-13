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
import entities.ServiceCategory;

@Stateless
public class ServiceCategoryEJB {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "EASYSERVICES_PU")
    private EntityManager em;

    // ======================================
    // =           Public Methods           =
    // ======================================
    public List<ServiceCategory> listServiceCategory() {
        // TODO not implemented with eclipselink 2.0 TypedQuery query = em.createNamedQuery("findAllBooks", Book.class);
        TypedQuery<ServiceCategory> query = em.createNamedQuery("findAllServiceCategory", ServiceCategory.class);
        return query.getResultList();
    }

    /**
     *
     * @param customer
     * @param address
     * @return customer
     */
    public ServiceCategory addServiceCategory(ServiceCategory serCat) {
        em.persist(serCat);
        return serCat;
    }

    /**
     *
     * @param serCat_id
     * @return customer
     */
    public ServiceCategory getServiceCategory(Long serCat_id) {
        ServiceCategory serCat = em.find(ServiceCategory.class, serCat_id);
        return serCat;
    }

    /**
     *
     * @return customer
     */
    public ServiceCategory getServiceCategoryByParamId() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String id = params.get("serCat_id");
        Long serCat_id = Long.parseLong(id);
        return this.getServiceCategory(serCat_id);
    }

    /**
     * method to search customer by using different attribute
     *
     * @param search
     * @param searchBy
     * @return List Customer
     */
    public List<ServiceCategory> search(String search, String searchBy) {
        TypedQuery<ServiceCategory> query = em.createNamedQuery("findServiceCategoryBy" + searchBy, ServiceCategory.class);
        //changed all upper case as in query field, set field name to upper case
        search = search.toUpperCase();
        query.setParameter(searchBy, "%" + search + "%");
        return query.getResultList();
    }

}
