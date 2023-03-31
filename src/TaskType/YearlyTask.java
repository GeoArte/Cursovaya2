package TaskType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class YearlyTask extends Task
{
    public YearlyTask(String title, String description, Type type, LocalDateTime dateTime)
    {
        super(title, description, type, dateTime);
    }

    @Override
    public boolean appearsIn(LocalDate date) {
        if (this.getDateTime().getDayOfMonth() == date.getDayOfMonth() && this.getDateTime().getMonthValue() == date.getMonthValue() && this.getDateTime().toLocalDate().compareTo(date) <= 0) {
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
            if (nextEvent.compareTo(LocalDateTime.now()) > 0) {
                return nextEvent.format(formatter);
            } else {
                return nextEvent.plusYears(1).format(formatter);
            }
        } else {
            return this.getDateTime().format(formatter);
        }
    }
}
