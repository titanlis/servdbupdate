package ru.itm.servdbupdate.entity.tables.equipment;

import lombok.Data;
import ru.itm.servdbupdate.entity.AbstractEntity;
import javax.persistence.*;

@Entity
@Table(name="equip_type", schema = "equipment")
public class EquipmentType extends AbstractEntity {

	@Enumerated(EnumType.STRING)
	private EQUIPMENT_TYPE name;
	private String descr;
	private boolean active;

	public EquipmentType() {

	}

	public String toStringShow() {
		return "equipment.equip_type [id=" + id + ", name=" + name + ", descr=" + descr
				+ ", active=" + active + "]";
	}

	public EQUIPMENT_TYPE getName() {
		return name;
	}

	public void setName(EQUIPMENT_TYPE name) {
		this.name = name;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}