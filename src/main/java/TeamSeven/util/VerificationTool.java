package TeamSeven.util;

import TeamSeven.entity.Account;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.sql.*;

/**
 * Created by joshoy on 16/3/27.
 */
public abstract class VerificationTool {

    private static void updateAllowedAccount() {
        // TODO
    }

    public static int checkAccount(Account account) {
        // TODO
        String name = account.getUserName();
        String passwd = account.getPassword();
        return verifyPassword(name,passwd);
    }

    // Check account if active to ensure the chat is Okay.
    public static boolean checkAccountActive(Account account) {
        Connection c;
        PreparedStatement pst;
        try{
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:teamwork-1.db");
            pst = c.prepareStatement("SELECT * FROM account WHERE userName = ?");
            pst.setString(1,account.getUserName());
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                int status = rs.getInt("login");
                if(status == 1){
                    return true;
                }
            }
        }catch (Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        return false;
    }

    public static String crypMD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            System.out.println(sb.toString());
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    // return 1 means log in OK.
    // return 0 means wrong name or passwd
    // return -1 means diplicate login.
    public static int verifyPassword( String userName, String password ) //返回用户id,验证不通过返回-1
    {
        Connection c = null;
        PreparedStatement stmt = null;
        PreparedStatement st = null;
        //int userId = -1;

        try {
            Class.forName( "org.sqlite.JDBC" );
            c = DriverManager.getConnection( "jdbc:sqlite:teamwork-1.db" );
            //System.out.println( "Opened database successfully" );

            stmt = c.prepareStatement("SELECT * FROM account WHERE userName = ?");
            stmt.setString(1,userName);
            ResultSet rs = stmt.executeQuery();
            while ( rs.next() ) {
                String expectedPw = rs.getString( "password" );
                int login = rs.getInt("login");
                if( password.equals(expectedPw) )
                {
                    // System.out.println(expectedPw.equals(password)+" "+login);
                    if(login == 0){
                        st = c.prepareStatement("UPDATE account SET login = 1 where userName = ?");
                        st.setString(1,userName);
                        st.executeUpdate();
                        return 1;
                    }else {
                        return -1;
                    }
                }
            }

            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        //return userId;
        return 0;
    }

}
