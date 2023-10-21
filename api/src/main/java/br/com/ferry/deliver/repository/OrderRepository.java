package br.com.ferry.deliver.repository;

import br.com.ferry.deliver.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.status = 0 order by o.moment")
    List<Order> findByStatusAndByMoment();
}