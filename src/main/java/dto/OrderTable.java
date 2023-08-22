/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Raiku
 */
@Entity
@Table(name = "order_table")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderTable.findAll", query = "SELECT o FROM OrderTable o"),
    @NamedQuery(name = "OrderTable.findByOrderId", query = "SELECT o FROM OrderTable o WHERE o.orderId = :orderId"),
    @NamedQuery(name = "OrderTable.findByShipMail", query = "SELECT o FROM OrderTable o WHERE o.shipMail = :shipMail"),
    @NamedQuery(name = "OrderTable.findByOrderDate", query = "SELECT o FROM OrderTable o WHERE o.orderDate = :orderDate"),
    @NamedQuery(name = "OrderTable.findByShipFee", query = "SELECT o FROM OrderTable o WHERE o.shipFee = :shipFee"),
    @NamedQuery(name = "OrderTable.findByTotalAmount", query = "SELECT o FROM OrderTable o WHERE o.totalAmount = :totalAmount"),
    @NamedQuery(name = "OrderTable.findByPaymentMethod", query = "SELECT o FROM OrderTable o WHERE o.paymentMethod = :paymentMethod"),
    @NamedQuery(name = "OrderTable.loadOrder", query = "SELECT o FROM OrderTable o ORDER BY o.orderId DESC"),
    @NamedQuery(name = "OrderTable.loadOrderByStatus", query = "SELECT o FROM OrderTable o WHERE o.status = :status ORDER BY o.orderId DESC"),
    @NamedQuery(name = "OrderTable.loadOrderByPaymentMethod", query = "SELECT o FROM OrderTable o WHERE o.paymentMethod = :paymentMethod ORDER BY o.orderId DESC"),
    @NamedQuery(name = "OrderTable.loadOrderByMethodAndStatus", query = "SELECT o FROM OrderTable o WHERE o.paymentMethod = :paymentMethod AND o.status = :status ORDER BY o.orderId DESC"),
    @NamedQuery(name = "OrderTable.findByStatus", query = "SELECT o FROM OrderTable o WHERE o.status = :status"),
    @NamedQuery(name = "OrderTable.countAll", query = "SELECT COUNT(o) FROM OrderTable o"),
    @NamedQuery(name = "OrderTable.countByStatus", query = "SELECT COUNT(o) FROM OrderTable o WHERE o.status = :status"),
    @NamedQuery(name = "OrderTable.countByMethod", query = "SELECT COUNT(o) FROM OrderTable o WHERE o.paymentMethod = :paymentMethod"),
    @NamedQuery(name = "OrderTable.countByStatusAndMethod", query = "SELECT COUNT(o) FROM OrderTable o WHERE o.status = :status AND o.paymentMethod = :paymentMethod")})
public class OrderTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "order_id")
    private Integer orderId;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "ship_name")
    private String shipName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ship_mail")
    private String shipMail;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "ship_phone")
    private String shipPhone;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "ship_address")
    private String shipAddress;
    @Column(name = "order_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    @Column(name = "ship_fee")
    private Integer shipFee;
    @Lob
    @Size(max = 65535)
    @Column(name = "discount_code")
    private String discountCode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_amount")
    private double totalAmount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "payment_method")
    private String paymentMethod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "status")
    private String status;
    @OneToMany(mappedBy = "orderId")
    private List<OrderItem> orderItemList;
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    @ManyToOne
    private Customer customerId;

    public OrderTable() {
    }

    public OrderTable(Integer orderId) {
        this.orderId = orderId;
    }

    public OrderTable(Integer orderId, String shipName, String shipMail, String shipPhone, String shipAddress, double totalAmount, String paymentMethod, String status) {
        this.orderId = orderId;
        this.shipName = shipName;
        this.shipMail = shipMail;
        this.shipPhone = shipPhone;
        this.shipAddress = shipAddress;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getShipMail() {
        return shipMail;
    }

    public void setShipMail(String shipMail) {
        this.shipMail = shipMail;
    }

    public String getShipPhone() {
        return shipPhone;
    }

    public void setShipPhone(String shipPhone) {
        this.shipPhone = shipPhone;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getShipFee() {
        return shipFee;
    }

    public void setShipFee(Integer shipFee) {
        this.shipFee = shipFee;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderId != null ? orderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderTable)) {
            return false;
        }
        OrderTable other = (OrderTable) object;
        if ((this.orderId == null && other.orderId != null) || (this.orderId != null && !this.orderId.equals(other.orderId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.OrderTable[ orderId=" + orderId + " ]";
    }
    
}
