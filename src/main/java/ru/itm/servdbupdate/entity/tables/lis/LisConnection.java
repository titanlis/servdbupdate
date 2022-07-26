package ru.itm.servdbupdate.entity.tables.lis;

import ru.itm.servdbupdate.entity.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "lis_connection", schema = "lis")
public class LisConnection extends AbstractEntity {

	@Column(name = "predicate_FK")
	private Long lisPredicateId;
	
	@Column(name = "action_result_FK")
	private Long actionResultId;
	
	@Column(name = "action_precond_FK")
	private Long actionPrecondId;
	
	
	public LisConnection() {
	}
}
