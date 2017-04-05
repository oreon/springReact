
package com.td.bbwp.web.action.wf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.witchcraft.base.spring.BaseService;
import org.witchcraft.base.spring.BaseController;

import com.td.bbwp.service.wf.statusService;
import com.td.bbwp.wf.status;

@RestController
@RequestMapping("/rest/statuss")
public class statusController extends BaseController<status> {

	@Autowired
	private statusService statusService;

	@Override
	public BaseService<status> getBaseService() {
		return statusService;
	}

}
