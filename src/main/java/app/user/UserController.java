package app.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @RequestMapping("/home")
    public String homePage() {
        return "homeView";
    }


    @PostMapping("/home")
    public String homePageLogin(@RequestParam String login, @RequestParam String password) {

        if (userDao.findByLogin(login) != null) {
            if (userDao.findByLogin(login).getPassword().equals(password)){
                return "homeView";
            } else {
                return "/WEB-INF/index.jsp";
            }

        } else {
            return "/WEB-INF/index.jsp";
        }

    }

}
