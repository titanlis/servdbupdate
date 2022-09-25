/**
 * @file KryoFactory.java
 * Фабричный класс для оздания объекта Kryo
 */
package ru.itm.servdbupdate.kryo;


import com.esotericsoftware.kryo.kryo5.Kryo;
import ru.itm.servdbupdate.entity.tables.config.ValuesData;
import ru.itm.servdbupdate.entity.tables.dispatcher.Dispatcher;
import ru.itm.servdbupdate.entity.tables.drilling.Hole;
import ru.itm.servdbupdate.entity.tables.drilling.HoleStatus;
import ru.itm.servdbupdate.entity.tables.equipment.*;
import ru.itm.servdbupdate.entity.tables.lis.*;
import ru.itm.servdbupdate.entity.tables.location.*;
import ru.itm.servdbupdate.entity.tables.material.Material;
import ru.itm.servdbupdate.entity.tables.material.MaterialConversation;
import ru.itm.servdbupdate.entity.tables.material.MaterialType;
import ru.itm.servdbupdate.entity.tables.operator.*;
import ru.itm.servdbupdate.entity.tables.sensor.Sensor;
import ru.itm.servdbupdate.entity.tables.sensor.SensorDataType;
import ru.itm.servdbupdate.entity.tables.sensor.SensorInclineCalibration;
import ru.itm.servdbupdate.entity.tables.sensor.SensorType;
import ru.itm.servdbupdate.entity.tables.shift.Shift;
import ru.itm.servdbupdate.entity.tables.status.*;
import ru.itm.servdbupdate.entity.tables.location.Routes;
import ru.itm.servdbupdate.entity.tables.tire.Tire;
import ru.itm.servdbupdate.entity.tables.tire.TireAssign;
import ru.itm.servdbupdate.entity.tables.tire.TireStorage;
import ru.itm.servdbupdate.entity.tables.trans.TransCoord;
import ru.itm.servdbupdate.entity.tables.trans.TransFuel;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class KryoFactory {


    private final static KryoFactory threadFactory = new ThreadLocalKryoFactory();

    protected KryoFactory() {
    }

    public static KryoFactory getDefaultFactory() {
        return threadFactory;
    }

    /**
     * В методе прописываются (регистрируются в kryo) все классы для сериализации.
     * @return kryo объект Kryo
     */
    protected Kryo createKryo() {
        Kryo kryo = new Kryo();

        kryo.register(ValuesData.class);

        kryo.register(Dispatcher.class);

        kryo.register(Hole.class);
        kryo.register(HoleStatus.class);

        kryo.register(Equipment.class);
        kryo.register(EquipmentDrill.class);
        kryo.register(EquipmentHaul.class);
        kryo.register(EquipmentLoad.class);
        kryo.register(EQUIPMENT_TYPE.class);
        kryo.register(EquipmentType.class);

        kryo.register(ActionGroup.class);
        kryo.register(ActionVariableSource.class);
        kryo.register(LisAction.class);
        kryo.register(LisActionPredicate.class);
        kryo.register(LisActionPredicateVariable.class);
        kryo.register(LisActionSourceVariable.class);
        kryo.register(LisActionVariable.class);
        kryo.register(LisCheckFunction.class);
        kryo.register(LisConnection.class);
        kryo.register(LisGroup.class);
        kryo.register(LisPoint.class);
        kryo.register(LisPredicate.class);
        kryo.register(LisPredicateVariable.class);
        kryo.register(LisSourceVariable.class);
        kryo.register(LisType.class);

        kryo.register(CoordinateLocation.class);
        kryo.register(Location.class);
        kryo.register(LocationAutoStatus.class);
        kryo.register(LocationInheritance.class);
        kryo.register(LocationType.class);
        kryo.register(Road.class);
        kryo.register(Routes.class);
        kryo.register(RoutesRoads.class);
        kryo.register(SpeedLimits.class);

        kryo.register(MaterialType.class);
        kryo.register(MaterialConversation.class);
        kryo.register(Material.class);

        kryo.register(Act.class);
        kryo.register(ActToRole.class);
        kryo.register(Operator.class);
        kryo.register(OperatorOnEquip.class);
        kryo.register(OperatorOnEquipType.class);
        kryo.register(Role.class);

        kryo.register(Sensor.class);
        kryo.register(SensorDataType.class);
        kryo.register(SensorInclineCalibration.class);
        kryo.register(SensorType.class);

        kryo.register(Shift.class);
        kryo.register(Timestamp.class);

        kryo.register(Statuses.class);
        kryo.register(StatusCategory.class);
        kryo.register(StatusCatLocationRel.class);
        kryo.register(StatusGroup.class);
        kryo.register(StatusGroupRelation.class);
        kryo.register(StatusInheritance.class);

        kryo.register(Tire.class);
        kryo.register(TireAssign.class);
        kryo.register(TireStorage.class);

        kryo.register(TransFuel.class);
        kryo.register(Calendar.class);
        kryo.register(GregorianCalendar.class);
        kryo.register(BigDecimal.class);

        kryo.register(TransCoord.class);

        return kryo;
    }

}