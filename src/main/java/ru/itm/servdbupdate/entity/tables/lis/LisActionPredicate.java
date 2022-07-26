package ru.itm.servdbupdate.entity.tables.lis;

import ru.itm.servdbupdate.entity.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "lis_action_predicate", schema = "lis")
public class LisActionPredicate extends AbstractEntity {

	@Column(name = "precond_result")
	private boolean precondResult;
	
	@Column(name = "lis_action_FK")
	private Long lisActionId;
		
	@Column(name = "lis_predicate_FK")
	private Long predicateId;
	
	
	public LisActionPredicate() {
	}
}
