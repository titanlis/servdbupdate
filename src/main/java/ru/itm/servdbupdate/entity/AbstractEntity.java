/**
 * @file AbstractEntity.java
 * Абстрактная сущность
 */
package ru.itm.servdbupdate.entity;

import javax.persistence.MappedSuperclass;
import javax.persistence.*;

/**
 * @class AbstractEntity абстрактный класс для всех сущностей
 * От этого класса будут наследоваться все сущности (таблицы).
 */
@MappedSuperclass   ///< Hibernate тоже должен узнать, что это абстракция.
public abstract class AbstractEntity{

    protected Long id;    ///< id есть у каждой сущности

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}