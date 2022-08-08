package ru.itm.servdbupdate.entity.tables.lis;

import ru.itm.servdbupdate.entity.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "action_variable_source", schema = "lis")
public class ActionVariableSource extends AbstractEntity {

	private String name;
	private Long type;

	public ActionVariableSource(){}

	public String toStringShow() {
		return "action_variable_source [id=" + id + ", name=" + name + ", type=" + type + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}
}
