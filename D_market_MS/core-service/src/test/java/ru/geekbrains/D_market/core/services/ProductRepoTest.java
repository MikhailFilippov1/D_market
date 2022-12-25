package ru.geekbrains.D_market.core.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import ru.geekbrains.D_market.core.models.Product;
import ru.geekbrains.D_market.core.repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class ProductRepoTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void productRepositoryTest(){
        Product product = new Product();
//        product.setId(20L); TODO: Не работает, пишет: detached entity passed to persist
        product.setTitle("Beer");
        product.setPrice(BigDecimal.valueOf(333));

        entityManager.persist(product);
        entityManager.flush();

        List<Product> productList = productRepository.findAll();

        Assertions.assertEquals("Beer", productList.get(20).getTitle());
        Assertions.assertEquals(333, productList.get(20).getPrice());
        Assertions.assertEquals(21, productList.size());
    }

    @Test
    public void initDbTest(){
        List<Product> productList = productRepository.findAll();

        Assertions.assertEquals(10, productList.size());
    }
}
