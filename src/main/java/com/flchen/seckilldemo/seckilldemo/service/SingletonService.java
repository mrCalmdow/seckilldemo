package com.flchen.seckilldemo.seckilldemo.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author feilongchen
 * @since 2018-10-09 3:24 PM
 */
@Service
public class SingletonService {

	private final List<String> list = new ArrayList<>();


	public void initList() {
		list.add("hello");
		list.add("world");
		meansureSize();
	}

	public void reloadList() {
		list.clear();
		meansureSize();
	}

	public void meansureSize() {
		System.out.println("~~~list size: " + list.size());
	}

	public int getListSize() {
		return list.size();
	}
}
