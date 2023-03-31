package TaskType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task
{
    private String title;
    private String description;
    private Type type;
    private LocalDateTime dateTime;
    private int id;

    private static int creatorId;

    static {
        creatorId = 0;
    }

    public Task(String title, String description, Type type, LocalDateTime dateTime)
    {
        this.dateTime = dateTime;
        this.id = creatorId;
        creatorId ++;
        this.title = title;
        this.type = type;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public Type getType() {
        return type;
    }

    public String toString()
    {
        return title + "/n" + description + "/n" + dateTime;
    }
    public abstract boolean appearsIn(LocalDate localDate);

    public abstract String getNextEventDateTime();

    public String getTask() {
        return "id: " + this.id + " название: " + this.title + " описание: " + this.description + " тип: " + this.type + " дата и время: " + this.dateTime;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}