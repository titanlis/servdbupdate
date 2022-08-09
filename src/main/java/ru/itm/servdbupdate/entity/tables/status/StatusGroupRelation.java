package ru.itm.servdbupdate.entity.tables.status;

import ru.itm.servdbupdate.entity.AbstractEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "status_group_rel", schema = "status")
public final class StatusGroupRelation extends AbstractEntity {
	private Long status_id;
	private Long status_group_id;

	public StatusGroupRelation() {}

	@Override
	public String toString() {
		return "status.status_group_rel{" +
				"id=" + id +
				", status_id=" + status_id +
				", status_group_id=" + status_group_id +
				'}';
	}

	public String toStringShow() {
		return this.toString();
	}

	public Long getStatus_id() {
		return status_id;
	}

	public void setStatus_id(Long status_id) {
		this.status_id = status_id;
	}

	public Long getStatus_group_id() {
		return status_group_id;
	}

	public void setStatus_group_id(Long status_group_id) {
		this.status_group_id = status_group_id;
	}
}
