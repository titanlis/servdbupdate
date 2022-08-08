package ru.itm.servdbupdate.entity.tables.lis;

import ru.itm.servdbupdate.entity.AbstractEntity;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "lis_group", schema = "lis")
public class LisGroup extends AbstractEntity {
	private String name;

	public LisGroup(){}

	@Override
	public String toString() {
		return "lis.lis_group{" +
				"id=" + id +
				", name=" + name + '}';
	}

	public String toStringShow() {
		return this.toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
