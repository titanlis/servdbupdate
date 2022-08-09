package ru.itm.servdbupdate.repository.sensor;

import org.springframework.stereotype.Repository;
import ru.itm.servdbupdate.entity.tables.sensor.SensorDataType;
import ru.itm.servdbupdate.repository.CommonRepository;

@Repository
public interface SensorDataTypeRepository extends CommonRepository<SensorDataType> {
}
