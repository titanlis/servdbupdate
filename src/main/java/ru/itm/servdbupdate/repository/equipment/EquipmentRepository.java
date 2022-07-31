package ru.itm.servdbupdate.repository.equipment;

import org.springframework.stereotype.Repository;
import ru.itm.servdbupdate.entity.tables.equipment.Equipment;
import ru.itm.servdbupdate.repository.CommonRepository;

@Repository
public interface EquipmentRepository extends CommonRepository<Equipment> {
}
