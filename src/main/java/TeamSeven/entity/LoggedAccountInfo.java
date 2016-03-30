package TeamSeven.entity;

import java.util.Date;
import java.util.LinkedList;

/**
 * Created by Andy on 2016/3/27.
 */
public class LoggedAccountInfo {
    private final int LimitedMSGNUM = 5;
    private int MSGCount;
    private LinkedList<Date> MSGTimeQueue;

    public int getMSGCount() {
        return MSGCount;
    }

    public boolean incMSGCount(){
        MSGCount++;
        return true;
    }

    public Date getRecentMSGTime() {
        if (MSGTimeQueue.size() < LimitedMSGNUM){
            return null;
        }

        return MSGTimeQueue.peek();
    }

    public boolean updateRecentMSGTime(Date date) {
        if (MSGTimeQueue.size() == LimitedMSGNUM){
            MSGTimeQueue.poll();
        }

        return MSGTimeQueue.offer(date);
    }

    public LoggedAccountInfo(){
        MSGCount = 0;
        MSGTimeQueue = new LinkedList<Date>();
    }
}
