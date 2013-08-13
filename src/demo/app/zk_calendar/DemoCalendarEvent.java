package demo.app.zk_calendar;
 
import java.util.Date;
 
import org.zkoss.calendar.impl.SimpleCalendarEvent;
 
public class DemoCalendarEvent extends SimpleCalendarEvent {
    private static final long serialVersionUID = 1L;
 
    public DemoCalendarEvent(Date beginDate, Date endDate, String headerColor, String contentColor, String content) {
        setHeaderColor(headerColor);
        setContentColor(contentColor);
        setContent(content);
        setBeginDate(beginDate);
        setEndDate(endDate);
    }
 
    public DemoCalendarEvent(Date beginDate, Date endDate, String headerColor, String contentColor, String content,
            String title) {
        setHeaderColor(headerColor);
        setContentColor(contentColor);
        setContent(content);
        setTitle(title);
        setBeginDate(beginDate);
        setEndDate(endDate);
    }
 
    public DemoCalendarEvent(Date beginDate, Date endDate, String headerColor, String contentColor, String content,
            String title, boolean locked) {
        setHeaderColor(headerColor);
        setContentColor(contentColor);
        setContent(content);
        setTitle(title);
        setBeginDate(beginDate);
        setEndDate(endDate);
        setLocked(locked);
    }
     
    public DemoCalendarEvent() {
        setHeaderColor("#FFFFFF");
        setContentColor("#000000");
    }
}