package ru.itm.servdbupdate.repository.trans;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itm.servdbupdate.entity.AbstractEntity;
import ru.itm.servdbupdate.entity.tables.trans.TransFuel;
import ru.itm.servdbupdate.repository.CommonRepository;

import java.awt.desktop.AboutEvent;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransFuelRepository extends CommonRepository<TransFuel> {
     List<TransFuel> findAllByEquipId(Long equip_id);
     Integer countAllByEquipIdAndTimeRead(Long equip_id, Calendar time_read);
}
//public interface TransFuelRepository extends JpaRepository<TransFuel, Long> {
//    List<TransFuel> findAllByEquipId(Long equip_id);
//}
