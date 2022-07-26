package ru.itm.servdbupdate.repository.config;

import org.springframework.stereotype.Repository;
import ru.itm.servdbupdate.entity.tables.config.ValuesData;
import ru.itm.servdbupdate.repository.CommonRepository;
@Repository
public interface ValuesDataRepository  extends CommonRepository<ValuesData> {
}