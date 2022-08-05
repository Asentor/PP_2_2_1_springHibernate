package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);
      Car tmp;
      tmp = new Car("model1", 1);
      carService.add(tmp);


      userService.add(new User(tmp, "User1", "Lastname1", "user1@mail.ru"));
      tmp = new Car("model4", 4);
      carService.add(tmp);
      userService.add(new User(tmp, "User2", "Lastname2", "user2@mail.ru"));
      tmp = new Car("model2", 2);
      carService.add(tmp);
      userService.add(new User(tmp, "User3", "Lastname3", "user3@mail.ru"));
      tmp = new Car("model3", 3);
      carService.add(tmp);
      userService.add(new User(tmp, "User4", "Lastname4", "user4@mail.ru"));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         if (user.getCar() != null) {System.out.println("Car = " + user.getCar().getModel());}
         System.out.println();
      }
      userService.getUserByCar("model3", 3);


      context.close();
   }
}
