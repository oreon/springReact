
/**
 * This file is created by Witchcraftmda only once .
 * It is OK to make changes as they will not be overwritten by subseuent re runs of the generator.
 */

package com.td.bbwp.scoringstrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.witchcraft.base.entity.BaseEntity;

public interface IScoringMethod {

	public Integer score(com.td.bbwp.commerce.CustomerOrder answeredQuesionnaire);

}
