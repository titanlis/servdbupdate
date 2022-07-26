package ru.itm.servdbupdate.entity.tables.lis;

import ru.itm.servdbupdate.entity.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "lis_point", schema = "lis")
public class LisPoint extends AbstractEntity {
	private double x;
	private double y;
	
	@Column(name = "number_point")
	private int number;
	
	@Column(name = "connection_id_FK")
	private Long connectionId;

	public LisPoint() {}
}
