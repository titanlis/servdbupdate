package ru.itm.servdbupdate.entity.tables.dispatcher;
 
import ru.itm.servdbupdate.entity.AbstractEntity;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "dispatcher", schema = "dispatcher")
public final class Dispatcher extends AbstractEntity {
	private String name;

	public Dispatcher() {}
}
