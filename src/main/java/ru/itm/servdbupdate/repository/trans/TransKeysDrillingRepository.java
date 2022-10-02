package ru.itm.servdbupdate.repository.trans;

import org.springframework.stereotype.Repository;
import ru.itm.servdbupdate.entity.tables.trans.TransKeysCycle;
import ru.itm.servdbupdate.entity.tables.trans.TransKeysDrilling;
import ru.itm.servdbupdate.repository.CommonRepository;

@Repository
public interface TransKeysDrillingRepository  extends CommonRepository<TransKeysDrilling> {
    Long countByIdHoleAndIdTransDrillingAndIdTransSensor(Long idHole, Long idTransDrilling, Long idTransSensor);
}
