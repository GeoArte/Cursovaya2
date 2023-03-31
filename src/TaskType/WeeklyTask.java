package TaskType;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class WeeklyTask extends Task
{

    public WeeklyTask(String title, String description, Type type, LocalDateTime dateTime)
    {
        super(title, description, type, dateTime);
    }
    @Override
    public boolean appearsIn(LocalDate date) {
        if (this.getDateTime().getDayOfWeek() == date.getDayOfWeek() && this.getDateTime().toLocalDate().compareTo(date) <= 0) {
            return true;
        } else {
            return false;
        }
    }

    public String getNextEventDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        if (this.getDateTime().compareTo(LocalDateTime.now()) <= 0) {
            DayOfWeek dayOfWeek = this.getDateTime().getDayOfWeek();
            LocalDateTime nextEvent = LocalDate.now().with(TemporalAdjusters.next(dayOfWeek)).atTime(this.getDateTime().toLocalTime());
            return nextEvent.format(formatter);
        } else {
            return this.getDateTime().format(formatter);
        }
    }
}
