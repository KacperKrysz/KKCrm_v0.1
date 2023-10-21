package app.controllers;

import app.model.activity.Activity;
import app.model.activity.ActivityDao;
import app.model.client.ClientDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Controller responsible for viewing, editing, deleting activities
 */
@Controller
@RequestMapping("/activity")
public class ActivityController {

    private final ActivityDao activityDao;
    private final ClientDao clientDao;

    /**
     * Constructs an ActivityController with the specified data access objects.
     *
     * @param activityDao The data access object for activities.
     * @param clientDao The data access object for clients.
     */
    public ActivityController(ActivityDao activityDao, ClientDao clientDao) {
        this.activityDao = activityDao;
        this.clientDao = clientDao;
    }

    /**
     * Handles the request to display the activity view.
     *
     * @param activityId The identifier of the activity.
     * @param clientId The identifier of the client.
     * @param model The Spring Model that allows passing data to the view.
     * @return The activity view.
     */
    @RequestMapping(("/{clientId}/{activityId}"))
    public String activityView(@PathVariable Long activityId, @PathVariable long clientId, Model model) {
        model.addAttribute("activity",activityDao.findById(activityId));
        model.addAttribute("clientId",clientId).addAttribute("clientName",clientDao.findById(clientId).getFullName());
        return "activityView";
    }

    /**
     * Handles the request to display the add activity form.
     *
     * @param clientId The identifier of the client for whom the activity is added.
     * @return The add activity form view.
     */
    @GetMapping("/addActivity/{clientId}")
    public String addActivity(@PathVariable Long clientId) {

        return "forms/addActivityForm";
    }

    /**
     * Handles the request to add a new activity.
     *
     * @param subject The subject of the activity.
     * @param status The status of the activity.
     * @param date The date of the activity in "yyyy-MM-dd" format.
     * @param priority The priority of the activity.
     * @param description The description of the activity.
     * @param contactPerson The contact person for the activity.
     * @param softwarePatron The software patron for the activity.
     * @param clientId The identifier of the client for whom the activity is added.
     * @return Redirects to the client's page.
     */
    @PostMapping("/addActivity/{clientId}")
    public String addActivityPerform(@RequestParam String subject, @RequestParam String status,
                                     @RequestParam String date, @RequestParam String priority,
                                     @RequestParam String description, @RequestParam String contactPerson,
                                     @RequestParam String softwarePatron, @PathVariable Long clientId){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


        Activity activity = new Activity(subject,status, LocalDate.parse(date,formatter),priority,description,
                contactPerson,softwarePatron,clientDao.findById(clientId));
        activityDao.saveActivity(activity);

        return "redirect:/clients/" + clientId;
    }

    /**
     * Handles the request to display the edit activity form.
     *
     * @param model The Spring Model for passing data to the view.
     * @param activityId The identifier of the activity to be edited.
     * @param clientId The identifier of the client associated with the activity.
     * @return The edit activity form view.
     */

    @GetMapping("/edit/{clientId}/{activityId}")
    public String editActivity(Model model,@PathVariable Long activityId,@PathVariable Long clientId) {
        model.addAttribute("clientId", clientId);
        model.addAttribute("activity",activityDao.findById(activityId));

        return "forms/editActivityForm";
    }

    /**
     * Handles the request to perform the edit of an existing activity.
     *
     * @param subject The updated subject of the activity.
     * @param status The updated status of the activity.
     * @param date The updated date of the activity in "yyyy-MM-dd" format.
     * @param priority The updated priority of the activity.
     * @param description The updated description of the activity.
     * @param contactPerson The updated contact person for the activity.
     * @param softwarePatron The updated software patron for the activity.
     * @param clientId The identifier of the client associated with the activity.
     * @param activityId The identifier of the activity to be edited.
     * @return Redirects to the client's page after the edit is performed.
     */
    @PostMapping("/edit/{clientId}/{activityId}")
    public String editActivityPerform(@RequestParam String subject, @RequestParam String status,
                                     @RequestParam String date, @RequestParam String priority,
                                     @RequestParam String description, @RequestParam String contactPerson,
                                     @RequestParam String softwarePatron, @PathVariable Long clientId, @PathVariable Long activityId){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


        Activity activity = activityDao.findById(activityId);

        activity.setSubject(subject);
        activity.setStatus(status);
        activity.setDate(LocalDate.parse(date,formatter));
        activity.setPriority(priority);
        activity.setDescription(description);
        activity.setContactPerson(contactPerson);
        activity.setSoftwarePatron(softwarePatron);

        activityDao.update(activity);

        return "redirect:/clients/" + clientId;
    }

    /**
     * Handles the request to delete an activity.
     *
     * @param activityId The identifier of the activity to be deleted.
     * @return Redirects to the client's page.
     */
    @GetMapping("/delete/{activityId}")
    public String deleteActivity(@PathVariable long activityId ) {
        String clientId = String.valueOf(activityDao.findById(activityId).getClient().getId());
        activityDao.delete(activityDao.findById(activityId));
        return "redirect:/clients/" + clientId;
    }


}
