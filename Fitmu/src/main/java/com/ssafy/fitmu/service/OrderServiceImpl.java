package com.ssafy.fitmu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.fitmu.dao.OrderDao;
import com.ssafy.fitmu.dto.Order;

@Service
public class OrderServiceImpl implements OrderService{
	private OrderDao orderDao;
	public OrderServiceImpl(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	@Override
	public List<Order> getOrderList() {
		return orderDao.getOrderList();
	}

	@Override
	public List<Order> getOrderListBySellerId(int sellerId) {
		return orderDao.getOrderListBySellerId(sellerId);
	}

	@Override
	public List<Order> getOrderListByUserId(int userId) {
		return orderDao.getOrderListByUserId(userId);
	}

	@Override
	public Order getOrderDetail(int orderId) {
		return orderDao.getOrderDetail(orderId);
	}

	@Override
	public int updateStatus(Order order) {
		return orderDao.updateStatus(order);
	}

	@Override
	public int registOrder(Order order) {
		return orderDao.registOrder(order);
	}

	@Override
	public int deleteOrder(int orderId) {
		return orderDao.deleteOrder(orderId);
	}

}
