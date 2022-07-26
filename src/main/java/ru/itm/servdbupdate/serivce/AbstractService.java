package ru.itm.servdbupdate.serivce;

import org.springframework.beans.factory.annotation.Autowired;
import ru.itm.servdbupdate.entity.AbstractEntity;
import ru.itm.servdbupdate.repository.CommonRepository;

public abstract class AbstractService<E extends AbstractEntity, R extends CommonRepository<E>>
        implements CommonService<E> {

    protected final R repository;

    @Autowired
    public AbstractService(R repository) {
        this.repository = repository;
    }

//другие методы, переопределённые из интерфейса
}