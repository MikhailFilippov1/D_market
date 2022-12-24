package ru.geekbrains.D_market.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.D_market.core.models.Category;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByTitle(String title);

    @Query("select c from Category c join fetch c.products where c.id = :id")
    Optional<Category> findByIdWithProducts(Long id);
}
