package ru.itm.servdbupdate.repository.trans;

import org.springframework.stereotype.Repository;
import ru.itm.servdbupdate.entity.tables.trans.TransNetwork;
import ru.itm.servdbupdate.repository.CommonRepository;

import java.util.Calendar;

@Repository
public interface TransNetworkRepository extends CommonRepository<TransNetwork> {
    Integer countAllByEquipIdAndEquipTime(Long equip_id, Calendar equipTime);
}
