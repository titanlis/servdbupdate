package ru.itm.servdbupdate.repository.trans;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itm.servdbupdate.entity.tables.trans.TransCoord;
import ru.itm.servdbupdate.repository.CommonRepository;

import java.util.Calendar;

@Repository
public interface TransCoordRepository extends CommonRepository<TransCoord> {
    Integer countAllByEquipIdAndEquipTime(Long equip_id, Calendar equip_time);
}