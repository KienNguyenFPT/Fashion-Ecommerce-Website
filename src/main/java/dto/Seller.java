/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Raiku
 */
@Entity
@Table(name = "seller")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Seller.findAll", query = "SELECT s FROM Seller s"),
    @NamedQuery(name = "Seller.findBySellerId", query = "SELECT s FROM Seller s WHERE s.sellerId = :sellerId"),
    @NamedQuery(name = "Seller.findByUsername", query = "SELECT s FROM Seller s WHERE s.username = :username"),
    @NamedQuery(name = "Seller.findByPassword", query = "SELECT s FROM Seller s WHERE s.password = :password"),
    @NamedQuery(name = "Seller.findByCompanyName", query = "SELECT s FROM Seller s WHERE s.companyName = :companyName"),
    @NamedQuery(name = "Seller.findByEmail", query = "SELECT s FROM Seller s WHERE s.email = :email"),
    @NamedQuery(name = "Seller.findByIdAndPw", query = "SELECT s FROM Seller s WHERE s.username = :username AND s.password = :password"),
    @NamedQuery(name = "Seller.findByStatus", query = "SELECT s FROM Seller s WHERE s.status = :status")})
public class Seller implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "seller_id")
    private Integer sellerId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "company_name")
    private String companyName;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email")
    private String email;
    @Column(name = "status")
    private Boolean status;
    @OneToMany(mappedBy = "sellerId")
    private List<Product> productList;

    public Seller() {
    }

    public Seller(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Seller(Integer sellerId, String username, String password, String companyName, String email) {
        this.sellerId = sellerId;
        this.username = username;
        this.password = password;
        this.companyName = companyName;
        this.email = email;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @XmlTransient
    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sellerId != null ? sellerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Seller)) {
            return false;
        }
        Seller other = (Seller) object;
        if ((this.sellerId == null && other.sellerId != null) || (this.sellerId != null && !this.sellerId.equals(other.sellerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dto.Seller[ sellerId=" + sellerId + " ]";
    }
    
}
