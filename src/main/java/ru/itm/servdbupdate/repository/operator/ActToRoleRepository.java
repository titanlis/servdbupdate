package ru.itm.servdbupdate.repository.operator;

import org.springframework.stereotype.Repository;
import ru.itm.servdbupdate.entity.tables.operator.ActToRole;
import ru.itm.servdbupdate.repository.CommonRepository;

@Repository
public interface ActToRoleRepository extends CommonRepository<ActToRole> {
}
