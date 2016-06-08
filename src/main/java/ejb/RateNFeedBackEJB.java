
package ejb;

import entities.RateNFeedBack;
import entities.ServiceOrder;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * A stateless enterprise java bean to handle all the business logic related to rate and feedback object
 */
@Stateless
public class RateNFeedBackEJB {
    @PersistenceContext(unitName = "EASYSERVICES_PU")
    private EntityManager em;
    
    /**
     * Business logic to list all rate and feedback objects
     * @return list of all rate and feedback objects
     */
    public List<RateNFeedBack> listRatesAndFeedbacks() {
        TypedQuery<RateNFeedBack> query = em.createNamedQuery("findAllRateNFeedback", RateNFeedBack.class);
        return query.getResultList();
    }

    /**
     * Business logic to find service order object to create rate and feedback for it
     * @param id to be passed to set id to find service order
     * @return service order object
     */
    public ServiceOrder createRateNFeedback(Long id){
        return em.find(ServiceOrder.class, id);
    }
    
    /**
     * Business login to persist rate and feedback object
     * @param r to be passed to be persisted in the database
     * @return the currently persisted rate and feedback object
     */
    public RateNFeedBack createRateNFeedbackCommit(RateNFeedBack r){
        em.persist(r);
        return r;
    }
    
    /**
     *
     * @param id to be passed to find the rate and feedback object in the database
     * @return the currently found rate and feedback object
     */
    public RateNFeedBack getRatings(Long id) {
        RateNFeedBack rateNFeedBack = em.find(RateNFeedBack.class, id);
        return rateNFeedBack;
    }
    
    /**
     * Business logic to delete an rate and feedback object
     * @param id to be passed to find the rate and feedback object to be deleted from the database
     */
    public void deleteRate(Long id){
       em.remove(getRatings(id));
    }
    
   
}
