package othr.sw.koesler.entity.repo;

import othr.sw.koesler.entity.Order;
import othr.sw.koesler.entity.util.SingleIdEntityRepository;

import javax.enterprise.context.Dependent;

@Dependent
public class OrderRepo extends SingleIdEntityRepository<Long, Order> {
}
