package com.flchen.seckilldemo.seckilldemo.repository;

import com.flchen.seckilldemo.seckilldemo.entity.OrderDO;
import com.flchen.seckilldemo.seckilldemo.entity.ProductDO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author feilongchen
 * @create 2018-09-20 9:46 AM
 */
@Repository
public interface OrderAutoRepository extends MongoRepository<OrderDO, String> {

	ProductDO findByUser_Username(String name);

	ProductDO findByUser_Phone(String phone);

	ProductDO findByUser_Id(String id);
}
