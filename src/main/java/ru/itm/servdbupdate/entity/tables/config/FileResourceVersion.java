package ru.itm.servdbupdate.entity.tables.config;

import ru.itm.servdbupdate.entity.AbstractEntity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Calendar;

@Entity
@Table(name = "file_resource_version", schema = "config")
public class FileResourceVersion extends AbstractEntity {

	private String resource_name;
	private String hash;
	private String version;
	private Calendar date;

	public FileResourceVersion() {}

}
