package ru.itm.servdbupdate.kryo;

import com.esotericsoftware.kryo.kryo5.Kryo;
import com.esotericsoftware.kryo.kryo5.io.Input;
import com.esotericsoftware.kryo.kryo5.io.Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @class KryoSerializer для простоты сериализации
 * Функциональный процесс сериализации для упрощения.
 * Два статических метода.
 */
public class KryoSerializer {
    private static final ThreadLocalKryoFactory factory = new ThreadLocalKryoFactory();

    /**
     * Сериализует объект в массив байтов
     * @param object объект
     * @return b массив байтов
     */
    public static byte[] serialize(Object object) {
        Kryo kryo = factory.getKryo();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Output output = new Output(byteArrayOutputStream);
        kryo.writeClassAndObject(output, object);
        output.flush();
        output.close();
        byte[] b = byteArrayOutputStream.toByteArray();
        try {
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }

    /**
     * Десериализация массива байтов в объект.
     * @param b массив байтов
     * @return Object объект - десериализованный класс
     */
    public static Object deserialize(byte[] b) {
        if (b.length == 0) {
            return null;
        }
        Input input = new Input(new ByteArrayInputStream(b));
        Kryo kryo = factory.getKryo();
        return kryo.readClassAndObject(input);
    }


}
