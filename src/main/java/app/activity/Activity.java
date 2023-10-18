package app.activity;

import app.client.Client;
import app.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "activities")
public class Activity {

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
