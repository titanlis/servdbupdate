package ru.itm.servdbupdate.repository.status;

import org.springframework.stereotype.Repository;
import ru.itm.servdbupdate.entity.tables.status.StatusGroupRelation;
import ru.itm.servdbupdate.repository.CommonRepository;

@Repository
public interface StatusGroupRelationRepository extends CommonRepository<StatusGroupRelation> {
}
