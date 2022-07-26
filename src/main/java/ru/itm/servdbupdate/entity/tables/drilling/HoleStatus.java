package ru.itm.servdbupdate.entity.tables.drilling;

import ru.itm.servdbupdate.entity.AbstractEntity;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "hole_status", schema = "drilling")
public class HoleStatus extends AbstractEntity {
	private String name;
	private String descr;
	private String color;

	public HoleStatus() {}
}
