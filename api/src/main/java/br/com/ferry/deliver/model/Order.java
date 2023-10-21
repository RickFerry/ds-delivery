package br.com.ferry.deliver.model;

import br.com.ferry.deliver.model.enums.OrderStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
@Entity @Builder @Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {
    @Id @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    Double total;

    @ManyToMany
    @JoinTable(name = "orders_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    Set<Product> products = new HashSet<>();

    public Double getTotal() {
        Double sum = 0.0;
        for (Product product : getProducts()) {
            sum += product.getPrice();
        }
        return sum;
    }
}
