import java.util.*;

public class OrderListRepo implements OrderRepo {
	private List<Order> orders = new ArrayList<>();

	public List<Order> getOrders() {
		return orders;
	}

	public Optional<Order> getOrderById(String id) {
		for (Order order : orders) {
			if (order.id().equals(id)) {
				return Optional.of(order);
			}
		}
		return Optional.empty();
	}

	public Order addOrder(Order newOrder) {
		orders.add(newOrder);
		return newOrder;
	}

	@Override
	public Order updateOrderStatus(String orderId, OrderStatus orderStatus) {
		Order orderToUpdate = getOrderById(orderId).orElseThrow(() -> new OrderNotFoundException("Order mit der Id: " + orderId + " konnte nicht gefunden werden!"));
		Order updatedOrder = orderToUpdate.withOrderStatus(orderStatus);
		removeOrder(orderId);
		orders.add(updatedOrder);
		return updatedOrder;
	}

	public void removeOrder(String id) {
		for (Order order : orders) {
			if (order.id().equals(id)) {
				orders.remove(order);
				return;
			}
		}
	}

	@Override
	public Map<OrderStatus, Order> getOldestOrderPerStatus() {
		Map<OrderStatus, Order> oldestOrderPerStatus = new HashMap<>();
		for (Order order : orders) {
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
