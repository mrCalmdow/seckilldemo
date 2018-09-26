package com.flchen.seckilldemo.seckilldemo.repository;

import com.flchen.seckilldemo.seckilldemo.entity.ProductDO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author feilongchen
 * @since 2018-09-20 9:45 AM
 */
@Repository
public interface ProductAutoRepository extends MongoRepository<ProductDO, String> {

	ProductDO findByProductName(String productName);
}
