package ru.itm.servdbupdate.entity.tables.status;

import ru.itm.servdbupdate.entity.AbstractEntity;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "status_group", schema = "status")
public final class StatusGroup extends AbstractEntity {
	private String name;
	public StatusGroup(){}
}
