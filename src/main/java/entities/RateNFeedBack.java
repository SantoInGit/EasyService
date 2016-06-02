/*
*Entity class for rate and feedback
*/
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    //select query to search all rate and feedbacks 
    @NamedQuery(name = "findAllRateNFeedback", query = "select s from RateNFeedBack s")
})
public class RateNFeedBack implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String feedbackNote;
    private String rating;
    private String customerName;
    private Long serviceOrderId;
    
    //getter and setter functions
    //get and set rate and feedback id
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    //get and set the feedback note by customer
    public String getFeedbackNote() {
        return feedbackNote;
    }
    public void setFeedbackNote(String feedbackNote) {
        this.feedbackNote = feedbackNote;
    }

    //get and set the rating value
    public String getRating() {
        return rating;
    }
    public void setRating(String rating) {
        this.rating = rating;
    }
    
    //get and set the customer name who made the feedback
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    //get and set the serviceorder for which the feedback is made
    public Long getServiceOrderId() {
        return serviceOrderId;
    }
    public void setServiceOrderId(Long serviceOrderId) {
        this.serviceOrderId = serviceOrderId;
    }

    //auto generated codes
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RateNFeedBack)) {
            return false;
        }
        RateNFeedBack other = (RateNFeedBack) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "entities.RateNFeedBack[ id=" + id + " ]";
    }    
}
