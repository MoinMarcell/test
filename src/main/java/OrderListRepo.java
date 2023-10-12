import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
}
