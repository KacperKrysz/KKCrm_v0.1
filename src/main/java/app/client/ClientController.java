package app.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/clients")
public class ClientController {
    private final ClientDao clientDao;

    public ClientController(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @RequestMapping("/list")
    public String clientsPage(Model model){
        model.addAttribute("clients",clientDao.findAll());
        return "allClientsView";
    }

    @RequestMapping("/{fullName}")
    public String clientInfo (@PathVariable String fullName,Model model) {
        model.addAttribute("client",clientDao.findClientByFullName(fullName));
        return "singleClientView";
    }



}
