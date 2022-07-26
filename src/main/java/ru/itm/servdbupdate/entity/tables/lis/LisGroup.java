package ru.itm.servdbupdate.entity.tables.lis;

import ru.itm.servdbupdate.entity.AbstractEntity;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "lis_group", schema = "lis")
public class LisGroup extends AbstractEntity {
	private String name;

	public LisGroup(){}
}
