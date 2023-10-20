package app.activity;

import app.client.ClientDao;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/activity")
public class ActivityController {

    private final ActivityDao activityDao;
    private final ClientDao clientDao;

    public ActivityController(ActivityDao activityDao, ClientDao clientDao) {
        this.activityDao = activityDao;
        this.clientDao = clientDao;
    }

    @RequestMapping(("/{clientId}/{activityId}"))
    public String activityView(@PathVariable Long activityId, @PathVariable long clientId, Model model) {
        model.addAttribute("activity",activityDao.findById(activityId));
        model.addAttribute("clientId",clientId).addAttribute("clientName",clientDao.findById(clientId).getFullName());
        return "activityView";
    }


    @GetMapping("/delete/{activityId}")
    public String deleteActivity(@PathVariable long activityId ) {
        String clientId = String.valueOf(activityDao.findById(activityId).getClient().getId());
        activityDao.delete(activityDao.findById(activityId));
        return "redirect:/clients/" + clientId;
    }

    @GetMapping("/addActivity/{clientId}")
    public String addActivity() {
        return "forms/addActivityForm";
    }

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



    @GetMapping("/edit/{clientId}/{activityId}")
    public String editActivity(Model model,@PathVariable Long activityId) {
        model.addAttribute("activity",activityDao.findById(activityId));

        return "forms/editActivityForm";
    }

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



}
