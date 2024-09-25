package com.training.task.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "orders"}) 
public class Order {

    @Id
    @Column(name = "order_id_PK")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "cust_id_PK")
    private Customer customer;

    @Column(name = "invoice_creation_date", nullable = false)
    private LocalDateTime invoiceCreationDate;

    @Column(name = "delivery_due_date", nullable = false)
    private LocalDateTime deliveryDueDate;

    @Column(name = "payment_due_date", nullable = false)
    private LocalDateTime paymentDueDate;

    @Column(name = "custom_message")
    private String customMessage;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProducts;

   
    public Order() {
    }

    
    public Order(Long orderId, Customer customer, LocalDateTime invoiceCreationDate, LocalDateTime deliveryDueDate,
                 LocalDateTime paymentDueDate, String customMessage, List<OrderProduct> orderProducts) {
        this.orderId = orderId;
        this.customer = customer;
        this.invoiceCreationDate = invoiceCreationDate;
        this.deliveryDueDate = deliveryDueDate;
        this.paymentDueDate = paymentDueDate;
        this.customMessage = customMessage;
        this.orderProducts = orderProducts;
    }

   
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
   
  
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getInvoiceCreationDate() {
        return invoiceCreationDate;
    }

    public void setInvoiceCreationDate(LocalDateTime invoiceCreationDate) {
        this.invoiceCreationDate = invoiceCreationDate;
    }

    public LocalDateTime getDeliveryDueDate() {
        return deliveryDueDate;
    }

    public void setDeliveryDueDate(LocalDateTime deliveryDueDate) {
        this.deliveryDueDate = deliveryDueDate;
    }

    public LocalDateTime getPaymentDueDate() {
        return paymentDueDate;
    }

    public void setPaymentDueDate(LocalDateTime paymentDueDate) {
        this.paymentDueDate = paymentDueDate;
    }

    public String getCustomMessage() {
        return customMessage;
    }

    public void setCustomMessage(String customMessage) {
        this.customMessage = customMessage;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }
}

