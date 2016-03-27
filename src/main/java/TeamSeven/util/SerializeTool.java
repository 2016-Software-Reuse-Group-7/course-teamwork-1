package TeamSeven.util;

import org.nustaq.serialization.FSTConfiguration;

import java.io.*;
import java.util.Base64;

/**
 * Created by joshoy on 16/3/26.
 */
public abstract class SerializeTool {
    /* 自定义版本 */
    public static FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();

    /* 将Object转String */
    public static String ObjectToString(Serializable obj) throws IOException {
        byte bArray[] = conf.asByteArray(obj);
        String ret = new String(Base64.getEncoder().encode(bArray));
        return ret;
    }

    /* 将String转Object, 记得强制转换类型 */
    public static Object ObjectFromString(String s) throws IOException, ClassNotFoundException {
        byte[] data = Base64.getDecoder().decode(s);
        return conf.asObject(data);
    }
}
