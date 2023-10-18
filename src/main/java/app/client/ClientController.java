package app.client;

import app.activity.Activity;
import app.activity.ActivityDao;
import app.user.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clients")
public class ClientController {
    private final ClientDao clientDao;
    private final UserDao userDao;

    private final ActivityDao activityDao;

    public ClientController(ClientDao clientDao, UserDao userDao, ActivityDao activityDao) {
        this.clientDao = clientDao;
        this.userDao = userDao;
        this.activityDao = activityDao;
    }

    @RequestMapping("/list")
    public String clientsPage(Model model){
        model.addAttribute("clients",clientDao.findAll());
        return "allClientsView";
    }

    @RequestMapping("/{clientId}")
    public String clientInfo (@PathVariable Long clientId,Model model) {

        model.addAttribute("client", clientDao.findById(clientId));
        model.addAttribute("activities", activityDao.findActivitiesByClientId(clientId));
        return "singleClientView";
    }

    @GetMapping("/addClient")
    public String addClientShow(){
        return "addClientForm";
    }

    @GetMapping("/edit/{clientId}")
    public String editClientShow(@PathVariable Long clientId, Model model){
        model.addAttribute("client",clientDao.findById(clientId));
        return "editClientForm";
    }

    @PostMapping("/edit/{clientId}")
    public String editClientPerform(
            @RequestParam String fullName, @RequestParam String shortName, @RequestParam String type,
            @RequestParam String industry, @RequestParam String address, @RequestParam String nip,
            @RequestParam String source, @RequestParam String contactPerson, @RequestParam String trainingPatron,
            @RequestParam String softwarePatron, @RequestParam String additionalInfo, @RequestParam Integer needManualUpdate,
            @PathVariable Long clientId, Model model) {

        if (needManualUpdate == null) {
            needManualUpdate = 0;
        }
        Client client = clientDao.findById(clientId);

        client.setFullName(fullName);
        client.setShortName(shortName);
        client.setType(type);
        client.setIndustry(industry);
        client.setAddress(address);
        client.setNip(nip);
        client.setSource(source);
        client.setContactPerson(contactPerson);
        client.setTrainingPatron(trainingPatron);
        client.setSoftwarePatron(softwarePatron);
        client.setAdditionalInfo(additionalInfo);
        client.setNeedManualUpdate(needManualUpdate);


        clientDao.update(client);
        model.addAttribute("client", client);
        return "redirect:/clients/" + clientId;
    }


    @PostMapping("/addClient")
    public String addClientPerform(
            @RequestParam String fullName, @RequestParam String shortName, @RequestParam String type,
            @RequestParam String industry, @RequestParam String address, @RequestParam String nip,
            @RequestParam String source, @RequestParam String contactPerson, @RequestParam String trainingPatron,
            @RequestParam String softwarePatron, @RequestParam String additionalInfo, @RequestParam Integer needManualUpdate,
            Model model) {

        Client existingClient = clientDao.findClientByFullName(fullName);

        if (existingClient != null) {
            return "redirect:/clients/list";
        }

        Client client = new Client();
        client.setFullName(fullName);
        client.setShortName(shortName);
        client.setType(type);
        client.setIndustry(industry);
        client.setAddress(address);
        client.setNip(nip);
        client.setSource(source);
        client.setContactPerson(contactPerson);
        client.setTrainingPatron(trainingPatron);
        client.setSoftwarePatron(softwarePatron);
        client.setAdditionalInfo(additionalInfo);
        client.setNeedManualUpdate(needManualUpdate);

        clientDao.saveClient(client);
        model.addAttribute("client", client);
        return "redirect:/clients/" + client.getId();
    }

    @RequestMapping("/delete/{clientId}")
    public String deleteClient(@PathVariable Long clientId) {
        activityDao.findActivitiesByClientId(clientId).forEach(activity -> {
            activityDao.delete(activity);
        });
        clientDao.delete(clientDao.findById(clientId));

        return "redirect:/clients/list";
    }


}
