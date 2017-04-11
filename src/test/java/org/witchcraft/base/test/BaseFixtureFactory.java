package org.witchcraft.base.test;

import java.util.List;

import org.springframework.core.GenericTypeResolver;
import org.witchcraft.base.entity.BaseEntity;

import com.td.bbwp.commerce.Customer;

import br.com.six2six.fixturefactory.Fixture;

public abstract class BaseFixtureFactory<T extends BaseEntity> {
	
	protected String arrayNames[] = { "Anderson", "Arthur", "Katy", "Mary", "John", "Hesus", "Ricardo" };
	
	abstract protected Class<T> getMyClass();
	
	abstract protected String getName();
	
	
	public List<T> getRecords(int num) {
		return Fixture.from(getMyClass()).gimme(num, getName());
	}
	
	public T getOneRecord() {
		return getRecords(1).get(0);
	}

}
