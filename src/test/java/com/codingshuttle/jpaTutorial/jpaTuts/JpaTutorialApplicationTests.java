package com.codingshuttle.jpaTutorial.jpaTuts;

import com.codingshuttle.jpaTutorial.jpaTuts.entities.ProductEntity;
import com.codingshuttle.jpaTutorial.jpaTuts.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class JpaTutorialApplicationTests {

	@Autowired
	ProductRepository productRepository;
    private LocalDateTime LocalDateTime;

    @Test
	void contextLoads() {
	}

	@Test
	void testRepository() {
		ProductEntity productEntity = ProductEntity.builder()
				.sku("britannia")
				.title("Biskfarm Marie")
				.price(BigDecimal.valueOf(40.56))
				.quantity(7)
				.build();

		ProductEntity savedProductEntity = productRepository.save(productEntity);
		System.out.println(savedProductEntity);
	}

    @Test
    void getProductsLessThanAmount(){
        List<ProductEntity> entities = productRepository.findByPriceLessThanAndTitleContainingIgnoreCase(15,"Pa");
        System.out.println("Raw entities :::"+entities);
        List<Integer> quants = entities.stream()
                        .map(ProductEntity::getQuantity)
                        .filter(count->count>2).toList();
        System.out.println(quants);
    }



	@Test
	void getRepository() {
		List<ProductEntity> entities = productRepository.findByCreatedAtAfterOrderByTitle(
				LocalDateTime.of(2025, 1, 1, 0, 0, 0 ));
		List<ProductEntity> entities1 = productRepository.findByQuantityGreaterThanOrPriceLessThan(4, BigDecimal.valueOf(23.45));
		List<ProductEntity> entities2 = productRepository.findByTitleContainingIgnoreCase("CHOco", null);
		System.out.println("---->"+entities);
	}

	@Test
	void getSingleFromRepository() {
		Optional<ProductEntity> productEntity = productRepository
				.findByTitleAndPrice("KitKat", BigDecimal.valueOf(13.7));
        System.out.println(" Get Single Product from Repo");
		productEntity.ifPresent(System.out::println);
	}

}

