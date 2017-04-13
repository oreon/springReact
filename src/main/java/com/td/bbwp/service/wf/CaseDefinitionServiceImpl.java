
package com.td.bbwp.service.wf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.witchcraft.base.entity.BaseRepository;
import org.witchcraft.base.spring.BaseServiceImpl;

import com.td.bbwp.web.action.wf.CaseDefinitionRepository;
import com.td.bbwp.wf.CaseDefinition;

@Service
@Transactional
public class CaseDefinitionServiceImpl extends BaseServiceImpl<CaseDefinition> implements CaseDefinitionService {

	@Autowired
	private final CaseDefinitionRepository caseDefinitionRepository = null;

	@Override
	public BaseRepository<CaseDefinition> getRepository() {
		return caseDefinitionRepository;
	}
}
