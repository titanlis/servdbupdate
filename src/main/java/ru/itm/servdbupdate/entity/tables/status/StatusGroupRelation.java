package ru.itm.servdbupdate.entity.tables.status;

import ru.itm.servdbupdate.entity.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "status_group_rel", schema = "status")
public final class StatusGroupRelation extends AbstractEntity {
	@Column(name = "status_id")
	private Long statusId;
	
	@Column(name = "status_group_id")
	private Long statusGroupId;

	public StatusGroupRelation() {}	
}
