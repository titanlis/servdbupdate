package ru.itm.servdbupdate.entity.tables.lis;

import ru.itm.servdbupdate.entity.AbstractEntity;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "lis_type", schema = "lis")
public class LisType extends AbstractEntity {
	private String name;
	private String description;

	public LisType(){}
}
