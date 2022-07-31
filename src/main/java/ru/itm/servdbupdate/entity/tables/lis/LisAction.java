package ru.itm.servdbupdate.entity.tables.lis;

import ru.itm.servdbupdate.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "lis_action", schema = "lis")
public class LisAction extends AbstractEntity {

	private String name;
	private String description;

	public LisAction(){}

	public String toStringShow() {
		return "ActionGroup [id=" + id + ", name=" + name + ", text=" + description + "]";
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
