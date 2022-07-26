package ru.itm.servdbupdate.serivce.equipment;

import org.springframework.stereotype.Service;
import ru.itm.servdbupdate.entity.tables.equipment.Equipment;
import ru.itm.servdbupdate.repository.equipment.EquipmentRepository;
import ru.itm.servdbupdate.serivce.AbstractService;

@Service
public class EquipmentService extends AbstractService<Equipment, EquipmentRepository> {

    public EquipmentService(EquipmentRepository repository) {
        super(repository);
    }
}
