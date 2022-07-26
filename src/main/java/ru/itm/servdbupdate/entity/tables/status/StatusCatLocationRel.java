package ru.itm.servdbupdate.entity.tables.status;

import ru.itm.servdbupdate.entity.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "status_cat_location_rel", schema = "status")
public final class StatusCatLocationRel extends AbstractEntity {
	@Column(name = "location_auto_status_id")
	private Long locationAutoStatusId;
	
	@Column(name = "location_type_id")
	private Long locationTypeId;
	
	@Column(name = "on_enter")
	private Boolean onEnter;
	
	@Column(name = "on_exit")
	private Boolean onExit;

	public StatusCatLocationRel() {}
}
