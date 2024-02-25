package org.example.question_3.repository;

import jakarta.transaction.Transactional;
import org.example.question_3.entity.CategoryEntity;
import org.example.question_3.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProductRepository extends JpaRepository<ProductEntity,Long> {

    @Query("""
    SELECT P FROM ProductEntity as P
    WHERE P.categoryEntity.id IN :ids and P.price <= :price
    """)
    public List<ProductEntity> filterOption1 ( @Param("ids") List<Long> categoryId , Float price);

    @Query("""
    SELECT P FROM ProductEntity as P
    WHERE P.categoryEntity.id IN :ids  and P.price > :price
    """)
    public List<ProductEntity> filterOption2( @Param("ids") List<Long> ids , Float price );

    public List<ProductEntity> searchByName(String name);
}
