package app.controllers;

import app.model.client.ClientDao;
import app.model.contact.Contact;
import app.model.contact.ContactDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Controller responsible for managing contacts associated with clients.
 */
@Controller
@RequestMapping("/contact")
public class ContactController {

    private final ClientDao clientDao;
    private final ContactDao contactDao;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Initializes a new instance of the ContactController class with the provided dependencies.
     *
     * @param clientDao   The data access object for clients.
     * @param contactDao  The data access object for contacts.
     */
    public ContactController(ClientDao clientDao, ContactDao contactDao) {
        this.clientDao = clientDao;
        this.contactDao = contactDao;
    }


    /**
     * Displays the view with contact's details.
     *
     * @param contactId The identifier of the contact.
     * @param clientId  The identifier of the associated client.
     * @param model     The Spring Model for passing data to the view.
     * @return The view displaying the contact details.
     */
    @RequestMapping(("/{clientId}/{contactId}"))
    public String contactView(@PathVariable Long contactId, @PathVariable long clientId, Model model) {
        model.addAttribute("contact",contactDao.findById(contactId));
        model.addAttribute("clientId",clientId).addAttribute("clientName",clientDao.findById(clientId).getFullName());
        return "contactView";
    }

    /**
     * Displays the form for adding a new contact.
     *
     * @param clientId The identifier of the associated client.
     * @return The view displaying the form for adding a new contact.
     */
    @GetMapping("/add/{clientId}")
    public String addContact(@PathVariable Long clientId) {
        return "forms/addContactForm";
    }

    /**
     * Handles the request to add a new contact.
     *
     * @param nameSurname            The name and surname of the contact.
     * @param mobileNumber           The mobile number of the contact.
     * @param phoneNumber            The phone number of the contact.
     * @param position               The position of the contact.
     * @param department             The department of the contact.
     * @param email                  The email address of the contact.
     * @param isContactPerson        The flag indicating if the contact is a primary contact person.
     * @param marketingConsent       The flag indicating marketing consent.
     * @param rodoConsent            The flag indicating consent for processing personal data.
     * @param marketingConsentDate   The date of marketing consent.
     * @param rodoConsentDate        The date of personal data processing consent.
     * @param softwarePatron         The software patron of the contact.
     * @param clientId               The identifier of the associated client.
     * @return A redirect to the client's page after adding the contact.
     */
    @PostMapping("/add/{clientId}")
    public String addContactPerform(@RequestParam String nameSurname, @RequestParam String mobileNumber,
                                     @RequestParam String phoneNumber, @RequestParam String position,
                                     @RequestParam String department, @RequestParam String email,
                                     @RequestParam int isContactPerson, @RequestParam int marketingConsent,
                                     @RequestParam int rodoConsent, @RequestParam String marketingConsentDate,
                                     @RequestParam String rodoConsentDate, @RequestParam String softwarePatron,
                                     @PathVariable Long clientId){


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

    /**
     * Displays the form for editing an existing contact.
     *
     * @param clientId The identifier of the associated client.
     * @param model    The Spring Model for passing data to the view.
     * @param contactId The identifier of the contact to edit.
     * @return The view displaying the form for editing the contact.
     */
    @GetMapping("/edit/{clientId}/{contactId}")
    public String addContact(@PathVariable Long clientId,Model model, @PathVariable Long contactId) {
        model.addAttribute("clientId",clientId);
        model.addAttribute("contact",contactDao.findById(contactId));
        return "forms/editContactForm";
    }

    /**
     * Handles the request to edit an existing contact.
     *
     * @param nameSurname            The updated name and surname of the contact.
     * @param mobileNumber           The updated mobile number of the contact.
     * @param phoneNumber            The updated phone number of the contact.
     * @param position               The updated position of the contact.
     * @param department             The updated department of the contact.
     * @param email                  The updated email address of the contact.
     * @param isContactPerson        The updated flag indicating if the contact is a primary contact person.
     * @param marketingConsent       The updated flag indicating marketing consent.
     * @param rodoConsent            The updated flag indicating consent for processing personal data.
     * @param marketingConsentDate   The updated date of marketing consent.
     * @param rodoConsentDate        The updated date of personal data processing consent.
     * @param softwarePatron         The updated software patron of the contact.
     * @param clientId               The identifier of the associated client.
     * @param contactId              The identifier of the contact being edited.
     * @return A redirect to the client's page after performing the edit.
     */
    @PostMapping("/edit/{clientId}/{contactId}")
    public String editContactPerform(@RequestParam String nameSurname, @RequestParam String mobileNumber,
                                     @RequestParam String phoneNumber, @RequestParam String position,
                                     @RequestParam String department, @RequestParam String email,
                                     @RequestParam int isContactPerson, @RequestParam int marketingConsent,
                                     @RequestParam int rodoConsent, @RequestParam String marketingConsentDate,
                                     @RequestParam String rodoConsentDate, @RequestParam String softwarePatron,
                                     @PathVariable Long clientId, @PathVariable Long contactId){

        LocalDate marketingConsentDateFormatted = null;
        LocalDate rodoConsentDateFormatted = null;
        if (!marketingConsentDate.isEmpty()) {
            marketingConsentDateFormatted = LocalDate.parse(marketingConsentDate,formatter);
        }
        if (!marketingConsentDate.isEmpty()) {
            rodoConsentDateFormatted = LocalDate.parse(rodoConsentDate,formatter);
        }

        Contact contact = contactDao.findById(contactId);

        contact.setNameSurname(nameSurname);
        contact.setMobileNumber(mobileNumber);
        contact.setPhoneNumber(phoneNumber);
        contact.setPosition(position);
        contact.setDepartment(department);
        contact.setEmail(email);
        contact.setIsContactPerson(isContactPerson);
        contact.setMarketingConsent(marketingConsent);
        contact.setRodoConsent(rodoConsent);
        contact.setMarketingConsentDate(marketingConsentDateFormatted);
        contact.setRodoConsentDate(rodoConsentDateFormatted);
        contact.setSoftwarePatron(softwarePatron);

        contactDao.update(contact);

        return "redirect:/clients/" + clientId;
    }

    /**
     * Handles the request to delete a contact.
     *
     * @param contactId The identifier of the contact to delete.
     * @return A redirect to the client's page after deleting the contact.
     */
    @GetMapping("/delete/{contactId}")
    public String deleteContact(@PathVariable long contactId ) {
        String clientId = String.valueOf(contactDao.findById(contactId).getClient().getId());
        contactDao.delete(contactDao.findById(contactId));
        return "redirect:/clients/" + clientId;
    }


}
