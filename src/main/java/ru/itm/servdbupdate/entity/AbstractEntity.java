/**
 * @file AbstractEntity.java
 * Абстрактная сущность
 */
package ru.itm.servdbupdate.entity;

import ru.itm.servdbupdate.entity.tables.trans.Trans;

import javax.persistence.MappedSuperclass;
import javax.persistence.*;
import java.util.Calendar;

/**
 * @class AbstractEntity абстрактный класс для всех сущностей
 * От этого класса будут наследоваться все сущности (таблицы).
 */
@MappedSuperclass   ///< Hibernate тоже должен узнать, что это абстракция.
public abstract class AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;    ///< id есть у каждой сущности

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String toStringShow(){return toString();}

}