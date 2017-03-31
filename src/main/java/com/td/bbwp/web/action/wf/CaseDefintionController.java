package com.td.bbwp.web.action.wf;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.witchcraft.base.spring.BaseService;

import com.td.bbwp.wf.CaseDefinition;
import com.td.bbwp.wf.TaskDefinition;

@RestController
@RequestMapping("/rest/caseDefinitions")
public class CaseDefintionController extends BaseController<CaseDefinition> {

	@Autowired
	CaseDefinitionService caseDefintionService;

	@Autowired
	CaseDefinitionRepository caseDefinitionRepository;

	@Override
	public BaseService<CaseDefinition> getBaseService() {
		// TODO Auto-generated method stub
		return caseDefintionService;
	}

	@Autowired
	private CaseDefinitionService caseDefinitionService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<CaseDefinition> getCaseDefinitions() {
		return StreamSupport.stream(caseDefinitionService.findAll().spliterator(), false).collect(Collectors.toList());

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity getCaseDefinition(@PathVariable("id") Long id) {
		return caseDefinitionService.findOne(id).map(x -> new ResponseEntity(x, HttpStatus.OK))
				.orElse(new ResponseEntity("No CaseDefinition found for ID " + id, HttpStatus.NOT_FOUND));
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity createCaseDefinition(@RequestBody CaseDefinition caseDefinition) {
		caseDefinitionRepository.save(caseDefinition);
		return new ResponseEntity(caseDefinition, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCaseDefinition(@PathVariable Long id) {

		// if (null == caseDefinitionService.delete(id)) {
		// return new ResponseEntity("No CaseDefinition found for ID " + id,
		// HttpStatus.NOT_FOUND);
		// }
		caseDefinitionService.delete(id);
		return new ResponseEntity(id, HttpStatus.OK);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	// @SuppressWarnings({ "unchecked", "rawtypes" })
	public ResponseEntity<?> updateCaseDefinition(@PathVariable Long id, @RequestBody CaseDefinition caseDefinition) {

		return caseDefinitionService.findOne(id).map(x -> {
			try {
				// caseDefinition.setTaskDefinitions(new
				// ArrayList<TaskDefinition>());
//				caseDefinition.getTaskDefinitions().stream().forEach(y -> {
//					y.setCaseDefinition(caseDefinition);
//					y.getFields().forEach(z -> z.setTaskDefinition(y));
//				});

				BeanUtils.copyProperties(x, caseDefinition);
				x.getTaskDefinitions().clear();
				x.getTaskDefinitions().addAll(caseDefinition.getTaskDefinitions());
			} catch (Exception e) {
				// h block
				throw new RuntimeException("error copying to destination");
			}
			x = caseDefinitionRepository.save(x);
			return new ResponseEntity(x, HttpStatus.OK);
		}).orElse(new ResponseEntity("No CaseDefinition found for ID " + id, HttpStatus.NOT_FOUND));

		// caseDefinition =
		// caseDefinitionService.save(caseDefinition);//caseDefinitionDAO.update(id,
		// caseDefinition);
		//
		// if (null == caseDefinition) {
		// return new ResponseEntity("No CaseDefinition found for ID " + id,
		// HttpStatus.NOT_FOUND);
		// }

		// return new ResponseEntity(caseDefinition, HttpStatus.OK);
	}

}
