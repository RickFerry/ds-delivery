package br.com.ferry.deliver.model.dto;

import br.com.ferry.deliver.model.Order;
import br.com.ferry.deliver.model.enums.OrderStatus;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private String address;
    private Double latitude;
    private Double longitude;
    private Instant moment;
    private OrderStatus status;
    private Set<ProductDto> products = new HashSet<>();

    public OrderDto(@NotNull Order order) {
        this.id = order.getId();
        this.address = order.getAddress();
        this.latitude = order.getLongitude();
        this.longitude = order.getLongitude();
        this.moment = order.getMoment();
        this.status = order.getStatus();
        this.products = order.getProducts().stream().map(ProductDto::new).collect(Collectors.toSet());
    }
}
