package app.contact;

import app.activity.Activity;
import app.client.ClientDao;
import app.user.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/contact")
public class ContactController {

    private final ClientDao clientDao;
    private final ContactDao contactDao;

    public ContactController(ClientDao clientDao, ContactDao contactDao) {
        this.clientDao = clientDao;
        this.contactDao = contactDao;
    }

    @GetMapping("/add/{clientId}")
    public String addContact(@PathVariable Long clientId) {
        return "forms/addContactForm";
    }

    @GetMapping("/delete/{contactId}")
    public String deleteContact(@PathVariable long contactId ) {
        String clientId = String.valueOf(contactDao.findById(contactId).getClient().getId());
        contactDao.delete(contactDao.findById(contactId));
        return "redirect:/clients/" + clientId;
    }

    @RequestMapping(("/{clientId}/{contactId}"))
    public String contactView(@PathVariable Long contactId, @PathVariable long clientId, Model model) {
        model.addAttribute("contact",contactDao.findById(contactId));
        model.addAttribute("clientId",clientId).addAttribute("clientName",clientDao.findById(clientId).getFullName());
        return "contactView";
    }

    @PostMapping("/add/{clientId}")
    public String addActivityPerform(@RequestParam String nameSurname, @RequestParam String mobileNumber,
                                     @RequestParam String phoneNumber, @RequestParam String position,
                                     @RequestParam String department, @RequestParam String email,
                                     @RequestParam int isContactPerson, @RequestParam int marketingConsent,
                                     @RequestParam int rodoConsent, @RequestParam String marketingConsentDate,
                                     @RequestParam String rodoConsentDate, @RequestParam String softwarePatron,
                                     @PathVariable Long clientId){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate marketingConsentDateFormatted = null;
        LocalDate rodoConsentDateFormatted = null;

        if (!marketingConsentDate.isEmpty()) {
            marketingConsentDateFormatted = LocalDate.parse(marketingConsentDate,formatter);
        }

        if (!marketingConsentDate.isEmpty()) {
            rodoConsentDateFormatted = LocalDate.parse(rodoConsentDate,formatter);
        }

        Contact contact = new Contact(nameSurname,mobileNumber,phoneNumber,position,department,email,isContactPerson,
                marketingConsent, rodoConsent,marketingConsentDateFormatted,
                rodoConsentDateFormatted,softwarePatron,clientDao.findById(clientId));

        contactDao.saveContact(contact);

        return "redirect:/clients/" + clientId;
    }


}
