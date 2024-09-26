package br.com.fiap.Marketplace.orderItem;

import br.com.fiap.Marketplace.orderItem.dto.OrderItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("orderItem")
public class OrderItemController {

    final PagedResourcesAssembler<OrderItem> pageAssembler;
    final OrderItemService orderItemService;

    public OrderItemController(OrderItemService orderItemService, PagedResourcesAssembler<OrderItem> pageAssembler) {
        this.orderItemService = orderItemService;
        this.pageAssembler = pageAssembler;
    }

    @GetMapping
    public PagedModel<EntityModel<OrderItem>> getOrderItems(@PageableDefault(sort = "order.data", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<OrderItem> page = orderItemService.findAll(pageable);
        return pageAssembler.toModel(page, OrderItem::toEntityModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<OrderItem>> getOrderItemById(@PathVariable UUID id) {
        OrderItem orderItem = orderItemService.findById(id);
        return ResponseEntity.ok(orderItem.toEntityModel());
    }

    @GetMapping("filterDate")
    public PagedModel<EntityModel<OrderItem>> getOrderItemsByOrderData(@PageableDefault(size = 5) @RequestParam LocalDate date, Pageable pageable) {
        Page<OrderItem> page = orderItemService.getOrderItemsByOrderData(date, pageable);
        return pageAssembler.toModel(page, OrderItem::toEntityModel);
    }

    @PostMapping
    public OrderItem createOrderItem(@RequestBody OrderItemRequest orderItemRequest) {
        return orderItemService.createOrderItem(orderItemRequest.toModel());
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItem> updateOrderItem(
            @PathVariable UUID id,
            @RequestBody OrderItemRequest orderItemRequest) {
        OrderItem updatedOrderItem = orderItemService.updateOrderItem(id, orderItemRequest.toModel());
        return ResponseEntity.ok(updatedOrderItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable UUID id) {
        orderItemService.deleteOrderItem(id);
        return ResponseEntity.noContent().build();
    }

}
