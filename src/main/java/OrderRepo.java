import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface OrderRepo {

    List<Order> getOrders();

    Optional<Order> getOrderById(String id);

    Order addOrder(Order newOrder);

    Order updateOrderStatus(String orderId, OrderStatus orderStatus);

    void removeOrder(String id);

    Map<OrderStatus, Order> getOldestOrderPerStatus();
}
