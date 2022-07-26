package ru.itm.servdbupdate.entity.tables.operator;

import ru.itm.servdbupdate.entity.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "operators", schema = "operator")
public class Operator extends AbstractEntity {
	private String tab;
	private String name;
	private String surname;
	private String middle;
	private String licence;
	private String expire;
	
	@Column(name = "role_id")
	private Long roleId;
	
	private boolean active;
	
	@Column(name = "rfid_id")
	private String rfidId;

	public Operator() {}

}
