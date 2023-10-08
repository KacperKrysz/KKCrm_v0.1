package app.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @PostMapping("/userLogin")
    @ResponseBody
    public String homePage(@RequestParam String login, @RequestParam String password) {

        if (userDao.findByLogin(login) != null) {
            if (userDao.findByLogin(login).getPassword().equals(password)){
                return "homeView";
            } else {
                return "Nieprawidłowe dane logowania";
            }

        } else {
            return "Nieprawidłowe dane logowania";
        }

    }

}
