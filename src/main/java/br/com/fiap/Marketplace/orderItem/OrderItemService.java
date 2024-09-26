package br.com.fiap.Marketplace.orderItem;

import br.com.fiap.Marketplace.order.OrderService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderItemService {

    final OrderItemRepository orderItemRepository;
    final OrderService orderService;

    public OrderItemService(OrderItemRepository orderItemRepository, OrderService orderService) {
        this.orderItemRepository = orderItemRepository;
        this.orderService = orderService;
    }

    public OrderItem createOrderItem(OrderItem orderItem) {
        var price = orderItem.getProduct().getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity()));
        orderItem.setPrice(price);
        var orderItemSaved = orderItemRepository.save(orderItem);
        orderItemSaved.getOrder().setTotal(orderItemSaved.getOrder().getTotal().add(price));
        orderService.updateOrder(orderItemSaved.getOrder());
        return orderItemSaved;
    }

    public List<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }
    
}
