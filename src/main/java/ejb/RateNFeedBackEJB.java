/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entities.RateNFeedBack;
import entities.ServiceOrder;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Santo
 */
@Stateless
public class RateNFeedBackEJB {
    @PersistenceContext(unitName = "EASYSERVICES_PU")
    private EntityManager em;
    
    public ServiceOrder createRateNFeedback(Long id){
        return em.find(ServiceOrder.class, id);
    }
    
    public RateNFeedBack createRateNFeedbackCommit(RateNFeedBack r){
        em.persist(r);
        return r;
    }
    
   
}
