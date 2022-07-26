package ru.itm.servdbupdate.entity.tables.sensor;

import ru.itm.servdbupdate.entity.AbstractEntity;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sensor_data_types", schema = "sensor")
public final class SensorDataType extends AbstractEntity {
	private String name;
	public SensorDataType(){}
}
