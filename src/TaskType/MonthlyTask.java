package TaskType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MonthlyTask extends Task
{

    public MonthlyTask(String title, String description, Type type, LocalDateTime dateTime)
    {
        super(title, description, type, dateTime);
    }
    @Override
    public boolean appearsIn(LocalDate date) {
        if (this.getDateTime().getDayOfMonth() == date.getDayOfMonth() && this.getDateTime().toLocalDate().compareTo(date) <= 0) {
            return true;
        } else {
            return false;
        }
    }

    public String getNextEventDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        if (this.getDateTime().compareTo(LocalDateTime.now()) <= 0) {
            int years = LocalDateTime.now().getYear() - this.getDateTime().getYear();
            LocalDateTime nextEvent = this.getDateTime().plusYears(years);
            int month = LocalDateTime.now().getMonthValue() - this.getDateTime().getMonthValue();
            nextEvent = nextEvent.plusMonths(month);
            if (nextEvent.compareTo(LocalDateTime.now()) > 0) {
                return nextEvent.format(formatter);
            } else {
                return nextEvent.plusMonths(1).format(formatter);
            }
        } else {
            return this.getDateTime().format(formatter);
        }
    }
}
