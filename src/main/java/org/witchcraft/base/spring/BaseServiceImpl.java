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
		// TODO Auto-generated method stub
		return getRepository().save(t);
	}

	//@Override
	public Optional<T> findOne(Long id) {
		// TODO Auto-generated method stub
		return  getRepository().findById(id);
	}

	//@Override
	public Stream<T> findAll() {
		// TODO Auto-generated method stub
		return getRepository().allEntities();
	}

	//@Override
	public void delete(Long id) {
		getRepository().delete(id);
		// TODO Auto-generated method stub
	}
	
	public long count(){ return getRepository().count() ; }

}
