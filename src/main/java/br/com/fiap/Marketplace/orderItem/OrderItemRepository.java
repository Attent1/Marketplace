package br.com.fiap.Marketplace.orderItem;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
     @Query("SELECT oi FROM OrderItem oi JOIN oi.order o WHERE o.data = :date")
     Page<OrderItem> findByOrderDate(@Param("date") LocalDate date, Pageable pageable);
}
