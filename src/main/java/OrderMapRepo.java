import java.util.*;

public class OrderMapRepo implements OrderRepo{
    private Map<String, Order> orders = new HashMap<>();

    @Override
    public List<Order> getOrders() {
        return new ArrayList<>(orders.values());
    }

    @Override
    public Optional<Order> getOrderById(String id) {
        return Optional.of(orders.get(id));
    }

    @Override
    public Order addOrder(Order newOrder) {
        orders.put(newOrder.id(), newOrder);
        return newOrder;
    }

    @Override
    public Order updateOrderStatus(String orderId, OrderStatus orderStatus) {
        Order orderToUpdate = orders.get(orderId);
        if (orderToUpdate == null) {
            System.out.println("Order mit der Id: " + orderId + " konnte nicht gefunden werden!");
            return null;
        }

        Order updatedOrder = orderToUpdate.withOrderStatus(orderStatus);
        orders.remove(orderId);
        orders.put(orderId, updatedOrder);
        return updatedOrder;
    }

    @Override
    public void removeOrder(String id) {
        orders.remove(id);
    }

	@Override
	public Map<OrderStatus, Order> getOldestOrderPerStatus() {
		Map<OrderStatus, Order> oldestOrderPerStatus = new HashMap<>();
		for (Order order : orders.values()) {
			OrderStatus orderStatus = order.orderStatus();
			if (oldestOrderPerStatus.containsKey(orderStatus)) {
				Order oldestOrder = oldestOrderPerStatus.get(orderStatus);
				if (order.orderDate().isBefore(oldestOrder.orderDate())) {
					oldestOrderPerStatus.put(orderStatus, order);
				}
			} else {
				oldestOrderPerStatus.put(orderStatus, order);
			}
		}
		return oldestOrderPerStatus;
	}
}
