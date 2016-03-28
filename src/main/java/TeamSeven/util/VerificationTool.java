package TeamSeven.util;

import TeamSeven.entity.Account;
import TeamSeven.entity.LoggedAccountInfo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by joshoy on 16/3/27.
 */
public abstract class VerificationTool {

    private static void updateAllowedAccount() {
        // TODO
    }

    public static boolean checkAccount(Account account) {
        // TODO
        return true;
    }

    //消息验证工具
    //已登陆账户列表
    private static Map<Account, LoggedAccountInfo> LoggedAccountList = new HashMap<Account, LoggedAccountInfo>();

    //注册已登陆的账户到列表中
    public static boolean registerLoggedAccount(Account account){
        if (LoggedAccountList.containsKey(account)){
            //have registered
            return false;
        }else{
            LoggedAccountList.put(account, new LoggedAccountInfo());
            return true;
        }
    }

    //注销列表中的账户
    public static boolean cancelLoggedAccount(Account account){
        if (!LoggedAccountList.containsKey(account)){
            //didn't registered
            return false;
        }else{
            LoggedAccountList.remove(account);
            return true;
        }
    }

    public static boolean checkOverFrequency(Account account, Date date){
        final int LimitedTime = 1000; // 1秒/1000毫秒
        Date recentMSGTime = LoggedAccountList.get(account).getRecentMSGTime();
        if (recentMSGTime != null){
            if (date.getTime() - recentMSGTime.getTime() <= LimitedTime){
                //OverFrequency
                return false;
            }
        }

        //nothing wrong with this MSGDate
        return LoggedAccountList.get(account).updateRecentMSGTime(date);
    }

    public static boolean checkOutOfLimit(Account account, Date date){
        final  int LimitedMSGNum = 100;
        int MSGCount = LoggedAccountList.get(account).getMSGCount();
        if (MSGCount >= LimitedMSGNum){
            //OutOfLimit
            return false;
        }

        //nothing wrong with this MSGNum
        return LoggedAccountList.get(account).incMSGCount();
    }
}
