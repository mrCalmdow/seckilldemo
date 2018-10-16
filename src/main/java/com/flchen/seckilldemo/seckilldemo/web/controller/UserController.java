package com.flchen.seckilldemo.seckilldemo.web.controller;

import com.flchen.seckilldemo.seckilldemo.entity.UserDO;
import com.flchen.seckilldemo.seckilldemo.repository.UserAutoRepository;
import com.flchen.seckilldemo.seckilldemo.service.RedisCacheManager;
import com.flchen.seckilldemo.seckilldemo.service.SingletonService;
import com.flchen.seckilldemo.seckilldemo.web.mo.ResponseMO;
import com.flchen.seckilldemo.seckilldemo.web.mo.UserAddMO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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

	@Autowired
	private RedisCacheManager redisCacheManager;

	@Autowired
	private SingletonService singletonService;

	@GetMapping("/testSingleton")
	public ResponseMO testSingleton() {
		singletonService.initList();
		return success(singletonService);
	}

	@PostMapping
	public ResponseMO addUser(@RequestBody @Valid UserAddMO user) {
		UserDO userDO = userAutoRepository.findByPhone(user.getPhone());
		if(null != userDO) {
			return error("Phone number has registered!");
		}
		userDO = new UserDO();
		BeanUtils.copyProperties(user, userDO);
		userDO = userAutoRepository.save(userDO);
		redisCacheManager.addOrUpdateValue(userDO.getUsername(), userDO);
		return success();
	}

	@DeleteMapping("/{username}")
	public ResponseMO deleteUser(@PathVariable @NotNull String username) {
		UserDO userDO = userAutoRepository.findByUsername(username);
		userAutoRepository.delete(userDO);
		redisCacheManager.evictValue(username);
		return success();
	}

	@GetMapping("/{username}")
	public ResponseMO getUser(@PathVariable @NotNull String username) {
		return null;
	}

}
