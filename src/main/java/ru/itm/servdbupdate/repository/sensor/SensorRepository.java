package ru.itm.servdbupdate.repository.sensor;

import org.springframework.stereotype.Repository;
import ru.itm.servdbupdate.entity.tables.sensor.Sensor;
import ru.itm.servdbupdate.repository.CommonRepository;

@Repository
public interface SensorRepository extends CommonRepository<Sensor> {
}
