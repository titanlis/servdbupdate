package ru.itm.servdbupdate.entity.tables.dispatcher;
 
import ru.itm.servdbupdate.entity.AbstractEntity;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Используется на бк для определения текущего диспетчера.
 * На сервере есть еще логин и пароль диспетчера, но на бк они не нужны.
 * Водитель просто теперь знает имя диспетцера.
 */
@Entity
@Table(name = "dispatcher", schema = "dispatcher")
public final class Dispatcher extends AbstractEntity {
	private String name;

	public Dispatcher() {}

	public String toStringShow() {
		return "dispatcher.dispatcher [id=" + id + ", name =\'" + name +"\']";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
