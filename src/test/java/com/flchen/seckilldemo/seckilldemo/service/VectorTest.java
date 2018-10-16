package com.flchen.seckilldemo.seckilldemo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/**
 * @author feilongchen
 * @since 2018-10-05 11:08 AM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class VectorTest {

	private final List<String> list = new Vector<>();

	@Test
	public void testClearByDefault() {
		list.clear();
	}

	@Test
	public void testList() {

		BigDecimal ii =BigDecimal.ZERO;
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add(0, "0");
//		Collections.reverse(list);
		list.forEach(e -> {
			System.out.println(e);
		});
	}
}
