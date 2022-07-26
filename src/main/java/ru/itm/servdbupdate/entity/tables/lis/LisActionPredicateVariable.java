package ru.itm.servdbupdate.entity.tables.lis;

import ru.itm.servdbupdate.entity.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "lis_action_predicate_varible", schema = "lis")
public class LisActionPredicateVariable extends AbstractEntity {

	@Column(name = "lis_predicate_varible_FK")
	private int lisPredicateVariableFK;
	
	@Column(name = "lis_action_varible_FK")
	private int lisActionVariableFK;

	public LisActionPredicateVariable() {
	}
}
