
package ejb;

import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import entities.ServiceCategory;

/**
 *
 * A stateless enterprise java bean to handle all the business logic related to service category object
 */
@Stateless
public class ServiceCategoryEJB {
    //persistence context name for database persistence   

    @PersistenceContext(unitName = "EASYSERVICES_PU")
    private EntityManager em;

     /**
     * Business logic to list all service categories
     * @return list of all services categories
     */
    public List<ServiceCategory> listServiceCategory() {
        // TODO not implemented with eclipselink 2.0 TypedQuery query = em.createNamedQuery("findAllBooks", Book.class);
        TypedQuery<ServiceCategory> query = em.createNamedQuery("findAllServiceCategory", ServiceCategory.class);
        return query.getResultList();
    }

    /**
     * Business logic to list all active service categories at the front end
     * @return list of all active service categories
     */
    public List<ServiceCategory> listFrontendServiceCategory() {
        TypedQuery<ServiceCategory> query = em.createNamedQuery("findAllFrontendServiceCategory", ServiceCategory.class);
        String active = "Active";
        active = active.toUpperCase();
        query.setParameter("status", active);
        return query.getResultList();
    }

    /**
     * Business login to persist service category object
     * @param serCat to be passed to be persisted in the database
     * @return the currently persisted service category object
     */
    public ServiceCategory addServiceCategory(ServiceCategory serCat) {
        em.persist(serCat);
        return serCat;
    }
    
    /**
     * Business logic to delete an service category object
     * @param id to be passed to find the service  category object to be deleted from the database
     */
    public void deleteServiceCategory(Long id){
       em.remove(getServiceCategory(id));
    }
    
    /**
     * Business logic to update an service category object
     * @param serviceCategory to be passed to be updated its attributes
     * @return the currently edited service category object
     */
    public int editServiceCategoryCommit(ServiceCategory serviceCategory){
       TypedQuery<ServiceCategory> query = em.createNamedQuery("updateServiceCategory",ServiceCategory.class);

       query.setParameter("serCatLongDescription", serviceCategory.getLongDescription() );
       query.setParameter("serCatServiceCategoryName", serviceCategory.getServiceCategoryName() );
       query.setParameter("serCatShortDescription", serviceCategory.getShortDescription() );
       query.setParameter("serCatStatus", serviceCategory.getStatus() );
       query.setParameter("serCatId", serviceCategory.getServiceCategoryId());
              
       return query.executeUpdate();
             
   }

    /**
     *
     * @param serCat_id to be passed to find the service category object in the database
     * @return the currently found service category object
     */
    public ServiceCategory getServiceCategory(Long serCat_id) {
        ServiceCategory serCat = em.find(ServiceCategory.class, serCat_id);
        return serCat;
    }

    /**
     * Function to find a service category object by id passed as parameter
     * @return the currently found service category object
     */
    public ServiceCategory getServiceCategoryByParamId() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        String id = params.get("serCat_id");
        Long serCat_id = Long.parseLong(id);
        return this.getServiceCategory(serCat_id);
    }

     /**
     * Business logic to search an service category object
     * @param search to passed to set the search text
     * @param searchBy to be passed to select the search method
     * @return list of service category object which follow the search condition
     */
    public List<ServiceCategory> search(String search, String searchBy) {
        TypedQuery<ServiceCategory> query = em.createNamedQuery("findServiceCategoryBy" + searchBy, ServiceCategory.class);
        //changed all upper case as in query field, set field name to upper case
        search = search.toUpperCase();
        query.setParameter(searchBy, "%" + search + "%");
        return query.getResultList();
    }

}
