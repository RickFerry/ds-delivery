package br.com.ferry.deliver.service;

import br.com.ferry.deliver.model.Order;
import br.com.ferry.deliver.model.dto.OrderDto;
import br.com.ferry.deliver.model.enums.OrderStatus;
import br.com.ferry.deliver.repository.OrderRepository;
import br.com.ferry.deliver.repository.ProductRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public List<OrderDto> findAll() {
        return orderRepository.findByStatusAndByMoment().stream().map(OrderDto::new).collect(Collectors.toList());
    }

    @Transactional
    public OrderDto create(@NotNull OrderDto order) {
        Order build = Order.builder()
                .id(null)
                .address(order.getAddress())
                .latitude(order.getLatitude())
                .longitude(order.getLongitude())
                .moment(Instant.now())
                .status(OrderStatus.PENDING)
                .products(new HashSet<>())
                .build();
        order.getProducts().forEach(p -> build.getProducts().add(productRepository.getOne(p.getId())));
        return new OrderDto(orderRepository.save(build));
    }

    @Transactional
    public OrderDto setDelivered(Long id) {
        Order order = orderRepository.getOne(id);
        order.setStatus(OrderStatus.DELIVERED);
        return new OrderDto(orderRepository.save(order));
    }
}
