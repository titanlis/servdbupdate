package ru.itm.servdbupdate.repository.operator;

import org.springframework.stereotype.Repository;
import ru.itm.servdbupdate.entity.tables.operator.Operator;
import ru.itm.servdbupdate.repository.CommonRepository;

@Repository
public interface OperatorRepository extends CommonRepository<Operator> {
}
