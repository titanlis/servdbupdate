package ru.itm.servdbupdate.entity.tables.config;

import ru.itm.servdbupdate.entity.AbstractEntity;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "values_data", schema = "config")
public final class ValuesData extends AbstractEntity {
	private String name;
	private String value;
	private String description;

	public ValuesData(){}
}
