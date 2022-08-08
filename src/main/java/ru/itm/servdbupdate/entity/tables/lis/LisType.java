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

	@Override
	public String toString() {
		return "LisType{" +
				"id=" + id +
				", name=" + name +
				", description=" + description +
				'}';
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}

