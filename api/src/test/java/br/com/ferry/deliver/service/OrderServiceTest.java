package br.com.ferry.deliver.service;

import br.com.ferry.deliver.model.Order;
import br.com.ferry.deliver.model.Product;
import br.com.ferry.deliver.model.dto.OrderDto;
import br.com.ferry.deliver.model.enums.OrderStatus;
import br.com.ferry.deliver.repository.OrderRepository;
import br.com.ferry.deliver.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ProductRepository productRepository;

    private Order order;
    private OrderDto orderDto;
    private Product product;

    @BeforeEach
    void setUp() {
        openMocks(this);
        startOrders();
    }

    @Test
    void whenFindAllThenReturnListOfOrderDto() {
        when(orderRepository.findByStatusAndByMoment())
                .thenReturn(List.of(order));

        List<OrderDto> resp = orderService.findAll();
        assertEquals(OrderDto.class, resp.get(0).getClass());
    }

    @Test
    void whenCreateThenReturnOrderDto() {
        when(orderRepository.save(any())).thenReturn(order);
        when(productRepository.getOne(any())).thenReturn(product);

        OrderDto resp = orderService.create(orderDto);
        assertNotNull(resp);
        assertEquals(OrderDto.class, resp.getClass());
    }

    @Test
    void whenSetDeliveredChangeStatusDelivered() {
        when(orderRepository.getOne(any())).thenReturn(order);
        when(orderRepository.save(any())).thenReturn(order);

        OrderDto resp = orderService.setDelivered(any());
        assertEquals(OrderStatus.DELIVERED, resp.getStatus());
    }

    private void startOrders() {
        this.order = Order.builder()
                .id(1L)
                .address("address-address-address")
                .latitude(12345.0)
                .longitude(54321.0)
                .moment(Instant.now())
                .status(OrderStatus.PENDING)
                .products(new HashSet<>())
                .build();

        this.orderDto = OrderDto.builder()
                .id(1L)
                .address("address-address-address")
                .latitude(12345.0)
                .longitude(54321.0)
                .moment(Instant.now())
                .status(OrderStatus.PENDING)
                .products(new HashSet<>())
                .build();

        this.product = Product.builder()
                .id(1L)
                .name("name-name-name")
                .price(999.9)
                .description("description-description-description")
                .imageUri("fggfdretredgfhghfvbvcvcbcvb")
                .build();
    }
}