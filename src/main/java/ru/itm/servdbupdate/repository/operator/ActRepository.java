package ru.itm.servdbupdate.repository.operator;

import org.springframework.stereotype.Repository;
import ru.itm.servdbupdate.entity.tables.operator.Act;
import ru.itm.servdbupdate.repository.CommonRepository;

@Repository
public interface ActRepository extends CommonRepository<Act> {
}
