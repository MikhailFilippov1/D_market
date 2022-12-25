package ru.geekbrains.D_market.core.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geekbrains.D_market.api.CartDto;
import ru.geekbrains.D_market.api.CartItemDto;
import ru.geekbrains.D_market.core.integrations.CartServiceIntegration;
import ru.geekbrains.D_market.core.models.Category;
import ru.geekbrains.D_market.core.models.Order;
import ru.geekbrains.D_market.core.models.Product;
import ru.geekbrains.D_market.core.repositories.OrderRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest(classes = OrderService.class)
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @MockBean
    private CartServiceIntegration cartServiceIntegration;

    @MockBean
    private ProductService productService;

    @MockBean
    private OrderRepository orderRepository;

    @Test
    public void createOrderTest(){
        CartDto cartDto = new CartDto();
        List<CartItemDto> items = new ArrayList<>();

        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setProductId(777L);
        cartItemDto.setProductTitle("VINE");
        cartItemDto.setQuantity(1);
        cartItemDto.setPricePerProduct(BigDecimal.valueOf(777));
        cartItemDto.setPrice(BigDecimal.valueOf(777));

        cartDto.setTotalPrice(BigDecimal.valueOf(777));
        cartDto.setItems(items);

        Mockito.doReturn(cartDto).when(cartServiceIntegration).getCurrentCart(null);

        Category category = new Category();
        category.setId(1L);
        category.setTitle("Other");
        Product product = new Product();
        product.setId(777L);
        product.setTitle("Vine");
        product.setPrice(BigDecimal.valueOf(777));
        product.setCategory(category);

        Mockito.doReturn(Optional.of(product)).when(productService).findById(777L);

        Order order = orderService.createOrder("Zoe");
        Assertions.assertEquals(order.getTotalPrice(), BigDecimal.valueOf(777));
        Mockito.verify(orderRepository,Mockito.times(1)).save(ArgumentMatchers.any());
    }
}
