package br.com.ferry.deliver.model.dto;

import br.com.ferry.deliver.model.Order;
import br.com.ferry.deliver.model.enums.OrderStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Builder @Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDto {
    @Setter
    Long id;
    @Setter
    String address;
    @Setter
    Double latitude;
    @Setter
    Double longitude;
    @Setter
    Instant moment;
    @Setter
    OrderStatus status;
    @Setter
    Double total;

    Set<ProductDto> products = new HashSet<>();

    public OrderDto(@NotNull Order order) {
        this.id = order.getId();
        this.address = order.getAddress();
        this.latitude = order.getLongitude();
        this.longitude = order.getLongitude();
        this.moment = order.getMoment();
        this.status = order.getStatus();
        this.total = order.getTotal();
        this.products = order.getProducts().stream().map(ProductDto::new).collect(Collectors.toSet());
    }
}
