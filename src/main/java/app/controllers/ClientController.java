package app.controllers;

import app.model.activity.ActivityDao;
import app.model.client.Client;
import app.model.client.ClientDao;
import app.model.contact.ContactDao;
import app.model.purchasedProducts.PurchasedProductDao;
import app.model.user.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class responsible for handling client-related requests and views.
 */
@Controller
@RequestMapping("/clients")
public class ClientController {
    private final ClientDao clientDao;
    private final UserDao userDao;
    private final ActivityDao activityDao;

    private final ContactDao contactDao;
    private final PurchasedProductDao purchasedProductDao;

    /**
     * Constructs a new instance of the ClientController.
     *
     * @param clientDao            Data access object for clients.
     * @param userDao              Data access object for users.
     * @param activityDao          Data access object for activities.
     * @param contactDao           Data access object for contacts.
     * @param purchasedProductDao Data access object for purchased products.
     */
    public ClientController(ClientDao clientDao, UserDao userDao, ActivityDao activityDao,
                            ContactDao contactDao, PurchasedProductDao purchasedProductDao) {
        this.clientDao = clientDao;
        this.userDao = userDao;
        this.activityDao = activityDao;
        this.contactDao = contactDao;
        this.purchasedProductDao = purchasedProductDao;
    }
    /**
     * Displays a list of all clients.
     *
     * @param model The Spring Model for passing data to the view.
     * @return The view showing a list of all clients.
     */
    @RequestMapping("/list")
    public String allClientsView(Model model){
        model.addAttribute("clients",clientDao.findAll());
        return "allClientsView";
    }

    /**
     * Displays information with a single client.
     *
     * @param clientId The identifier of the client to display.
     * @param model    The Spring Model for passing data to the view.
     * @return The view displaying information about a single client.
     */
    @RequestMapping("/{clientId}")
    public String clientView (@PathVariable Long clientId,Model model) {

        model.addAttribute("client", clientDao.findById(clientId));
        model.addAttribute("activities", activityDao.findActivitiesByClientId(clientId));
        model.addAttribute("contacts",contactDao.findContactsByClientId(clientId));
        model.addAttribute("purchasedProducts",purchasedProductDao.findPurchasedProductsByClientId(clientId));
        return "singleClientView";
    }

    /**
     * Displays the form for adding a new client.
     *
     * @return The view displaying the form for adding a new client.
     */
    @GetMapping("/addClient")
    public String addClientShow(){
        return "forms/addClientForm";
    }

    /**
     * Handles the request to add a new client.
     *
     * @param fullName          The full name of the client.
     * @param shortName         The short name of the client.
     * @param type              The type of the client.
     * @param industry          The industry of the client.
     * @param address           The address of the client.
     * @param nip               The NIP (Tax Identification Number) of the client.
     * @param source            The source of the client.
     * @param contactPerson     The contact person for the client.
     * @param trainingPatron    The training patron for the client.
     * @param softwarePatron    The software patron for the client.
     * @param additionalInfo    Additional information about the client.
     * @param needManualUpdate  Whether the client needs manual updates.
     * @param model             The Spring Model for passing data to the view.
     * @return A redirect to the client's page after adding the client.
     */
    @PostMapping("/addClient")
    public String addClientPerform(
            @RequestParam String fullName, @RequestParam String shortName, @RequestParam String type,
            @RequestParam String industry, @RequestParam String address, @RequestParam String nip,
            @RequestParam String source, @RequestParam String trainingPatron,
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
        client.setTrainingPatron(trainingPatron);
        client.setSoftwarePatron(softwarePatron);
        client.setAdditionalInfo(additionalInfo);
        client.setNeedManualUpdate(needManualUpdate);

        clientDao.saveClient(client);
        model.addAttribute("client", client);
        return "redirect:/clients/" + client.getId();
    }

    /**
     * Displays the form for editing an existing client.
     *
     * @param clientId The identifier of the client to edit.
     * @param model    The Spring Model for passing data to the view.
     * @return The view displaying the form for editing the client.
     */
    @GetMapping("/edit/{clientId}")
    public String editClientShow(@PathVariable Long clientId, Model model){
        model.addAttribute("client",clientDao.findById(clientId));
        return "forms/editClientForm";
    }

    /**
     * Handles the request to perform the editing of an existing client.
     *
     * @param fullName          The updated full name of the client.
     * @param shortName         The updated short name of the client.
     * @param type              The updated type of the client.
     * @param industry          The updated industry of the client.
     * @param address           The updated address of the client.
     * @param nip               The updated NIP (Tax Identification Number) of the client.
     * @param source            The updated source of the client.
     * @param contactPerson     The updated contact person for the client.
     * @param trainingPatron    The updated training patron for the client.
     * @param softwarePatron    The updated software patron for the client.
     * @param additionalInfo    The updated additional information about the client.
     * @param needManualUpdate  The updated flag indicating whether the client needs manual updates.
     * @param clientId          The identifier of the client being edited.
     * @param model             The Spring Model for passing data to the view.
     * @return A redirect to the client's page after performing the edit.
     */
    @PostMapping("/edit/{clientId}")
    public String editClientPerform(
            @RequestParam String fullName, @RequestParam String shortName, @RequestParam String type,
            @RequestParam String industry, @RequestParam String address, @RequestParam String nip,
            @RequestParam String source, @RequestParam String trainingPatron,
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
        client.setTrainingPatron(trainingPatron);
        client.setSoftwarePatron(softwarePatron);
        client.setAdditionalInfo(additionalInfo);
        client.setNeedManualUpdate(needManualUpdate);


        clientDao.update(client);
        model.addAttribute("client", client);
        return "redirect:/clients/" + clientId;
    }

    /**
     * Handles the request to delete a client and associated activities.
     *
     * @param clientId The identifier of the client to be deleted.
     * @return A redirect to the list of all clients after the client is deleted.
     */
    @RequestMapping("/delete/{clientId}")
    public String deleteClient(@PathVariable Long clientId) {
        activityDao.findActivitiesByClientId(clientId).forEach(activity -> {
            activityDao.delete(activity);
        });
        clientDao.delete(clientDao.findById(clientId));

        return "redirect:/clients/list";
    }


}
