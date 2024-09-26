package br.com.fiap.Marketplace.orderItem;

import br.com.fiap.Marketplace.order.OrderService;
import br.com.fiap.Marketplace.product.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class OrderItemService {

    final OrderItemRepository orderItemRepository;
    final OrderService orderService;
    final ProductService productService;

    public OrderItemService(OrderItemRepository orderItemRepository, OrderService orderService, ProductService productService) {
        this.orderItemRepository = orderItemRepository;
        this.orderService = orderService;
        this.productService = productService;
    }

    public OrderItem createOrderItem(OrderItem orderItem) {
        var price = orderItem.getProduct().getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity()));
        orderItem.setPrice(price);
        var orderItemSaved = orderItemRepository.save(orderItem);
        var quantity = orderItemSaved.getQuantity();
        orderItemSaved.getOrder().setTotal(orderItemSaved.getOrder().getTotal().add(price));
        orderItemSaved.getProduct().setStock(orderItemSaved.getProduct().getStock() - quantity);
        orderService.updateOrder(orderItemSaved.getOrder());
        productService.updateProduct(orderItemSaved.getProduct());
        return orderItemSaved;
    }

    public Page<OrderItem> findAll(Pageable pageable) {
        return orderItemRepository.findAll(pageable);
    }

    public OrderItem updateOrderItem(UUID id, OrderItem orderItem) {
        orderItemRepository.findById(id).orElseThrow(() -> new RuntimeException("OrderItem Not Found"));
        orderItem.setId(id);
        return orderItemRepository.save(orderItem);
    }

    public Page<OrderItem> getOrderItemsByOrderData(LocalDate date, Pageable pageable) {
        return orderItemRepository.findByOrderDate(date, pageable);

    }

    public void deleteOrderItem(UUID id) {
        orderItemRepository.findById(id).orElseThrow(() -> new RuntimeException("OrderItem Not Found"));
        orderItemRepository.deleteById(id);
    }

    public OrderItem findById(UUID id) {
        return orderItemRepository.findById(id).orElseThrow(() -> new RuntimeException("OrderItem Not Found"));
    }
}
