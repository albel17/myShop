package myApp.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "orders", schema = "", catalog = "mydb")
public class OrdersEntity {
    private int id;
    private String paymentMethod;
    private String deliveryMethod;
    private String orderStatus;
    private String creationDate;
    private String deliveryDate;
    private int cost;
    private Collection<OrderItemEntity> orderItemsById;
    private PersonsEntity personsByClientId;
    private AddressesEntity addressesByAddressId;

    public OrdersEntity(String paymentMethod, String deliveryMethod, String orderStatus, String creationDate, String deliveryDate, int cost, PersonsEntity person, AddressesEntity address) {
        this.paymentMethod = paymentMethod;
        this.deliveryMethod = deliveryMethod;
        this.orderStatus = orderStatus;
        this.creationDate = creationDate;
        this.deliveryDate = deliveryDate;
        this.cost = cost;
        this.personsByClientId = person;
        this.addressesByAddressId = address;
    }

    public OrdersEntity() {
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "PAYMENT_METHOD")
    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Basic
    @Column(name = "DELIVERY_METHOD")
    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    @Basic
    @Column(name = "ORDER_STATUS")
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Basic
    @Column(name = "CREATION_DATE")
    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    @Basic
    @Column(name = "DELIVERY_DATE")
    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @Basic
    @Column(name = "COST")
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrdersEntity that = (OrdersEntity) o;

        if (id != that.id) return false;
        if (cost != that.cost) return false;
        if (paymentMethod != null ? !paymentMethod.equals(that.paymentMethod) : that.paymentMethod != null)
            return false;
        if (deliveryMethod != null ? !deliveryMethod.equals(that.deliveryMethod) : that.deliveryMethod != null)
            return false;
        if (orderStatus != null ? !orderStatus.equals(that.orderStatus) : that.orderStatus != null) return false;
        if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null) return false;
        return !(deliveryDate != null ? !deliveryDate.equals(that.deliveryDate) : that.deliveryDate != null);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (paymentMethod != null ? paymentMethod.hashCode() : 0);
        result = 31 * result + (deliveryMethod != null ? deliveryMethod.hashCode() : 0);
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
        result = 31 * result + (deliveryDate != null ? deliveryDate.hashCode() : 0);
        result = 31 * result + cost;
        return result;
    }

    @OneToMany(mappedBy = "ordersByOrderId", fetch = FetchType.EAGER)
    public Collection<OrderItemEntity> getOrderItemsById() {
        return orderItemsById;
    }

    public void setOrderItemsById(Collection<OrderItemEntity> orderItemsById) {
        this.orderItemsById = orderItemsById;
    }

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "ID", nullable = false)
    public PersonsEntity getPersonsByClientId() {
        return personsByClientId;
    }

    public void setPersonsByClientId(PersonsEntity personsByClientId) {
        this.personsByClientId = personsByClientId;
    }

    @ManyToOne
    @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID")
    public AddressesEntity getAddressesByAddressId() {
        return addressesByAddressId;
    }

    public void setAddressesByAddressId(AddressesEntity addressesByAddressId) {
        this.addressesByAddressId = addressesByAddressId;
    }
}
