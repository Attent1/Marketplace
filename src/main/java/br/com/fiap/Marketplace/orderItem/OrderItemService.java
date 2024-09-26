package br.com.fiap.Marketplace.orderItem;

import br.com.fiap.Marketplace.order.OrderService;
import br.com.fiap.Marketplace.product.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

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

    public List<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }
    
}
