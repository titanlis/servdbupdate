package ru.itm.servdbupdate.repository.dispatcher;

import org.springframework.stereotype.Repository;
import ru.itm.servdbupdate.entity.tables.dispatcher.Dispatcher;
import ru.itm.servdbupdate.repository.CommonRepository;

@Repository
public interface DispatcherRepository extends CommonRepository<Dispatcher> {
}
