package org.witchcraft.base.test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.witchcraft.base.entity.BaseEntity;

import br.com.six2six.fixturefactory.Fixture;

public abstract class BaseFixtureFactory<T extends BaseEntity> {
	
	protected static int UNIQUE_ID = 0;
	
	protected String arrayNames[] = { "Anderson", "Arthur", "Katy", "Mary", "John", "Hesus", "Ricardo" };
	
	abstract protected Class<T> getMyClass();
	
	abstract protected String getName();
	
	
	public List<T> getRecords(int num) {
		return Fixture.from(getMyClass()).gimme(num, getName());
	}
	
	public T getOneRecord() {
		return getRecords(1).get(0);
	}
	
	public Object[] getUniqueNames(){ 
		return Arrays.asList(arrayNames).stream()
			.map(x -> x + (UNIQUE_ID++))
			.collect(Collectors.toSet())
			.toArray();
		
	}

}
