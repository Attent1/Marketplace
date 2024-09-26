package br.com.fiap.Marketplace.order.dto;

import br.com.fiap.Marketplace.order.Orders;
import br.com.fiap.Marketplace.user.User;

public record OrderRequest(User user) {

    public Orders toModel(){
        return Orders.builder()
                .user(user)
                .build();
    }

}
