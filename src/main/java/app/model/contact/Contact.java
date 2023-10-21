package app.model.contact;

import app.model.client.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Represents a contact entity in the application.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contacts")
public class Contact {

    /**
     * Creates a new contact, ID is automatically generated.
     *
     * @param nameSurname         The name and surname of the contact person.
     * @param mobileNumber        The mobile phone number of the contact person.
     * @param phoneNumber         The phone number of the contact person.
     * @param position            The position or role of the contact person.
     * @param department          The department or division where the contact person works.
     * @param email               The email address of the contact person.
     * @param isContactPerson     Indicates whether the contact person is the main contact (1 for yes, 0 for no).
     * @param marketingConsent    Indicates whether marketing consent is given (1 for yes, 0 for no).
     * @param rodoConsent         Indicates whether GDPR consent is given (1 for yes, 0 for no).
     * @param marketingConsentDate The date when marketing consent was given.
     * @param rodoConsentDate      The date when GDPR consent was given.
     * @param softwarePatron      The software patron or sponsor associated with the contact.
     * @param client              The client associated with the contact.
     */
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
