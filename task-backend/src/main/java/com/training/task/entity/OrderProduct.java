package com.training.task.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "orders_products")
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_product_id_PK")
    private Long orderProductId;

    @ManyToOne
    @JoinColumn(name = "order_id_FK")
    @JsonBackReference
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id_FK")
    private Product product;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

   
    public OrderProduct() {
    }

    
    public OrderProduct(Long orderProductId, Order order, Product product, Integer quantity) {
        this.orderProductId = orderProductId;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }

   
    public Long getOrderProductId() {
        return orderProductId;
    }

    public void setOrderProductId(Long orderProductId) {
        this.orderProductId = orderProductId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}

