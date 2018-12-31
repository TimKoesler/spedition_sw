package othr.sw.koesler.service;

import othr.sw.koesler.entity.Address;
import othr.sw.koesler.entity.Customer;
import othr.sw.koesler.entity.repo.CustomerRepo;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;

@RequestScoped
public class UserService implements Serializable {
    @PersistenceContext(unitName = "speditionPU")
    private EntityManager entityManager;

    @Inject
    private CustomerRepo customerRepo;

    @Transactional
    public Customer login(String user, String password) {
        // customerRepo.getAll();
        for (Customer c : customerRepo.getAll()) {
            if (c.getUser().equals(user)) {
                if (c.getPassword().equals(password)) {
                    return c;
                }
            }
        }
        return null;
    }

    @Transactional
    public Customer register(String firstname, String lastname, Address address, String user, String password) {
        for (Customer c : customerRepo.getAll()) {
            if(c.getUser().equals(user)) {
                return null;
            }
        }
        Customer newCustomer = new Customer(firstname, lastname, address);
        newCustomer.setUser(user);
        newCustomer.setPassword(password);
        customerRepo.persist(newCustomer);
        return newCustomer;
    }

    @Transactional
    public Customer updateUser(Customer c) {
        return entityManager.merge(c);
    }

    @Transactional
    public String deleteUser(Customer c) {
        c = entityManager.find(Customer.class, c.getId());
        entityManager.remove(c);
        return "home";
    }
}
