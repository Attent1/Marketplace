package br.com.fiap.Marketplace.order;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Orders createOrder(Orders order) {
        return orderRepository.save(order);
    }

    public List<Orders> findAll() {
        return orderRepository.findAll();
    }

    public void updateOrder(Orders order) {
        orderRepository.save(order);
    }

}
