import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class OrderListRepoTest {

    @Test
    void getOrders() {
        //GIVEN
        OrderListRepo repo = new OrderListRepo();

        Product product = new Product("1", "Apfel");
        Order newOrder = new Order("1", List.of(product), OrderStatus.PROCESSING, ZonedDateTime.of(2021, 1, 1, 1, 1, 1, 1, ZonedDateTime.now().getZone()));
        repo.addOrder(newOrder);

        //WHEN
        List<Order> actual = repo.getOrders();

        //THEN
        List<Order> expected = new ArrayList<>();
        Product product1 = new Product("1", "Apfel");
        expected.add(new Order("1", List.of(product1), OrderStatus.PROCESSING, ZonedDateTime.of(2021, 1, 1, 1, 1, 1, 1, ZonedDateTime.now().getZone())));

        assertEquals(actual, expected);
    }

    @Test
    void getOrderById() {
        //GIVEN
        OrderListRepo repo = new OrderListRepo();

        Product product = new Product("1", "Apfel");
        Order newOrder = new Order("1", List.of(product), OrderStatus.PROCESSING, ZonedDateTime.of(2021, 1, 1, 1, 1, 1, 1, ZonedDateTime.now().getZone()));
        repo.addOrder(newOrder);

        //WHEN
        Order actual = repo.getOrderById("1").orElseThrow();

        //THEN
        Product product1 = new Product("1", "Apfel");
        Order expected = new Order("1", List.of(product1), OrderStatus.PROCESSING, ZonedDateTime.of(2021, 1, 1, 1, 1, 1, 1, ZonedDateTime.now().getZone()));

        assertEquals(actual, expected);
    }

    @Test
    void addOrder() {
        //GIVEN
        OrderListRepo repo = new OrderListRepo();
        Product product = new Product("1", "Apfel");
        Order newOrder = new Order("1", List.of(product), OrderStatus.PROCESSING, ZonedDateTime.of(2021, 1, 1, 1, 1, 1, 1, ZonedDateTime.now().getZone()));

        //WHEN
        Order actual = repo.addOrder(newOrder);

        //THEN
        Product product1 = new Product("1", "Apfel");
        Order expected = new Order("1", List.of(product1), OrderStatus.PROCESSING, ZonedDateTime.of(2021, 1, 1, 1, 1, 1, 1, ZonedDateTime.now().getZone()));
        assertEquals(actual, expected);
        assertEquals(repo.getOrderById("1").get(), expected);
    }

    @Test
    void removeOrder() {
        //GIVEN
        OrderListRepo repo = new OrderListRepo();

        Product product = new Product("1", "Apfel");
        Order newOrder = new Order("1", List.of(product), OrderStatus.PROCESSING, ZonedDateTime.of(2021, 1, 1, 1, 1, 1, 1, ZonedDateTime.now().getZone()));
        repo.addOrder(newOrder);

        //WHEN
        repo.removeOrder("1");

        //THEN
        assertNull(repo.getOrderById("1").orElse(null));
    }
}
