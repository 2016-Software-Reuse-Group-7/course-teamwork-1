package TeamSeven.server.verification;

/**
 * Created by joshoy on 16/3/22.
 */
import java.sql.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public abstract class DbAccess {

    public static boolean verifyPassword( String userName, String password ) //返回用户id,验证不通过返回-1
    {
        Connection c = null;
        Statement stmt = null;
        //int userId = -1;

        try {
            Class.forName( "org.sqlite.JDBC" );
            c = DriverManager.getConnection( "jdbc:sqlite:teamwork-1.db" );
            //System.out.println( "Opened database successfully" );

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM user WHERE userName = '" + userName + "' ;" );
            while ( rs.next() ) {
                String expectedPw = rs.getString( "userPassword" );
                if( password.equals(expectedPw) )
                {
                    //userId = rs.getInt("userId");
                    return true;
                }
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        //return userId;
        return false;
    }

    public static boolean recordMessages( String userName, String content )
    {
        Connection c = null;
        Statement stmt = null;
        Date dt = new Date();
        DateFormat df = new SimpleDateFormat( "yyyy/MM/dd HH:mm:ss" );
        String time = df.format( dt );
        try {
            Class.forName( "org.sqlite.JDBC" );
            c = DriverManager.getConnection( "jdbc:sqlite:teamwork-1.db" );
            c.setAutoCommit( false );
            //System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "INSERT INTO messages VALUES ( '" + userName + "', '" + content + "', '" + time +"' );";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            return false;
        }
        return true;
    }

    public static boolean nameUsed( String name )
    {
        return false;
    }
}