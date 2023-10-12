import java.util.List;

public class Main {
	public static void main(String[] args) {
		ProductRepo productRepo = new ProductRepo();
		OrderRepo orderRepo = new OrderMapRepo();
		IdService idService = new IdService();
		ShopService shopService = new ShopService(productRepo, orderRepo, idService);

		// Legt drei konkrete Bestellungen fest und fügt sie alle dem ShopService hinzu.
		List<String> productsIds = List.of("1", "2");
		Order order1 = shopService.addOrder(productsIds);
		productsIds = List.of("1", "2", "3");
		Order order2 = shopService.addOrder(productsIds);
		productsIds = List.of("1", "2", "3", "4");
		Order order3 = shopService.addOrder(productsIds);
	}
}
