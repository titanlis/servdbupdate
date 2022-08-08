package ru.itm.servdbupdate.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itm.servdbupdate.entity.tables.location.LocationType;
import ru.itm.servdbupdate.repository.config.ValuesDataRepository;
import ru.itm.servdbupdate.repository.dispatcher.DispatcherRepository;
import ru.itm.servdbupdate.repository.drilling.HoleRepository;
import ru.itm.servdbupdate.repository.drilling.HoleStatusRepository;
import ru.itm.servdbupdate.repository.equipment.*;
import ru.itm.servdbupdate.repository.lis.*;
import ru.itm.servdbupdate.repository.location.*;
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
    private static ActionGroupRepository actionGroupRepository;
    private static ActionVariableSourceRepository actionVariableSourceRepository;
    private static LisActionRepository lisActionRepository;
    private static LisActionPredicateRepository lisActionPredicateRepository;
    private static LisActionPredicateVariableRepository lisActionPredicateVariableRepository;
    private static LisActionSourceVariableRepository lisActionSourceVariableRepository;
    private static LisPredicateRepository lisPredicateRepository;
    private static LisSourceVariableRepository lisSourceVariableRepository;
    private static LisTypeRepository lisTypeRepository;
    private static LisActionVariableRepository lisActionVariableRepository;
    private static LisConnectionRepository lisConnectionRepository;
    private static LisPredicateVariableRepository lisPredicateVariableRepository;
    private static LisGroupRepository lisGroupRepository;
    private static LisPointRepository lisPointRepository;
    private static LisCheckFunctionRepository lisCheckFunctionRepository;
    private static CoordinateLocationRepository coordinateLocationRepository;
    private static LocationAutoStatusRepository locationAutoStatusRepository;
    private static LocationInheritanceRepository locationInheritanceRepository;
    private static LocationTypeRepository locationTypeRepository;
    private static RoadRepository roadRepository;
    private static SpeedLimitsRepository speedLimitsRepository;

    @Autowired
    public void setSpeedLimitsRepository(SpeedLimitsRepository speedLimitsRepository) {
        RepositoryFactory.speedLimitsRepository = speedLimitsRepository;
    }

    @Autowired
    public void setRoadRepository(RoadRepository roadRepository) {
        RepositoryFactory.roadRepository = roadRepository;
    }

    @Autowired
    public void setLocationTypeRepository(LocationTypeRepository locationTypeRepository) {
        RepositoryFactory.locationTypeRepository = locationTypeRepository;
    }

    @Autowired
    public void setLocationInheritanceRepository(LocationInheritanceRepository locationInheritanceRepository) {
        RepositoryFactory.locationInheritanceRepository = locationInheritanceRepository;
    }

    @Autowired
    public void setLocationAutoStatusRepository(LocationAutoStatusRepository locationAutoStatusRepository) {
        RepositoryFactory.locationAutoStatusRepository = locationAutoStatusRepository;
    }

    @Autowired
    public void setCoordinateLocationRepository(CoordinateLocationRepository coordinateLocationRepository) {
        RepositoryFactory.coordinateLocationRepository = coordinateLocationRepository;
    }

    @Autowired
    public void setLisCheckFunctionRepository(LisCheckFunctionRepository lisCheckFunctionRepository) {
        RepositoryFactory.lisCheckFunctionRepository = lisCheckFunctionRepository;
    }

    @Autowired
    public void setLisPointRepository(LisPointRepository lisPointRepository) {
        RepositoryFactory.lisPointRepository = lisPointRepository;
    }
    @Autowired
    public void setLisGroupRepository(LisGroupRepository lisGroupRepository) {
        RepositoryFactory.lisGroupRepository = lisGroupRepository;
    }

    @Autowired
    public void setLisPredicateVariableRepository(LisPredicateVariableRepository lisPredicateVariableRepository) {
        RepositoryFactory.lisPredicateVariableRepository = lisPredicateVariableRepository;
    }

    @Autowired
    public void setLisConnectionRepository(LisConnectionRepository lisConnectionRepository) {
        RepositoryFactory.lisConnectionRepository = lisConnectionRepository;
    }

    @Autowired
    public void setLisActionVariableRepository(LisActionVariableRepository lisActionVariableRepository) {
        RepositoryFactory.lisActionVariableRepository = lisActionVariableRepository;
    }

    @Autowired
    public void setLisTypeRepository(LisTypeRepository lisTypeRepository) {
        RepositoryFactory.lisTypeRepository = lisTypeRepository;
    }

    @Autowired
    public void setLisSourceVariableRepository(LisSourceVariableRepository lisSourceVariableRepository) {
        RepositoryFactory.lisSourceVariableRepository = lisSourceVariableRepository;
    }

    @Autowired
    public void setLisPredicateRepository(LisPredicateRepository lisPredicateRepository) {
        RepositoryFactory.lisPredicateRepository = lisPredicateRepository;
    }

    @Autowired
    public void setLisActionSourceVariableRepository(LisActionSourceVariableRepository lisActionSourceVariableRepository) {
        RepositoryFactory.lisActionSourceVariableRepository = lisActionSourceVariableRepository;
    }

    @Autowired
    public void setLisActionPredicateVariableRepository(LisActionPredicateVariableRepository lisActionPredicateVariableRepository) {
        RepositoryFactory.lisActionPredicateVariableRepository = lisActionPredicateVariableRepository;
    }

    @Autowired
    public void setLisActionPredicateRepository(LisActionPredicateRepository lisActionPredicateRepository) {
        RepositoryFactory.lisActionPredicateRepository = lisActionPredicateRepository;
    }

    @Autowired
    public void setLisActionRepository(LisActionRepository lisActionRepository) {
        RepositoryFactory.lisActionRepository = lisActionRepository;
    }

    @Autowired
    public void setActionVariableSourceRepository(ActionVariableSourceRepository actionVariableSourceRepository) {
        RepositoryFactory.actionVariableSourceRepository = actionVariableSourceRepository;
    }

    @Autowired
    public void setActionGroupRepository(ActionGroupRepository actionGroupRepository) {
        RepositoryFactory.actionGroupRepository = actionGroupRepository;
    }

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
            case "action_group" -> {
                return actionGroupRepository;
            }
            case "action_variable_source" -> {
                return actionVariableSourceRepository;
            }
            case "lis_action" -> {
                return lisActionRepository;
            }
            case "lis_action_predicate" -> {
                return lisActionPredicateRepository;
            }
            case "lis_action_predicate_variable" -> {
                return lisActionPredicateVariableRepository;
            }
            case "lis_action_source_variable" -> {
                return lisActionSourceVariableRepository;
            }
            case "lis_predicate" -> {
                return lisPredicateRepository;
            }
            case "lis_source_variable" -> {
                return lisSourceVariableRepository;
            }
            case "lis_type" -> {
                return lisTypeRepository;
            }
            case "lis_action_variable" -> {
                return lisActionVariableRepository;
            }
            case "lis_connection" -> {
                return lisConnectionRepository;
            }
            case "lis_predicate_variable" -> {
                return lisPredicateVariableRepository;
            }
            case "lis_group" -> {
                return lisGroupRepository;
            }
            case "lis_point" -> {
                return lisPointRepository;
            }
            case "lis_check_function" -> {
                return lisCheckFunctionRepository;
            }
            case "coord_loc" -> {
                return coordinateLocationRepository;
            }
            case "location" -> {
                return locationRepository;
            }
            case "location_auto_status" -> {
                return locationAutoStatusRepository;
            }
            case "loc_inheritance" -> {
                return locationInheritanceRepository;
            }
            case "location_type" -> {
                return locationTypeRepository;
            }
            case "roads" -> {
                return roadRepository;
            }
            case "speed_limits" -> {
                return speedLimitsRepository;
            }


            default -> { return null; }
        }
    }

}
