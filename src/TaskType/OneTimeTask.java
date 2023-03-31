package TaskType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class OneTimeTask extends Task
{

    public OneTimeTask(String title, String description, Type type, LocalDateTime dateTime) {
        super(title, description, type, dateTime);
    }

    @Override
    public boolean appearsIn(LocalDate date) {

        return false;
    }
    public String getNextEventDateTime() {
        if (this.getDateTime().compareTo(LocalDateTime.now()) > 0) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            return this.getDateTime().format(formatter);
        } else {
            return "Таких событий больше не запланированно.";
        }
    }
}