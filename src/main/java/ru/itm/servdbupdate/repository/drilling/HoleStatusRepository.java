package ru.itm.servdbupdate.repository.drilling;

import org.springframework.stereotype.Repository;
import ru.itm.servdbupdate.entity.tables.drilling.HoleStatus;
import ru.itm.servdbupdate.repository.CommonRepository;

@Repository
public interface HoleStatusRepository extends CommonRepository<HoleStatus> {
}
