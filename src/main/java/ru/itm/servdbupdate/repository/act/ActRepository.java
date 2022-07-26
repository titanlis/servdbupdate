package ru.itm.servdbupdate.repository.act;

import org.springframework.stereotype.Repository;
import ru.itm.servdbupdate.entity.tables.act.Act;
import ru.itm.servdbupdate.repository.CommonRepository;

@Repository
public interface ActRepository extends CommonRepository<Act> {
}
