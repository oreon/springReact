
package com.td.bbwp.web.action.wf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.witchcraft.base.spring.BaseController;
import org.witchcraft.base.spring.BaseService;

import com.td.bbwp.service.wf.CaseDefinitionService;
import com.td.bbwp.wf.CaseDefinition;

@RestController
@RequestMapping("/rest/caseDefinitions")
public class CaseDefinitionController extends BaseController<CaseDefinition> {

	@Autowired
	private CaseDefinitionService caseDefinitionService;

	@Override
	public BaseService<CaseDefinition> getBaseService() {
		return caseDefinitionService;
	}

}
