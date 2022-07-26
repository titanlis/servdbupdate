package ru.itm.servdbupdate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import ru.itm.servdbupdate.entity.AbstractEntity;
import ru.itm.servdbupdate.serivce.CommonService;

public abstract class AbstractController<E extends AbstractEntity, S extends CommonService<E>>
        implements CommonController<E> {

    private final S service;

    @Autowired
    protected AbstractController(S service) {
        this.service = service;
    }

//    @Override
//    public ResponseEntity<E> save(@RequestBody E entity) {
//        return service.save(entity).map(ResponseEntity::ok)
//                .orElseThrow(() -> new SampleException(
//                        String.format(ErrorType.ENTITY_NOT_SAVED.getDescription(), entity.toString())
//                ));
//    }

//другие методы
}