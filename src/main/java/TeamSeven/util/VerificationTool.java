package TeamSeven.util;

import TeamSeven.entity.Account;

import java.sql.*;

/**
 * Created by joshoy on 16/3/27.
 */
public abstract class VerificationTool {

    private static void updateAllowedAccount() {
        // TODO
    }

    public static boolean checkAccount(Account account) {
        // TODO
        String name = account.getUserName();
        String passwd = account.getPassword();
        return true;
    }

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
