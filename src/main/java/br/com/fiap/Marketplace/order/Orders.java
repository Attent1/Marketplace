package br.com.fiap.Marketplace.order;

import br.com.fiap.Marketplace.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity(name = "ORDERS")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @JsonFormat(pattern="dd-MM-yyyy")
    @Builder.Default
    private LocalDate data = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder.Default
    private BigDecimal total = BigDecimal.ZERO;

}
