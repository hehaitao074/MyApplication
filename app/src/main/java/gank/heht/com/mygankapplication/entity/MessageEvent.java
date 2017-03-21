package gank.heht.com.mygankapplication.entity;

/**
 * Created by hehaitao01 on 2017/3/21.
 */

public class MessageEvent {

    public  boolean isChange;

    public MessageEvent(boolean isChange) {
        this.isChange = isChange;
    }

    @Override
    public String toString() {
        return "MessageEvent{" +
                "isChange=" + isChange +
                '}';
    }
}
