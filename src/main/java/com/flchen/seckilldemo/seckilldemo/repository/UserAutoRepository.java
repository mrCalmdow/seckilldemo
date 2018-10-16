package com.flchen.seckilldemo.seckilldemo.repository;

import com.flchen.seckilldemo.seckilldemo.entity.UserDO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author feilongchen
 * @since 2018-09-20 9:44 AM
 */
@Repository
public interface UserAutoRepository extends MongoRepository<UserDO, String> {

	UserDO findByPhone(String phone);

	UserDO findByUsername(String username);
}
