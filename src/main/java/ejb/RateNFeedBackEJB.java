
package ejb;

import entities.RateNFeedBack;
import entities.ServiceOrder;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


@Stateless
public class RateNFeedBackEJB {
    @PersistenceContext(unitName = "EASYSERVICES_PU")
    private EntityManager em;
    
    
    public List<RateNFeedBack> listRatesAndFeedbacks() {
        TypedQuery<RateNFeedBack> query = em.createNamedQuery("findAllRateNFeedback", RateNFeedBack.class);
        return query.getResultList();
    }
    public ServiceOrder createRateNFeedback(Long id){
        return em.find(ServiceOrder.class, id);
    }
    
    public RateNFeedBack createRateNFeedbackCommit(RateNFeedBack r){
        em.persist(r);
        return r;
    }
    
    public RateNFeedBack getRatings(Long id) {
        RateNFeedBack rateNFeedBack = em.find(RateNFeedBack.class, id);
        return rateNFeedBack;
    }
    
  
    
    public void deleteRate(Long id){
       em.remove(getRatings(id));
    }
    
   
}
