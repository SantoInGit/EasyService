/*
 * Entity class for Service Order
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
    //select queries to search for service orders
    @NamedQuery(name = "findAllServiceOrders", query = "select c from ServiceOrder c"),
    @NamedQuery(name = "findServiceOrderByOrderId", query = "select s from ServiceOrder s where s.serviceOrderId = :OrderId"),
    @NamedQuery(name = "findServiceOrderByStatus", query = "select s from ServiceOrder s where UPPER(s.serviceOrderStatus) LIKE :Status"),
    @NamedQuery(name = "findServiceOrdersByCustomer", query = "select so from ServiceOrder so JOIN so.Customer c where c.id = :CID "),

    //@NamedQuery(name = "findServiceOrdersById", query = "select so from ServiceOrder so JOIN so.serviceOrderItem s WHERE s.orderItemId = :OrderId"),
    @NamedQuery(name = "findServiceOrdersNameId", query = "select s from ServiceOrderItem s WHERE s.orderItemId = :OrderId"),

    @NamedQuery(name = "findServiceOrdersById", query = "select s from ServiceOrder s WHERE s.serviceOrderId = :OrderId"),

    //update query to change the status of the service order
    @NamedQuery(name = "changeServiceOrderStatus",
            query = "update ServiceOrder s Set s.serviceOrderStatus = :status WHERE s.serviceOrderId = :serOrderId"),

    //update query to update rescheduled service orders
    @NamedQuery(name = "updateServiceOrderReschedule", query = "UPDATE ServiceOrder s SET s.fromDate = :serFromDate,"
            + "s.toDate = :serToDate, s.hoursPerDay = :serHrsPerDay, s.staffRequired = :serStaffReqd, "
            + "s.customerNote = :serCustNote WHERE s.serviceOrderId = :serId")
})
public class ServiceOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long serviceOrderId;
    private String customerNote;
    private String hoursPerDay;
    private String serviceOrderDate;
    private String fromDate;
    private String toDate;
    private String staffRequired;
    private String serviceOrderStatus;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "jnd_ServiceOrder_ServiceOrderItem",
            joinColumns = @JoinColumn(name = "ServiceOrder_ServiceOrderItem_fk")
    )
    private List<ServiceOrderItem> serviceOrderItem;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "jnd_ServiceOrder_Customer",
            joinColumns = @JoinColumn(name = "serviceOrder_Customer_fk")
    )
    private Customer Customer;

    //getter and setter functions
    //get and set staff required to carry out the service order
    public String getStaffRequired() {
        return staffRequired;
    }

    public void setStaffRequired(String staffRequired) {
        this.staffRequired = staffRequired;
    }

    //get and set customer who orders the service

    public Customer getCustomer() {
        return Customer;
    }

    public void setCustomer(Customer Customer) {
        this.Customer = Customer;
    }

    //constructor

    public ServiceOrder() {
    }

    //get and set service order starting date
    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    //get and set service order finishing date
    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    //get and set id of the service order
    public Long getServiceOrderId() {
        return serviceOrderId;
    }

    public void setServiceOrderId(Long serviceOrderId) {
        this.serviceOrderId = serviceOrderId;
    }

    //get and set note made by the customer for the service order
    public String getCustomerNote() {
        return customerNote;
    }

    public void setCustomerNote(String customerNote) {
        this.customerNote = customerNote;
    }

    //get and set hour per day to be worked
    public String getHoursPerDay() {
        return hoursPerDay;
    }

    public void setHoursPerDay(String hoursPerDay) {
        this.hoursPerDay = hoursPerDay;
    }

    //get and set date of order made
    public String getServiceOrderDate() {
        return serviceOrderDate;
    }

    public void setServiceOrderDate(String serviceOrderDate) {
        this.serviceOrderDate = serviceOrderDate;
    }

    //get and set the status of the service order
    public String getServiceOrderStatus() {
        return serviceOrderStatus;
    }

    public void setServiceOrderStatus(String serviceOrderStatus) {
        this.serviceOrderStatus = serviceOrderStatus;
    }

    //get and set the order items in the order
    public List<ServiceOrderItem> getServiceOrderItem() {
        return serviceOrderItem;
    }

    public void setServiceOrderItem(List<ServiceOrderItem> serviceOrderItem) {
        this.serviceOrderItem = serviceOrderItem;
    }

    //auto generated codes
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serviceOrderId != null ? serviceOrderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceOrder)) {
            return false;
        }
        ServiceOrder other = (ServiceOrder) object;
        if ((this.serviceOrderId == null && other.serviceOrderId != null) || (this.serviceOrderId != null && !this.serviceOrderId.equals(other.serviceOrderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ServiceOrder[ id=" + serviceOrderId + " ]";
    }

}
