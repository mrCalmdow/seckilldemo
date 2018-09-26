package com.flchen.seckilldemo.seckilldemo.web.controller;

import com.flchen.seckilldemo.seckilldemo.entity.UserDO;
import com.flchen.seckilldemo.seckilldemo.repository.UserAutoRepository;
import com.flchen.seckilldemo.seckilldemo.web.mo.ResponseMO;
import com.flchen.seckilldemo.seckilldemo.web.mo.UserAddMO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author feilongchen
 * @since 2018-09-20 9:49 AM
 */
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController extends BaseController {

	@Autowired
	private UserAutoRepository userAutoRepository;

	@PostMapping
	public ResponseMO addUser(@RequestBody @Valid UserAddMO user) {
		UserDO userDO = userAutoRepository.findByPhone(user.getPhone());
		if(null != userDO) {
			return error("Phone number has registered!");
		}
		userDO = new UserDO();
		BeanUtils.copyProperties(user, userDO);
		userAutoRepository.save(userDO);
		return success();
	}

}
