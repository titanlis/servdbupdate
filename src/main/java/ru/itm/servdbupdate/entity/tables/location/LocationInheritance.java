package ru.itm.servdbupdate.entity.tables.location;

import ru.itm.servdbupdate.entity.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "loc_inheritance", schema = "location")
public final class LocationInheritance extends AbstractEntity {
	@Column(name = "parrent_loc_id")
	private Long parrentLocId;
	
	@Column(name = "child_loc_id")
	private Long childLocId;

	public LocationInheritance(){}
}
