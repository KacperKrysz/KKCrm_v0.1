package app.model.activity;

import app.model.client.Client;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Entity class representing an activity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "activities")
public class Activity {

    /**
     * Constructs a new Activity, ID is automatically generated.
     *
     * @param subject        The subject of the activity.
     * @param status         The status of the activity.
     * @param date           The date of the activity.
     * @param priority       The priority of the activity.
     * @param description     The description of the activity.
     * @param contactPerson   The contact person associated with the activity.
     * @param softwarePatron  The software patron of the activity.
     * @param client          The client associated with the activity.
     */
    public Activity(String subject, String status, LocalDate date, String priority, String description, String contactPerson, String softwarePatron, Client client) {
        this.subject = subject;
        this.status = status;
        this.date = date;
        this.priority = priority;
        this.description = description;
        this.contactPerson = contactPerson;
        this.softwarePatron = softwarePatron;
        this.client = client;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String subject;

    @Column
    private String status;

    @Column
    private LocalDate date;

    @Column
    private String priority;

    @Column
    private String description;

    @Column
    private String contactPerson;

//    @ManyToOne
//    private User softwarePatron;

    @Column
    private String softwarePatron;

    @ToString.Exclude
    @ManyToOne
    private Client client;

}
