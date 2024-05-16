package com.ssafy.fitmu.dao;

import java.util.List;

import com.ssafy.fitmu.dto.Order;

public interface OrderDao {
	public List<Order> getOrderList();
	public List<Order> getOrderListBySellerId(int sellerId);
	public List<Order> getOrderListByUserId(int userId);
	public Order getOrderDetail(int orderId);
	public int updateStatus(Order order);
	public int registOrder(Order order);
	public int deleteOrder(int orderId);
}
