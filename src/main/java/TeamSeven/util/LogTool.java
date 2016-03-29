package TeamSeven.util;

import TeamSeven.client.ClientWithTimer;
import TeamSeven.server.ServerWithTimer;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Created by tina on 16/3/29.
 */
public class LogTool
{
    private static int receivedMessagesNumber;
    private static int ignoredMessagesNumber;
    private static LinkedHashMap<String, Integer> sentMessagesNumber = new LinkedHashMap<String, Integer>();

    public LogTool()
    {
        receivedMessagesNumber = 0;
        ignoredMessagesNumber = 0;
        sentMessagesNumber.clear();
    }

    public static void log( String name )
    {

        Date dt = new Date();
        DateFormat df = new SimpleDateFormat( "yyyy/MM/dd HH:mm:ss" );
        String time = df.format( dt );
        try {

            if ( name.equals( "server" ) ) {

                String fileName = "./log/server.txt";
                FileWriter writer = new FileWriter( fileName, true );
                System.out.println( time + "  接收消息数量: " + receivedMessagesNumber +"  忽略消息数量: " +  ignoredMessagesNumber );  //received/ignored messages number
                writer.write( time + "  接收消息数量: " + receivedMessagesNumber +"  忽略消息数量: " +  ignoredMessagesNumber + "\n" );
                writer.close();

            } else {

                String fileName = "./log/client.txt";
                FileWriter writer = new FileWriter( fileName, true );
                System.out.println( time + "  用户: " + name + "  发送消息数量: " + getSentMessagesNumber( name ) );  //sent messages number
                writer.write( time + "  用户: " + name + "  发送消息数量: " + getSentMessagesNumber( name ) + "\n" );
                writer.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println( "error" );
        }

    }

    public static int getReceivedMessagesNumber()
    {
        return receivedMessagesNumber;
    }

    public static void addReceivedMessagesNumber( int number )
    {
        receivedMessagesNumber = receivedMessagesNumber + number;
    }

    public static int getIgnoredMessagesNumber()
    {
        return ignoredMessagesNumber;
    }

    public static void addIgnoredMessagesNumber( int number )
    {
        ignoredMessagesNumber = ignoredMessagesNumber + number;
    }

    public static int getSentMessagesNumber( String clientName )
    {
        return sentMessagesNumber.get( clientName );
    }

    public static void addSentMessagesNumber( String clientName, int number )
    {
        int newCount = sentMessagesNumber.get( clientName ) + number;
        sentMessagesNumber.remove( clientName );
        sentMessagesNumber.put( clientName, newCount );
    }

    public static void addClient( String clientName )
    {
        //Integer zero = new Integer(0);
        sentMessagesNumber.put( clientName, 0 );
    }

}
