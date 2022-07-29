package ru.itm.servdbupdate.entity.tables.drilling;

import ru.itm.servdbupdate.entity.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "holes", schema = "drilling")
public class Hole extends AbstractEntity {
	private Long id_loc;
	private Integer number;
	private Double drilling_z;
	private Double azimuth;
	private Double drill_angle;
	private Double after_deep;
	private String description;

	public String toStringShow(){
		return "[id=" + id + ", id_loc=" + id_loc +", number=" + number + ", drilling_z=" + drilling_z
				+ ", azimuth=" + azimuth + ", drilling_angle=" + drill_angle + ", after_deep=" + after_deep
				+ ", description=\'" + description + "\']" ;
	}


	public Hole(){}

	public Long getId_loc() {
		return id_loc;
	}

	public void setId_loc(Long id_loc) {
		this.id_loc = id_loc;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Double getDrilling_z() {
		return drilling_z;
	}

	public void setDrilling_z(Double drilling_z) {
		this.drilling_z = drilling_z;
	}

	public Double getAzimuth() {
		return azimuth;
	}

	public void setAzimuth(Double azimuth) {
		this.azimuth = azimuth;
	}

	public Double getDrill_angle() {
		return drill_angle;
	}

	public void setDrill_angle(Double drill_angle) {
		this.drill_angle = drill_angle;
	}

	public Double getAfter_deep() {
		return after_deep;
	}

	public void setAfter_deep(Double after_deep) {
		this.after_deep = after_deep;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
