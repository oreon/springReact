package com.td.bbwp.web.action.wf;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.witchcraft.base.spring.BaseService;

import com.td.bbwp.wf.CaseDefinition;

@RestController
public class CaseDefintionController extends BaseController<CaseDefinition>{
	
	@Autowired
	CaseDefinitionService caseDefintionService;

	@Override
	public BaseService<CaseDefinition> getBaseService() {
		// TODO Auto-generated method stub
		return caseDefintionService;
	}
	
	
	@Autowired
	private CaseDefinitionService caseDefinitionService;

	
	@GetMapping("/rest/caseDefintions")
	public List<CaseDefinition> getCaseDefinitions() {
		return StreamSupport.stream(caseDefinitionService.findAll().spliterator(), false).collect(Collectors.toList());
		
	}

	@GetMapping("/rest/caseDefintions/{id}")
	public ResponseEntity getCaseDefinition(@PathVariable("id") Long id) {

		CaseDefinition caseDefinition = caseDefinitionService.findOne(id);
		if (caseDefinition == null) {
			return new ResponseEntity("No CaseDefinition found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(caseDefinition, HttpStatus.OK);
	}

	@PostMapping("/rest/caseDefintions")
	public ResponseEntity createCaseDefinition(@RequestBody CaseDefinition caseDefinition) {
		caseDefinitionService.save(caseDefinition);
		return new ResponseEntity(caseDefinition, HttpStatus.OK);
	}

	@DeleteMapping("/rest/caseDefintions/{id}")
	public ResponseEntity deleteCaseDefinition(@PathVariable Long id) {

//		if (null == caseDefinitionService.delete(id)) {
//			return new ResponseEntity("No CaseDefinition found for ID " + id, HttpStatus.NOT_FOUND);
//		}
		caseDefinitionService.delete(id);
		return new ResponseEntity(id, HttpStatus.OK);

	}

	@PutMapping("/rest/caseDefintions/{id}")
	public ResponseEntity updateCaseDefinition(@PathVariable Long id, @RequestBody CaseDefinition caseDefinition) {
		

//		caseDefinition = caseDefinitionDAO.update(id, caseDefinition);
//
//		if (null == caseDefinition) {
//			return new ResponseEntity("No CaseDefinition found for ID " + id, HttpStatus.NOT_FOUND);
//		}

		return new ResponseEntity(caseDefinitionService.save(caseDefinition), HttpStatus.OK);
	}

	

}
