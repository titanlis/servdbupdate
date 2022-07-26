package ru.itm.servdbupdate.entity.tables.operator;

import ru.itm.servdbupdate.entity.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "operator_on_equip_type", schema = "operator")
public final class OperatorOnEquipType extends AbstractEntity {
	@Column(name = "operator_id")
	private Long operatorId;
	
	@Column(name = "equip_type_id")
	private Long equipTypeId;
}
