
package com.td.bbwp.web.action.wf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.witchcraft.base.spring.BaseController;
import org.witchcraft.base.spring.BaseService;

import com.td.bbwp.service.wf.CaseInstanceService;
import com.td.bbwp.wf.CaseInstance;

@RestController
@RequestMapping("/rest/caseInstances")
public class CaseInstanceController extends BaseController<CaseInstance> {

	@Autowired
	private CaseInstanceService caseInstanceService;

	@Override
	public BaseService<CaseInstance> getBaseService() {
		return caseInstanceService;
	}

}
