package ru.saubulprojects.shop.service.impl;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import ru.saubulprojects.shop.model.Order;
import ru.saubulprojects.shop.model.User;
import ru.saubulprojects.shop.repository.OrderRepository;
import ru.saubulprojects.shop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	private final OrderRepository orderRepo;
	
	public OrderServiceImpl(OrderRepository orderRepo) {
		this.orderRepo = orderRepo;
	}
	
	@Override
	public Order save(Order order) {
		return orderRepo.saveAndFlush(order);
	}

	@Override
	public Order findById(Long id) {
		return orderRepo.findById(id).get();
	}

	@Override
	public Page<Order> findAllByUser(User user, int pageNo) {
		return orderRepo.findAllByUser(user, PageRequest.of(pageNo - 1, 5));
	}

	@Override
	public Page<Order> findAllByUserAndDate(User user, LocalDate date, int pageNo) {
		return orderRepo.findAllByUserAndDateCreated(user, date, PageRequest.of(pageNo - 1, 5));
	}
}
