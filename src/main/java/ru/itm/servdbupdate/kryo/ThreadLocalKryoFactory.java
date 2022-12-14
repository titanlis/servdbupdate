package ru.itm.servdbupdate.kryo;


import com.esotericsoftware.kryo.kryo5.Kryo;

/**
 * Привязка экземпляров Kryo к потокам.
 */
public class ThreadLocalKryoFactory extends KryoFactory {

    private final ThreadLocal<Kryo> holder = new ThreadLocal<Kryo>() {
        @Override
        protected Kryo initialValue() {
            return createKryo();
        }
    };

    public Kryo getKryo() {
        return holder.get();
    }

}
