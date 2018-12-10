package othr.sw.koesler.service;

import othr.sw.koesler.entity.Order;
import othr.sw.koesler.entity.repo.BookingRepo;
import othr.sw.koesler.entity.util.OrderType;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.jws.WebService;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Date;

@WebService
@SessionScoped
public class BookingService implements Serializable {

    @Inject
    private TransportService transportService;
    @Inject
    private BookingRepo bookingRepo;

    @Transactional
    public Order createOrder(int id, OrderType type) {
        //TODO
        Order order = new Order();
        return order;

    }

    @Transactional
    public boolean cancelOrder(int id) {
        //TODO
        //bookingRepo.remove();
        return true;
    }

    public boolean checkAvailability(OrderType type, Date date, int amount) {
        //TODO
        System.out.println("CHECKING AVAILABILITY");
        return true;
    }
}
