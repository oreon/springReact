
/**
 * This file is created by Witchcraftmda only once .
 * It is OK to make changes as they will not be overwritten by subseuent re runs of the generator.
 */

package com.td.bbwp.wf;

import javax.persistence.*;
import org.witchcraft.base.entity.FileAttachment;
import org.witchcraft.base.entity.BaseEntity;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.IndexColumn;

import javax.validation.constraints.*;

import java.math.BigDecimal;

import java.util.Optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import org.witchcraft.base.entity.BaseEntity;

@Entity
@Table(name = "STATUS")

@Inheritance(strategy = InheritanceType.JOINED) //inherit

public class status extends statusBase implements java.io.Serializable {
	private static final long serialVersionUID = -2070246773L;
}
