package ru.itm.servdbupdate.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ru.itm.servdbupdate.entity.AbstractEntity;

/**
 * В этом репозитории мы задаём общие правила для всей цепочки: все сущности, участвующие в ней,
 * будут наследоваться от абстрактной.
 * Далее, для каждой сущности мы должны написать свой репозиторий-интерфейс, в котором обозначим,
 * с какой именно сущностью будет работать эта цепочка "репозиторий-контроллер"
 * @param <E> сущность
 */
@NoRepositoryBean
public interface CommonRepository<E extends AbstractEntity> extends CrudRepository<E, Long> {
}