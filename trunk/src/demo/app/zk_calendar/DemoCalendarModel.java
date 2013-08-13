package demo.app.zk_calendar;
 
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
 
import org.zkoss.calendar.api.CalendarEvent;
import org.zkoss.calendar.api.RenderContext;
import org.zkoss.calendar.impl.SimpleCalendarModel;
 
public class DemoCalendarModel extends SimpleCalendarModel {
    private static final long serialVersionUID = 1L;
     
    private String filterText = "";
 
    public DemoCalendarModel(List<CalendarEvent> calendarEvents) {
        super(calendarEvents);
    }
 
    public void setFilterText(String filterText) {
        this.filterText = filterText;
    }
 
    @Override
    public List<CalendarEvent> get(Date beginDate, Date endDate, RenderContext rc) {
        List<CalendarEvent> list = new LinkedList<CalendarEvent>();
        long begin = beginDate.getTime();
        long end = endDate.getTime();
                 
        for (Iterator<?> itr = _list.iterator(); itr.hasNext();) {
            Object obj = itr.next();
            CalendarEvent ce = obj instanceof CalendarEvent ? (CalendarEvent)obj : null;
             
            if(ce == null) break;
             
            long b = ce.getBeginDate().getTime();
            long e = ce.getEndDate().getTime();
            if (e >= begin && b < end && ce.getContent().toLowerCase().contains(filterText.toLowerCase()))
                list.add(ce);
        }
        return list;
    }
 
}
