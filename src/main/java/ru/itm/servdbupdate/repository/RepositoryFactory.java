package ru.itm.servdbupdate.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itm.servdbupdate.repository.config.ValuesDataRepository;
import ru.itm.servdbupdate.repository.dispatcher.DispatcherRepository;
import ru.itm.servdbupdate.repository.drilling.HoleRepository;
import ru.itm.servdbupdate.repository.drilling.HoleStatusRepository;
import ru.itm.servdbupdate.repository.equipment.*;
import ru.itm.servdbupdate.repository.location.LocationRepository;
import ru.itm.servdbupdate.repository.operator.ActRepository;
import ru.itm.servdbupdate.repository.operator.ActToRoleRepository;
import ru.itm.servdbupdate.repository.operator.RoleRepository;

@Component
public class RepositoryFactory {
    private static ActRepository actRepository;
    private static ActToRoleRepository actToRoleRepository;
    private static RoleRepository roleRepository;
    private static ValuesDataRepository valuesDataRepository;
    private static DispatcherRepository dispatcherRepository;
    private static HoleRepository holeRepository;
    private static HoleStatusRepository holeStatusRepository;
    private static EquipmentRepository equipmentRepository;
    private static LocationRepository locationRepository;
    private static EquipmentDrillRepository equipmentDrillRepository;
    private static EquipmentHaulRepository equipmentHaulRepository;
    private static EquipmentLoadRepository equipmentLoadRepository;
    private static EquipmentTypeRepository equipmentTypeRepository;
    @Autowired
    public void setEquipmentTypeRepository(EquipmentTypeRepository equipmentTypeRepository) {
        RepositoryFactory.equipmentTypeRepository = equipmentTypeRepository;
    }

    @Autowired
    public void setEquipmentLoadRepository(EquipmentLoadRepository equipmentLoadRepository) {
        RepositoryFactory.equipmentLoadRepository = equipmentLoadRepository;
    }

    @Autowired
    public void setEquipmentHaulRepository(EquipmentHaulRepository equipmentHaulRepository) {
        RepositoryFactory.equipmentHaulRepository = equipmentHaulRepository;
    }

    @Autowired
    public void setEquipmentDrillRepository(EquipmentDrillRepository equipmentDrillRepository) {
        RepositoryFactory.equipmentDrillRepository = equipmentDrillRepository;
    }

    @Autowired
    public void setActRepository(ActRepository actRepository) {
        RepositoryFactory.actRepository = actRepository;
    }

    @Autowired
    public void setActToRoleRepository(ActToRoleRepository actToRoleRepository) {
        RepositoryFactory.actToRoleRepository = actToRoleRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        RepositoryFactory.roleRepository = roleRepository;
    }

    @Autowired
    public void setValuesDataRepository(ValuesDataRepository valuesDataRepository) {
        RepositoryFactory.valuesDataRepository = valuesDataRepository;
    }

    @Autowired
    public void setDispatcherRepository(DispatcherRepository dispatcherRepository) {
        RepositoryFactory.dispatcherRepository = dispatcherRepository;
    }

    @Autowired
    public void setHoleRepository(HoleRepository holeRepository) {
        RepositoryFactory.holeRepository = holeRepository;
    }

    @Autowired
    public void setHoleStatusRepository(HoleStatusRepository holeStatusRepository) {
        RepositoryFactory.holeStatusRepository = holeStatusRepository;
    }

    @Autowired
    public void setEquipmentRepository(EquipmentRepository equipmentRepository) {
        RepositoryFactory.equipmentRepository = equipmentRepository;
    }

    @Autowired
    public void setLocationRepository(LocationRepository locationRepository) {
        RepositoryFactory.locationRepository = locationRepository;
    }


    public static CommonRepository getRepo(String tableName){

        switch (tableName.toLowerCase()){
            case "acts" -> {
                return actRepository;
            }
            case "acts_to_roles" -> {
                return actToRoleRepository;
            }
            case "roles" -> {
                return roleRepository;
            }
            case "values_data" -> {
                return valuesDataRepository;
            }
            case "dispatcher" -> {
                return dispatcherRepository;
            }
            case "location" -> {
                return locationRepository;
            }
            case "holes" -> {
                return holeRepository;
            }
            case "hole_status" -> {
                return holeStatusRepository;
            }
            case "equipment" -> {
                return equipmentRepository;
            }
            case "equipment_drill" -> {
                return equipmentDrillRepository;
            }
            case "equipment_haul" -> {
                return equipmentHaulRepository;
            }
            case "equipment_load" -> {
                return equipmentLoadRepository;
            }
            case "equip_type" -> {
                return equipmentTypeRepository;
            }

            default -> { return null; }
        }
    }

}
