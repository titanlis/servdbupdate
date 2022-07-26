package ru.itm.servdbupdate.entity.tables.material;

import ru.itm.servdbupdate.entity.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="material", schema="material")
public class Material extends AbstractEntity {

	@Column
	private String name;

	@Column(name = "material_type_id")
	private Long materialTypeId;

	@Column
	private String color;

	@Column
	private Boolean active;

	public Material() {}
}
