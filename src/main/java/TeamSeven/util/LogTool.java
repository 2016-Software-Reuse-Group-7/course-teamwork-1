package TeamSeven.util;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by tina on 16/3/29.
 */
public abstract class LogTool {

    public static void log( String name )
    {

        Date dt = new Date();
        DateFormat df = new SimpleDateFormat( "yyyy/MM/dd HH:mm:ss" );
        String time = df.format( dt );

        try {

            if ( name.equals( "server" ) ) {

                String fileName = "./log/server.txt";
                FileWriter writer = new FileWriter( fileName, true );
                System.out.println( time + "  接收消息数量: " + getReceivedMessagesNumber() +"  忽略消息数量: " +  getIgnoredMessagesNumber() );  //received/ignored messages number
                writer.write( time + "  接收消息数量: " + getReceivedMessagesNumber() +"  忽略消息数量: " +  getIgnoredMessagesNumber() + "\n" );
                writer.close();

            } else {

                String fileName = "./log/client.txt";
                FileWriter writer = new FileWriter( fileName, true );
                System.out.println( time + "  用户: " + name + "  发送消息数量: " + getSrntMessagesNumber() );  //sent messages number
                writer.write( time + "  用户: " + name + "  发送消息数量: " + getSrntMessagesNumber() + "\n" );
                writer.close();

            }


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println( "error" );
        }

    }

    public static int getReceivedMessagesNumber() {
        //...
        return 1;
    }

    public static int getIgnoredMessagesNumber() {
        //...
        return 2;
    }

    public static int getSrntMessagesNumber() {
        //...
        return 3;
    }
    


}
