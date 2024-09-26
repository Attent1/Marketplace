package br.com.fiap.Marketplace.order;

import br.com.fiap.Marketplace.orderItem.OrderItem;
import br.com.fiap.Marketplace.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity(name = "ORDERS")
public class Orders {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @NotNull
    private LocalDateTime data;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    private BigDecimal total;

}
