package TeamSeven.util;

import TeamSeven.entity.Account;
import TeamSeven.entity.Chat;
import junit.framework.TestCase;

/**
 * Created by joshoy on 16/3/27.
 */
public class SerializeToolTest extends TestCase {

    public void testObjectToString() throws Exception {
        String s = SerializeTool.ObjectToString(new Account("Josh Ouyang", "123456"));
        System.out.println(s);
    }

    public void testObjectFromString() throws Exception {

    }
}