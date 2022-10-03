package com.glx.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.glx.app.dto.Order;

public interface OrdersRepo extends JpaRepository<Order, Integer> {

}
