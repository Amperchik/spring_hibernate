package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User(new Car("AUDI", 100), "User1", "Lastname1", "user1@mail.ru"));
        userService.add(new User(new Car("BMW", 101), "User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User(new Car("TOYOTA", 102), "User3", "Lastname3", "user3@mail.ru"));
        userService.add(new User(new Car("VAZ", 103), "User4", "Lastname4", "user4@mail.ru"));

        List<User> users = userService.listUsers();
        for (User user : users) {
            user.toString();
            System.out.println();
        }
        System.out.println(userService.getUser("BMW", 101));
        context.close();
    }
}
