package com.flchen.seckilldemo.seckilldemo.web.controller;

import com.flchen.seckilldemo.seckilldemo.repository.UserAutoRepository;
import com.flchen.seckilldemo.seckilldemo.web.BaseControllerTestCase;
import com.flchen.seckilldemo.seckilldemo.web.mo.UserAddMO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author feilongchen
 * @since 2018-09-20 10:28 AM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest extends BaseControllerTestCase {

	private String urlTemplate = "/users";

	@Autowired
	private UserAutoRepository userAutoRepository;

	@Before
	public void setUp() {
		userAutoRepository.deleteAll();
	}

	@Test
	public void testAddUsers() throws Exception {
		UserAddMO userAddMO = new UserAddMO();
		Long startNumber = 1000L;
		Long endNumber = 2000L;

		while(startNumber < endNumber) {
			String username = "chen";
			String phone = "1868888";
			phone = phone + startNumber;
			username = username + startNumber;
			userAddMO.setPhone(phone);
			userAddMO.setUsername(username);
			mockMvc.perform(populatePostBuilder(urlTemplate, userAddMO));
			startNumber ++;
		}
	}

	@Test
	public void testGenerateTestData() throws IOException {

		File file = new File(System.getProperty("user.dir") + "/testData.csv");
		FileWriter fileWriter = new FileWriter(file);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		List<String> products = new ArrayList<>();
		products.add("huawei");
		products.add("xiaomi");
		products.add("vivo");
		products.add("iphone");

		Long startNumber = 1000L;
		Long endNumber = 2000L;
		List<String> list = new ArrayList<>();
		while(startNumber < endNumber) {
			String phone = "1868888";
			phone = phone + startNumber;
			Random random = new Random();
			list.add(phone + "," + products.get(random.nextInt(4)));
			startNumber++;
		}

		for (String str : list) {
			bufferedWriter.write(str);
			bufferedWriter.newLine();
		}
		bufferedWriter.flush();
		bufferedWriter.close();
		fileWriter.close();
	}

	@Test
	public void test() {
		System.out.println(System.getProperty("user.dir"));
	}
}
