package ru.itm.servdbupdate.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ru.itm.servdbupdate.entity.AbstractEntity;

@NoRepositoryBean
public interface CommonRepository<E extends AbstractEntity> extends CrudRepository<E, Long> {
}