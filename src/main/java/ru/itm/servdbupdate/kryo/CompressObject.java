package ru.itm.servdbupdate.kryo;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public final class CompressObject {
    public static byte[] writeCompressObject(Object obj) throws IOException {
        byte[] byteDate = null;
        ByteArrayOutputStream o = new ByteArrayOutputStream();
        GZIPOutputStream gzout = new GZIPOutputStream(o);
        ObjectOutputStream out = new ObjectOutputStream(gzout);
        out.writeObject(obj);
        out.flush();
        out.close();
        gzout.close();
        byteDate = o.toByteArray();
        o.close();
        return byteDate;
    }

    public static Object readCompressObject(byte[] byteDate)
            throws IOException, ClassNotFoundException {
        Object obj = null;
        ByteArrayInputStream i = new ByteArrayInputStream(byteDate);
        GZIPInputStream gzin = new GZIPInputStream(i);
        ObjectInputStream in = new ObjectInputStream(gzin);
        obj = in.readObject();
        i.close();
        gzin.close();
        in.close();
        return obj;
    }
}