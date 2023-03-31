import TaskType.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.time.format.DateTimeFormatter;

public class BookManager
{
    public static void addTask(TaskBook book)
    {
        System.out.println("Введите название задачи:");
        String name = new Scanner(System.in).next();
        System.out.println("Введите описание задачи:");
        String description = new Scanner(System.in).next();
        LocalDateTime dateTime = null;
        try {
            System.out.println("Напишите дату и время задачи в формате (dd/mm/yyyy hh:mm:ss):");
            String dateTimeStr = new Scanner(System.in).nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            dateTime = LocalDateTime.parse(dateTimeStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Неверно введены дата и время, не удалось создать задачу");
            return;
        }
        System.out.println("Введите тип задачи: \n >=0-Деловая \n <0-Личная");
        int typeN = new Scanner(System.in).nextInt();
        Type type;
        if (typeN >= 0) {
            type = Type.WORK;
        }
        else
        {
            type = Type.PERSONAL;
        }
        System.out.println("Выберите повторяемость задачи и введите соответствующее число: \n 1-Один раз \n 2-Ежедневно \n 3-Еженедельно \n 4-Ежемесячно \n 5-Ежегодно");
        int regularN = new Scanner(System.in).nextInt();
        try {
            if (regularN < 6 && regularN > 0) {
                if (regularN > 3) {
                    if (regularN == 4) {
                        MonthlyTask newTask = new MonthlyTask(name, description, type, dateTime);
                        book.getBook().add(newTask);
                    } else {
                        YearlyTask newTask = new YearlyTask(name, description, type, dateTime);
                        book.getBook().add(newTask);
                    }
                } else {
                    if (regularN < 2) {
                        OneTimeTask newTask = new OneTimeTask(name, description, type, dateTime);
                        book.getBook().add(newTask);
                    } else {
                        if (regularN == 2) {
                            DailyTask newTask = new DailyTask(name, description, type, dateTime);
                            book.getBook().add(newTask);
                        } else {
                            WeeklyTask newTask = new WeeklyTask(name, description, type, dateTime);
                            book.getBook().add(newTask);
                        }
                    }
                }
            }
        } catch (IllegalAccessError e)
        {
            System.out.println("Введены неверные данные");
        }
    }

    public static void getTasksOnDay(TaskBook book) {
        System.out.println("Напишите дату в которую вы хотите узнать задачи задачи в формате (dd/MM/yyyy):");
        String dateTimeStr = new Scanner(System.in).nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateTime = LocalDate.parse(dateTimeStr, formatter);
        List<Task> tasksOnDay = book.getBook().stream().filter(n -> n.appearsIn(dateTime)).collect(Collectors.toList());
        for (Task element : tasksOnDay) {
            System.out.println(element.getTask());
        }
        System.out.println("Больше задач на день нет");
    }
    public static void deleteATaskById(TaskBook book, int id , TaskBook trash) {
        boolean delete = false;
        Iterator<Task> iterator = book.getBook().iterator();
        while (iterator.hasNext()) {
            Task element = iterator.next();
            if (element.getId() == id) {
                trash.getBook().add(element);
                iterator.remove();
                System.out.println("Задача удалена");
                delete = true;
                break;
            }
        }
        if (!delete) {
            System.out.println("Задачи с таким id нет");
        }
    }

    public static void getNextEventById(TaskBook book, int id) {
        boolean found = false;
        for (Task task : book.getBook()) {
            if (task.getId() == id) {
                System.out.println(task.getNextEventDateTime());
                found = true;
                break;
            }
        }
        if (!found) {
            throw new IllegalArgumentException("Задачи с id=" + id + " нет в книге задач.");
        }
    }

    public static void getDeletedTask(TaskBook trash) {
        for (Task element : trash.getBook()) {
            System.out.println(element.getTask());
        }
    }

    public static void toChangeTask(TaskBook book, int id) {
        boolean found = false;
        for (Task task : book.getBook()) {
            if (task.getId() == id) {
                System.out.println("Что вы хотите изменить? \n >=0 Название \n <0 Описание");
                int change = new Scanner(System.in).nextInt();
                if (change >= 0) {
                    System.out.println("Введите новое название");
                    String name = new Scanner(System.in).next();
                    task.setTitle(name);
                } else {
                    System.out.println("Введите новое описание");
                    String description = new Scanner(System.in).next();
                    task.setDescription(description);
                }
                found = true;
                break;
            }
        }
        if (!found) {
            throw new IllegalArgumentException("Задачи с id=" + id + " нет в книге задач.");
        }
    }

    public static void getTaskGroupByDate(TaskBook book) {
        TaskBook bookTwo = book;
        Map<LocalDate, List<Task>> groupedByDate = bookTwo.getBook().stream().collect(Collectors.groupingBy(task -> task.getDateTime().toLocalDate()));
        groupedByDate.forEach((date, tasks) -> {
            System.out.println("Дата: " + date);
            for (Task task : tasks) {
                System.out.println(task.getTask());
            }
        });
    }
}