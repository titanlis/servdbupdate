package ru.itm.servdbupdate.entity.tables.tire;

import ru.itm.servdbupdate.entity.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tire_storage", schema = "tire")
public final class TireStorage extends AbstractEntity {
	private String name;
	private String description;
	
	@Column(name = "max_storage")
	private Integer maxStorage;
}
