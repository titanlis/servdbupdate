package ru.itm.servdbupdate.entity.tables.location;

import ru.itm.servdbupdate.entity.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "roads", schema = "location")
public class Road extends AbstractEntity {
	@Column(name = "loc_id")
	private Long locId;
	
	private Float timing;
	private Float lengths;
	private Float lvl;
	private Float angle;
	private boolean active;

	public Road(){}  
}
