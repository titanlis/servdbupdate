package ru.itm.servdbupdate.repository.trans;

import ru.itm.servdbupdate.entity.tables.trans.TransKeysCycle;
import ru.itm.servdbupdate.repository.CommonRepository;

public interface TransKeysCycleRepository  extends CommonRepository<TransKeysCycle> {
    Long countByIdTransCycleAndIdTransSensorAndSensorDataTypeId(Long idTransCycle, Long idTransSensor, Long sensorDataTypeId);
}
