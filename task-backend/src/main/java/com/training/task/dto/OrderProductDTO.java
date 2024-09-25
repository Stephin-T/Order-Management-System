package com.training.task.dto;

public class OrderProductDTO {

    private Long orderProductId;
    private Long orderId;
    private Long productId;
    private Integer quantity;
    private String name;
    private Double price;
    
    
    

    public OrderProductDTO() {
		super();
	}

	public OrderProductDTO(Long orderProductId, Long orderId, Long productId, Integer quantity, String name,
			Double price) {
		super();
		this.orderProductId = orderProductId;
		this.orderId = orderId;
		this.productId = productId;
		this.quantity = quantity;
		this.name = name;
		this.price = price;
	}

	public Long getOrderProductId() {
        return orderProductId;
    }

    public void setOrderProductId(Long orderProductId) {
        this.orderProductId = orderProductId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
