package othr.sw.koesler.entity.repo;

import othr.sw.koesler.entity.Customer;
import othr.sw.koesler.entity.util.SingleIdEntityRepository;

import javax.enterprise.context.Dependent;

@Dependent
public class CustomerRepo extends SingleIdEntityRepository<String, Customer> {
}
