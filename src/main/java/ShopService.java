import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class ShopService {
	private final ProductRepo productRepo;
	private final OrderRepo orderRepo;
	private final IdService idService;

	public Order addOrder(List<String> productIds) {
		List<Product> products = new ArrayList<>();
		for (String productId : productIds) {
			Product productToOrder = productRepo.getProductById(productId).orElseThrow(() -> new ProductNotFoundException("Product mit der Id: " + productId + " konnte nicht bestellt werden!"));
			if (productToOrder == null) {
				System.out.println("Product mit der Id: " + productId + " konnte nicht bestellt werden!");
				return null;
			}
			products.add(productToOrder);
		}

		Order newOrder = new Order(idService.generateId(), products, OrderStatus.PROCESSING, ZonedDateTime.now());

		return orderRepo.addOrder(newOrder);
	}

	public Order updateOrder(String orderId, OrderStatus orderStatus) {
		return orderRepo.updateOrderStatus(orderId, orderStatus);
    }

	/*
	Schreibt eine Methode getOldestOrderPerStatus, die eine Map mit dem ältesten Order-Objekt pro Status zurückgibt.
	 */
	public Map<OrderStatus, Order> getOldestOrderPerStatus() {
		return orderRepo.getOldestOrderPerStatus();
	}
}
