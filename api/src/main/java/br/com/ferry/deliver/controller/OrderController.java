package br.com.ferry.deliver.controller;


import br.com.ferry.deliver.model.dto.OrderDto;
import br.com.ferry.deliver.service.OrderService;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.List;

/**
 *
 */
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    /**
     * @param orderService
     */
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * @return list
     */
    @GetMapping
    public ResponseEntity<List<OrderDto>> findAll(){
        return  ResponseEntity.ok(orderService.findAll());
    }

    @PostMapping
    public  ResponseEntity<OrderDto> create(@RequestBody OrderDto order,
                                            @NotNull UriComponentsBuilder uriComponentsBuilder){
        return ResponseEntity
                .created(uriComponentsBuilder.path("/orders/{id}").buildAndExpand(orderService.create(order).getId())
                        .toUri()).body(orderService.create(order));
    }

    @PutMapping("/{id}/delivered")
    public ResponseEntity<OrderDto> setDelivered(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.setDelivered(id));
    }
}
