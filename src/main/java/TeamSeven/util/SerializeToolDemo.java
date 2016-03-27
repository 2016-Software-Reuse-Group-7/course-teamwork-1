package TeamSeven.util;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by joshoy on 16/3/26.
 */
public class SerializeToolDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        DataTest dt = new DataTest();
        dt.name = "Hello!";
        dt.id = 234;
        String s = SerializeTool.ObjectToString(dt);
        System.out.println(s);
        DataTest convert = (DataTest)SerializeTool.ObjectFromString(s);
        System.out.println("Name: " + convert.name);
        System.out.println("Id: " + convert.id);
    }
}

class DataTest implements Serializable {
    public String name;
    public int id;
}
