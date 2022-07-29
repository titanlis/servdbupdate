package ru.itm.servdbupdate.kryo;


import com.esotericsoftware.kryo.kryo5.Kryo;
import ru.itm.servdbupdate.entity.tables.config.ValuesData;
import ru.itm.servdbupdate.entity.tables.dispatcher.Dispatcher;
import ru.itm.servdbupdate.entity.tables.drilling.Hole;
import ru.itm.servdbupdate.entity.tables.drilling.HoleStatus;
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
        kryo.register(Location.class);

        return kryo;
    }

}