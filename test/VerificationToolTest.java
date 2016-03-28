import TeamSeven.util.VerificationTool;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.security.MessageDigest;

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
        int ret = VerificationTool.verifyPassword(userName,VerificationTool.crypMD5(passwd) );
        assertEquals(-1,ret);
    }
    @Test
    public void TestCryp() throws Exception{
        String origin = "haha";
        String crypt = "4e4d6c332b6fe62a63afe56171fd3725";
        assertEquals(crypt, VerificationTool.crypMD5(origin));
    }
}