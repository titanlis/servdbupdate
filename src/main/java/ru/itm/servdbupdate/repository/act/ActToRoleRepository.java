package ru.itm.servdbupdate.repository.act;

import org.springframework.stereotype.Repository;
import ru.itm.servdbupdate.entity.tables.act.ActToRole;
import ru.itm.servdbupdate.repository.CommonRepository;

@Repository
public interface ActToRoleRepository extends CommonRepository<ActToRole> {
}
