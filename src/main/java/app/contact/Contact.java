package app.contact;

import app.activity.Activity;
import app.client.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contacts")
public class Contact {

    public Contact(String nameSurname, String mobileNumber, String phoneNumber, String position,
                   String department, String email, int isContactPerson, int marketingConsent,
                   int rodoConsent, LocalDate marketingConsentDate, LocalDate rodoConsentDate,
                   String softwarePatron, Client client) {
        this.nameSurname = nameSurname;
        this.mobileNumber = mobileNumber;
        this.phoneNumber = phoneNumber;
        this.position = position;
        this.department = department;
        this.email = email;
        this.isContactPerson = isContactPerson;
        this.marketingConsent = marketingConsent;
        this.rodoConsent = rodoConsent;
        this.marketingConsentDate = marketingConsentDate;
        this.rodoConsentDate = rodoConsentDate;
        this.softwarePatron = softwarePatron;
        this.client = client;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String nameSurname;

    @Column
    private String mobileNumber;

    @Column
    private String phoneNumber;

    @Column
    private String position;

    @Column
    private String department;

    @Column
    private String email;


    @Column
    @Range(min = 0,max = 1)
    private int isContactPerson;

    @Column
    @Range(min = 0,max = 1)
    private int marketingConsent;

    @Column
    @Range(min = 0,max = 1)
    private int rodoConsent;

    @Column
    private LocalDate marketingConsentDate;

    @Column
    private LocalDate rodoConsentDate;

    @Column
    private String softwarePatron;

    @ToString.Exclude
    @ManyToOne
    private Client client;

}
