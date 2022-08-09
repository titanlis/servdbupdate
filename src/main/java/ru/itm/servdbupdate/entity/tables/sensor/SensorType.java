package ru.itm.servdbupdate.entity.tables.sensor;

import ru.itm.servdbupdate.entity.AbstractEntity;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sensor_types", schema = "sensor")
public final class SensorType extends AbstractEntity {
	private String name;
	public SensorType(){}

	@Override
	public String toString() {
		return "sensor.sensor_types{" +
				"id=" + id +
				", name=" + name +
				'}';
	}

	public String toStringShow() {
		return this.toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
