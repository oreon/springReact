package org.witchcraft.base.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.hornetq.core.journal.Journal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.witchcraft.base.entity.BaseEntity;

public abstract class BaseController<T extends BaseEntity> {

	public abstract BaseService<T> getBaseService();

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<T> findAll() {
		//List<T> lstRecords = new ArrayList<>();
		
		Iterable<T> records = getBaseService().findAll();
		return StreamSupport.stream(records.spliterator(), false).collect(Collectors.toList());
		
//		try (Stream<T> records = getBaseService().findAll()) {
//			records.forEach(x -> lstRecords.add(x));
//			return lstRecords;
//		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity findOne(@PathVariable("id") Long id) {
		return getBaseService().findOne(id).map(x -> new ResponseEntity(x, HttpStatus.OK))
				.orElse(new ResponseEntity("No record found for ID " + id, HttpStatus.NOT_FOUND));
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity create(@RequestBody T t) {
		getBaseService().save(t);
		return new ResponseEntity(t, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		getBaseService().delete(id);
		return new ResponseEntity(id, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	// @SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody T t) throws Exception {

		T current = getBaseService().findOne(id).orElseGet(() -> null);

		if (null == current) {
			return new ResponseEntity("No record found for ID " + id, HttpStatus.NOT_FOUND);
		}

		t.setVersion(current.getVersion());

		// BeanUtils.copyProperties(current, t);

		T saved = getBaseService().save(t);

		return new ResponseEntity(saved, HttpStatus.OK);
	}

}

/*
 * 
 * 
 * return caseDefinitionService.findOne(id).map(x -> { try { //
 * caseDefinition.setTaskDefinitions(new // ArrayList<TaskDefinition>()); //
 * caseDefinition.getTaskDefinitions().stream().forEach(y -> { //
 * y.setCaseDefinition(caseDefinition); // y.getFields().forEach(z ->
 * z.setTaskDefinition(y)); // });
 * 
 * BeanUtils.copyProperties(x, caseDefinition); x.getTaskDefinitions().clear();
 * x.getTaskDefinitions().addAll(caseDefinition.getTaskDefinitions()); } catch
 * (Exception e) { // h block throw new
 * RuntimeException("error copying to destination"); } x =
 * getBaseService().save(x); return new ResponseEntity(x, HttpStatus.OK);
 * }).orElse(new ResponseEntity("No CaseDefinition found for ID " + id,
 * HttpStatus.NOT_FOUND));
 * 
 * // caseDefinition = //
 * caseDefinitionService.save(caseDefinition);//caseDefinitionDAO.update(id, //
 * caseDefinition); // // if (null == caseDefinition) { // return new
 * ResponseEntity("No CaseDefinition found for ID " + id, //
 * HttpStatus.NOT_FOUND); // }
 * 
 * // return new ResponseEntity(caseDefinition, HttpStatus.OK);
 * 
 */
