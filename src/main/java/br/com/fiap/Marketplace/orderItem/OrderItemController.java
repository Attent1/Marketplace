package br.com.fiap.Marketplace.orderItem;

import br.com.fiap.Marketplace.orderItem.OrderItem;
import br.com.fiap.Marketplace.orderItem.OrderItemService;
import br.com.fiap.Marketplace.orderItem.dto.OrderItemRequest;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("orderItem")
public class OrderItemController {

    final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping
    public List<OrderItem> getOrderItems() {
        return orderItemService.findAll();
    }

    @PostMapping
    public OrderItem createOrderItem(@RequestBody OrderItemRequest orderItemRequest) {
        return orderItemService.createOrderItem(orderItemRequest.toModel());
    }
    
}
