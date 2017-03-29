package org.witchcraft.base.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.witchcraft.base.entity.BaseEntity;

/**
 * This class is the base for test data factories for various entities
 * 
 * @author jsingh
 *
 */
public abstract class AbstractTestDataFactory<T extends BaseEntity> {
	
	public static final Integer MAX_RECORDS = 5;

	/*
	public T getRandomRecord() {
		return getListOfRecords().get(new Random().nextInt(MAX_RECORDS-1));
	}

	public   List<T> getNRandomRecords(int n) {
		List<Integer> integers = IntStream.range(0, MAX_RECORDS-1) // <-- creates a stream
														// of ints
				.boxed() // <-- converts them to Integers
				.collect(Collectors.toList()); // <-- collects the values to a
												// list
		
		List<T> records = getListOfRecords();
		
		List<T> results = new ArrayList<>();

		Collections.shuffle(integers);

		for(int i = 0; i < n; i++)
			results.add( records.get(i) );
		
		return results;
		
	}*/

	public List<T> getListOfRecordsOrg() {
		// TODO Auto-generated method stub
		return null;
	}

	public void persistAll() {
	};
	
	protected static void handleCreationException22(Exception ex) {
		ex.printStackTrace();
		//in case of failure remove the last element
		//List<T> records = getListOfRecords();
		//records.remove(records.size() - 1);
	}

}