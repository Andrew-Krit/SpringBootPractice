package com.example.SpringBootPractice;

import com.example.SpringBootPractice.userService.DefaultUserService;
import com.example.SpringBootPractice.userService.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
@Profile("!test")
public class ConsoleApp {
    private final DefaultUserService userService;
    private final Scanner scanner = new Scanner(System.in);

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        while (true) {
            System.out.println("""
                    1. Создать пользователя
                    2. Показать всех пользователей
                    3. Найти пользователя по ID
                    4. Обновить пользователя
                    5. Удалить пользователя
                    0. Выход
                    """);

            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    scanner.nextLine();
                    System.out.print("Имя: ");
                    String name = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Возраст: ");
                    int age = scanner.nextInt();

                    userService.createUser(new UserDto(null, name, email, age));
                    break;
                case 2:
                    List<UserDto> users = userService.getAllUsers();
                    users.forEach(System.out::println);
                    break;
                case 3:
                    System.out.print("ID: ");
                    System.out.println(userService.getUserById(scanner.nextInt()));
                    break;
                case 4:
                    System.out.print("ID пользователя для обновления: ");
                    int id_editUser = scanner.nextInt();
                    scanner.nextLine();
                    UserDto user = userService.getUserById(id_editUser);

                    if (user == null) {
                        System.out.println("Пользователь не найден.");
                        break;
                    }

                    System.out.print("Новое имя: ");
                    user.setName(scanner.nextLine());
                    System.out.print("Новый email: ");
                    user.setEmail(scanner.nextLine());
                    System.out.print("Новый возраст: ");
                    user.setAge(scanner.nextInt());

                    userService.updateUser(id_editUser, user);
                    break;
                case 5:
                    System.out.print("ID для удаления: ");
                    userService.deleteUser(scanner.nextInt());
                    break;
                case 0:
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Неверный выбор.");
                    break;
            }
        }
    }
}
