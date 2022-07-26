package ru.itm.servdbupdate.entity.tables.lis;

import ru.itm.servdbupdate.entity.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "lis_action", schema = "lis")
public class LisAction extends AbstractEntity {

	private String name;
	
	@Column(columnDefinition = "text")
	private String description;

	public LisAction(){}
}
