package org.witchcraft.base.spring;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.transaction.annotation.Transactional;
import org.witchcraft.base.entity.BaseEntity;
import org.witchcraft.base.entity.BaseRepository;

@Transactional
public abstract class BaseServiceImpl<T extends BaseEntity> {
	
	public abstract BaseRepository<T> getRepository();
	
	
	//@Override
	public T save(T t) {
		
		return getRepository().save(t);
	}

	
	public Optional<T> findOne(Long id) {
		return  getRepository().findById(id);
	}

	
	public Iterable<T> findAll() {
		return getRepository().findAll();
	}
	
	
	public Stream<T> findAllStream() {
		return getRepository().allEntities();
	}

	//@Override
	public void delete(Long id) {
		getRepository().delete(id);
		
	}
	
	public long count(){ return getRepository().count() ; }

}
