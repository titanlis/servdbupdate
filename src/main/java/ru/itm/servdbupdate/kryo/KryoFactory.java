package ru.itm.servdbupdate.kryo;


import com.esotericsoftware.kryo.kryo5.Kryo;
import ru.itm.servdbupdate.entity.tables.config.ValuesData;
import ru.itm.servdbupdate.entity.tables.dispatcher.Dispatcher;
import ru.itm.servdbupdate.entity.tables.drilling.Hole;
import ru.itm.servdbupdate.entity.tables.drilling.HoleStatus;
import ru.itm.servdbupdate.entity.tables.equipment.*;
import ru.itm.servdbupdate.entity.tables.lis.*;
import ru.itm.servdbupdate.entity.tables.location.Location;
import ru.itm.servdbupdate.entity.tables.operator.Act;
import ru.itm.servdbupdate.entity.tables.operator.ActToRole;
import ru.itm.servdbupdate.entity.tables.operator.Role;

public abstract class KryoFactory {


    private final static KryoFactory threadFactory = new ThreadLocalKryoFactory();

    protected KryoFactory() {
    }

    public static KryoFactory getDefaultFactory() {
        return threadFactory;
    }

    protected Kryo createKryo() {
        Kryo kryo = new Kryo();
        kryo.register(Act.class);
        kryo.register(ActToRole.class);
        kryo.register(Role.class);
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
        kryo.register(LisActionPredicateVarible.class);
        kryo.register(LisActionSourceVariable.class);
        kryo.register(LisPredicate.class);
        kryo.register(LisSourceVariable.class);
        kryo.register(LisType.class);
        kryo.register(LisActionVariable.class);
        kryo.register(LisConnection.class);
        kryo.register(LisPredicateVariable.class);

        kryo.register(Location.class);

        return kryo;
    }

}