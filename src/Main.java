import TaskType.OneTimeTask;
import TaskType.Type;

import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        TaskBook book = new TaskBook();
        TaskBook trash = new TaskBook();
        boolean brake = false;
        int action;
        do {
            System.out.println("Что вы хотите сделать? Введите соответствующее число: \n 1-Создать задачу \n 2-Получить список задач на день \n 3-Удалить задачу по id \n 4-Получить следующую дату выполнения задачи по её id \n 5-Получить удалённые задачи \n 6-Редактировать задачу по id \n 7-Получить задачи сгруппированные по датам \n 8-Прекратить работу с задачами ");
            action = new Scanner(System.in).nextInt();
            try {
                if (action > 0 && action < 9) {
                    if (action > 4) {
                        if (action < 7) {
                            if (action == 5) {
                                BookManager.getDeletedTask(trash);
                            } else {
                                System.out.println("Напишите id задачи");
                                int id = new Scanner(System.in).nextInt();
                                BookManager.toChangeTask(book, id);
                            }
                        } else {
                            if (action == 7) {
                                BookManager.getTaskGroupByDate(book);
                            } else {
                                brake = true;
                            }
                        }
                    } else {
                        if (action < 3) {
                            if (action == 1) {
                                BookManager.addTask(book);
                            } else {
                                BookManager.getTasksOnDay(book);
                            }
                        } else {
                            if (action == 3) {
                                System.out.println("Напишите id задачи");
                                int id = new Scanner(System.in).nextInt();
                                BookManager.deleteATaskById(book, id, trash);
                            } else {
                                System.out.println("Напишите id задачи");
                                int id = new Scanner(System.in).nextInt();
                                BookManager.getNextEventById(book, id);
                            }
                        }
                    }
                }
            } catch (IllegalAccessError e) {
                System.out.println("Введено не корректное число");
            }
        } while (!brake);
    }
}