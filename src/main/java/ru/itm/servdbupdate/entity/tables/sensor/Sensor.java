package ru.itm.servdbupdate.entity.tables.sensor;

import ru.itm.servdbupdate.entity.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sensors", schema = "sensor")
public final class Sensor extends AbstractEntity {
	private Long sensor_data_type_id;
	private Long equip_id;
	private boolean is_server;
	private String ip;
	private String port;
	private String pin_name;
	private Integer type_sens;
	private Integer time_read;
	private Integer timeout_sens = 10;
	private Double min_sens;
	private Double max_sens;
	private Integer diff;
	private boolean enable_sens;

	public Sensor() {}


	@Override
	public String toString() {
		return "sensor.sensors{" +
				"id=" + id +
				", sensor_data_type_id=" + sensor_data_type_id +
				", equip_id=" + equip_id +
				", is_server=" + is_server +
				", ip=" + ip +
				", port=" + port +
				", pin_name=" + pin_name +
				", type_sens=" + type_sens +
				", time_read=" + time_read +
				", min_sens=" + min_sens +
				", diff=" + diff +
				", enable_sens=" + enable_sens +
				'}';
	}

	public String toStringShow() {
		return this.toString();
	}

	public Long getSensor_data_type_id() {
		return sensor_data_type_id;
	}

	public void setSensor_data_type_id(Long sensor_data_type_id) {
		this.sensor_data_type_id = sensor_data_type_id;
	}

	public Long getEquip_id() {
		return equip_id;
	}

	public void setEquip_id(Long equip_id) {
		this.equip_id = equip_id;
	}

	public boolean isIs_server() {
		return is_server;
	}

	public void setIs_server(boolean is_server) {
		this.is_server = is_server;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getPin_name() {
		return pin_name;
	}

	public void setPin_name(String pin_name) {
		this.pin_name = pin_name;
	}

	public Integer getType_sens() {
		return type_sens;
	}

	public void setType_sens(Integer type_sens) {
		this.type_sens = type_sens;
	}

	public Integer getTime_read() {
		return time_read;
	}

	public void setTime_read(Integer time_read) {
		this.time_read = time_read;
	}

	public Integer getTimeout_sens() {
		return timeout_sens;
	}

	public void setTimeout_sens(Integer timeout_sens) {
		this.timeout_sens = timeout_sens;
	}

	public Double getMin_sens() {
		return min_sens;
	}

	public void setMin_sens(Double min_sens) {
		this.min_sens = min_sens;
	}

	public Double getMax_sens() {
		return max_sens;
	}

	public void setMax_sens(Double max_sens) {
		this.max_sens = max_sens;
	}

	public Integer getDiff() {
		return diff;
	}

	public void setDiff(Integer diff) {
		this.diff = diff;
	}

	public boolean isEnable_sens() {
		return enable_sens;
	}

	public void setEnable_sens(boolean enable_sens) {
		this.enable_sens = enable_sens;
	}
}
