package app.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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

    @PostMapping("/addClient")
    public String addClientPerform(
            @RequestParam String fullName, @RequestParam String shortName, @RequestParam String type,
            @RequestParam String industry, @RequestParam String address, @RequestParam String nip,
            @RequestParam String source, @RequestParam String contactPerson, @RequestParam String trainingPatron,
            @RequestParam String softwarePatron, @RequestParam String additionalInfo, @RequestParam int haspqfmea, @RequestParam int haspqfmeaPlus,
            @RequestParam int haspqmsa, @RequestParam int needManualUpdate,
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
        client.setHaspqfmea(haspqfmea);
        client.setHaspqfmeaPlus(haspqfmeaPlus);;
        client.setHaspqmsa(haspqmsa);
        client.setNeedManualUpdate(needManualUpdate);

        clientDao.saveClient(client);
        model.addAttribute("client", client);
        return "singleClientView";
    }



}
