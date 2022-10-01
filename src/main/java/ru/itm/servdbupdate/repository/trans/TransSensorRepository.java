package ru.itm.servdbupdate.repository.trans;

import org.springframework.stereotype.Repository;
import ru.itm.servdbupdate.entity.tables.trans.TransSensor;
import ru.itm.servdbupdate.repository.CommonRepository;

import java.util.Calendar;
import java.util.List;

@Repository
public interface TransSensorRepository extends CommonRepository<TransSensor> {
    Integer countAllByEquipIdAndTimeRead(Long equip_id, Calendar time_read);
}