package com.ssafy.fitmu.dto;

import java.util.Date;

public class Order {
	private int orderId;
	private int productId;
	private int userId;
	private int sellerId;
	private int quantity;
	private String status; // 구매완료, 배송중, 배송완료, 구매확정
	private String confirmedAt; //구매확정날짜
	private String createdAt; //주문일자
	private String memo;
	
	public Order() {
		
	}
	public Order(int orderId, int productId, int userId, int sellerId, int quantity, String status,
		 String memo) {
		this.productId = productId;
		this.userId = userId;
		this.sellerId = sellerId;
		this.quantity = quantity;
		this.status = status;
		this.confirmedAt = new Date().toString();
		this.createdAt = new Date().toString();
		this.memo = memo;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getSellerId() {
		return sellerId;
	}
	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getConfirmedAt() {
		return confirmedAt;
	}
	public void setConfirmedAt(String confirmedAt) {
		this.confirmedAt = confirmedAt;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", productId=" + productId + ", userId=" + userId + ", sellerId="
				+ sellerId + ", quantity=" + quantity + ", status=" + status + ", confirmedAt=" + confirmedAt
				+ ", createdAt=" + createdAt + ", memo=" + memo + "]";
	}
}
