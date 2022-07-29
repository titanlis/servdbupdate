package ru.itm.servdbupdate.repository.drilling;

import org.springframework.stereotype.Repository;
import ru.itm.servdbupdate.entity.tables.drilling.Hole;
import ru.itm.servdbupdate.repository.CommonRepository;

@Repository
public interface HoleRepository extends CommonRepository<Hole> {
}
