import TeamSeven.util.VerificationTool;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by zhao on 2016/3/27.
 */
public class VerificationToolTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }
    @Test
    public void TestLogin() throws Exception{
        String userName = "a";
        String passwd = "bbb";
        int ret = VerificationTool.verifyPassword(userName,passwd);
        assertEquals(1,ret);
    }
}