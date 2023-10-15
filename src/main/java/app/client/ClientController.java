package app.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        return "singleClientView";
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
            return "allClientsView";
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
        return "singleClientView";
    }

    @RequestMapping("/delete/{clientId}")
    public String deleteClient(@PathVariable Long clientId) {
        clientDao.delete(clientDao.findById(clientId));
        return "redirect:/clients/list";
    }


}
