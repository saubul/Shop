package ru.saubulprojects.shop.service;

import java.time.LocalDate;

import org.springframework.data.domain.Page;

import ru.saubulprojects.shop.model.Order;
import ru.saubulprojects.shop.model.User;

public interface OrderService {

	Order save(Order order);

	Order findById(Long id);
	
	Page<Order> findAllByUser(User user, int pageNo);

	Page<Order> findAllByUserAndDate(User user, LocalDate date, int pageNo);
}
