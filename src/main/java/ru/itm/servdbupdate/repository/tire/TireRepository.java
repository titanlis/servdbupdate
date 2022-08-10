package ru.itm.servdbupdate.repository.tire;

import org.springframework.stereotype.Repository;
import ru.itm.servdbupdate.entity.tables.tire.Tire;
import ru.itm.servdbupdate.repository.CommonRepository;

@Repository
public interface TireRepository extends CommonRepository<Tire> {
}
