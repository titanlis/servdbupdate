package ru.itm.servdbupdate.entity.tables.status;

import ru.itm.servdbupdate.entity.AbstractEntity;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "statuses", schema = "status")
public class Statuses extends AbstractEntity {
	private String name;
	private String ext_id;

	public Statuses(){}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExt_id() {
		return ext_id;
	}

	public void setExt_id(String ext_id) {
		this.ext_id = ext_id;
	}

	@Override
	public String toString() {
		return "status.statuses{" +
				"id=" + id +
				", name=" + name +
				", ext_id=" + ext_id +
				'}';
	}

	public String toStringShow() {
		return this.toString();
	}
}
