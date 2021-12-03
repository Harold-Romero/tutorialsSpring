package com.fundamentosspring.tutoriales;

import com.fundamentosspring.tutoriales.bean.MyBean;
import com.fundamentosspring.tutoriales.bean.MyBeanWithDependency;
import com.fundamentosspring.tutoriales.bean.MyBeanWithProperties;
import com.fundamentosspring.tutoriales.component.ComponentDependency;
import com.fundamentosspring.tutoriales.entity.User;
import com.fundamentosspring.tutoriales.pojo.UserPojo;
import com.fundamentosspring.tutoriales.repository.UserRepository;
import com.fundamentosspring.tutoriales.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class TutorialesApplication implements CommandLineRunner {

    private final Log LOGGER = LogFactory.getLog(TutorialesApplication.class);

    private ComponentDependency componentDependency;
    private MyBean myBean;
    private MyBeanWithDependency myBeanWithDependency;
    private MyBeanWithProperties myBeanWithProperties;
    private UserPojo userPojo;
    private UserRepository userRepository;
    private UserService userService;

    public TutorialesApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency, MyBean myBean, MyBeanWithDependency myBeanWithDependency, MyBeanWithProperties myBeanWithProperties, UserPojo userPojo, UserRepository userRepository, UserService userService){
        this.componentDependency = componentDependency;
        this.myBean = myBean;
        this.myBeanWithDependency = myBeanWithDependency;
        this.myBeanWithProperties = myBeanWithProperties;
        this.userPojo = userPojo;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public static void main(String[] args) {
        SpringApplication.run(TutorialesApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //ejemplosAnteriores();
        saveUsersInDataBase();
        getInformationJpqlFromUser();
        saveWithErrorTransactional();
    }

    private void getInformationJpqlFromUser(){
        /*LOGGER.info("Usuario con el método findByUserEmail" + userRepository.findByUserEmail("Julie@domain.com")
                .orElseThrow(()-> new RuntimeException("No se encontró el usuario")));

        userRepository.findAndSort("user", Sort.by("id").descending())
                .stream()
                .forEach(user -> LOGGER.info("Usuario con método sort " + user));

        userRepository.findByName("John")
                .stream()
                .forEach(user -> LOGGER.info("Usuario con query method " + user));

        LOGGER.info("Usuario con query method findByEmailAndName " + userRepository.findByEmailAndName("Daniela@domain.com", "Daniela")
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado")));

        userRepository.findByNameLike("%u%")
                .stream()
                .forEach(user -> LOGGER.info("Usuario findByNameLike " + user));

        userRepository.findByNameOrEmail(null, "user10@domain.com")
                .stream()
                .forEach(user -> LOGGER.info("Usuario findByNameOrEmail " + user));*/

        userRepository.findByBirthdateBetween(LocalDate.of(2021, 3, 1), LocalDate.of(2021, 4, 2))
                .stream()
                .forEach(user -> LOGGER.info("Usuario con intervalo de fechas: " + user));

        /*userRepository.findByNameLikeOrderByIdDesc("%user%")
                .stream()
                .forEach(user -> LOGGER.info("Usuario encontrado con like y ordenado" + user));*/

        userRepository.findByNameContainingOrderByIdDesc("user")
                .stream()
                .forEach(user -> LOGGER.info("Usuario encontrado con Containing y ordenado" + user));

        LOGGER.info("El usuario a partir del named parameter es: " + userRepository.getAllByBirthDayAndEmail(LocalDate.of(2021, 07, 21), "Daniela@domain.com")
                .orElseThrow(()->new RuntimeException("No se encontró el usuario a partir del named parameter")));
    }

    private void saveWithErrorTransactional(){
        User test1 = new User("test1Transactional1", "TestTransactional1@domain.com", LocalDate.now());
        User test2 = new User("test2Transactional2", "test2Transactional2@domain.com", LocalDate.now());
        User test3 = new User("test3Transactional3", "test3Transactional3@domain.com", LocalDate.now());
        User test4 = new User("test4Transactional4", "test4Transactional4@domain.com", LocalDate.now());

        List<User> users = Arrays.asList(test1, test2, test3, test4);

        try {
            userService.saveTransactional(users);
        }catch (Exception e){
            LOGGER.error("Esta es una excepción dentro del método transactional " + e);
        }
        userService.getAllUsers().stream()
                .forEach(user -> LOGGER.info("Este es el usuario dentro del método transaccional " + user));
    }

    private void saveUsersInDataBase(){
        User user1 = new User("John", "Joth@domain.com", LocalDate.of(2021, 03, 20));
        User user2 = new User("Julie", "Julie@domain.com", LocalDate.of(2021, 05, 21));
        User user3 = new User("Daniela", "Daniela@domain.com", LocalDate.of(2021, 07, 21));
        User user4 = new User("user4", "user4@domain.com", LocalDate.of(2021, 07, 7));
        User user5 = new User("user5", "user5@domain.com", LocalDate.of(2021, 11, 11));
        User user6 = new User("user6", "user6@domain.com", LocalDate.of(2021, 2, 25));
        User user7 = new User("user7", "user7@domain.com", LocalDate.of(2021, 3, 11));
        User user8 = new User("user8", "user8@domain.com", LocalDate.of(2021, 4, 12));
        User user9 = new User("user9", "user9@domain.com", LocalDate.of(2021, 5, 22));
        User user10 = new User("user10", "user10@domain.com", LocalDate.of(2021, 8, 3));
        User user11 = new User("user11", "user11@domain.com", LocalDate.of(2021, 1, 12));
        User user12 = new User("user12", "user12@domain.com", LocalDate.of(2021, 2, 2));

        List<User> list = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10, user11, user12);

        list.stream().forEach(userRepository::save);
    }

    private void ejemplosAnteriores(){
        componentDependency.saludar();
        myBean.print();
        myBeanWithDependency.printWithDependency();
        System.out.println(myBeanWithProperties.function());
        System.out.println(userPojo.getEmail() + "-" + userPojo.getPassword());
        try{
            //error
            int value = 10/0;
            LOGGER.debug("Mi valor: " + value);
        }catch (Exception e){
            LOGGER.error("Esto es un error al dividir por cero " + e.getMessage());
        }
    }
}
