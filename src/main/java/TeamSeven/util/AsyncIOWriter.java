package TeamSeven.util;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by joshoy on 16/3/27.
 */
public class AsyncIOWriter {

    private Timer timer = null;
    TimerTask task = null;

    public AsyncIOWriter() {
        // true 说明这个timer以daemon方式运行（优先级低，程序结束timer也自动结束).
        this.timer = new Timer(true);
        this.task = new TimerTask() {
            public void run() {
                // TODO
            }
        };
    }

    // delay 为long类型：从现在起过period毫秒执行一次
    public void delayExecute(long period) {
        this.timer.schedule(task, 0, period);
    }

}
