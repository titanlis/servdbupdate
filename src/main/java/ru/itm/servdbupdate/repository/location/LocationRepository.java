package ru.itm.servdbupdate.repository.location;

import org.springframework.stereotype.Repository;
import ru.itm.servdbupdate.entity.tables.location.Location;
import ru.itm.servdbupdate.repository.CommonRepository;

@Repository
public interface LocationRepository extends CommonRepository<Location> {
}

