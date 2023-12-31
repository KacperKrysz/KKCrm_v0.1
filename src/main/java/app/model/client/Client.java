package app.model.client;

import app.model.activity.Activity;
import app.model.contact.Contact;
import app.model.purchasedProducts.PurchasedProduct;
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

/**
 * Entity class representing a client in the application.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clients")
public class Client {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String fullName;

    @Column(unique = true)
    private String shortName;

    @Column(name = "type", length = 100)
    private String type;

    @Column(name = "industry", length = 100)
    private String industry;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "nip", length = 100)
    private String nip;

    @Column(name = "source", length = 100)
    private String source;

    @Column(name = "contactPerson", length = 100)
    private String contactPerson;

    @Column(name = "trainingPatron", length = 100)
    private String trainingPatron;

    @Column
    private String softwarePatron;

//    @ManyToOne
//    private User softwarePatron;

    @Column(name = "additionalInfo", length = 100)
    private String additionalInfo;


    @Range(min = 0,max = 1)
    @Column(name = "haspqfmea")
    private Integer haspqfmea;

    @Column(name = "pqfmeaUpdateDate")
    private LocalDate pqfmeaUpdateDate;

    @Range(min = 0,max = 1)
    @Column(name = "haspqfmeaPlus")
    private Integer haspqfmeaPlus;

    @Column
    private LocalDate pqfmeaPlusUpdateDate;

    @Range(min = 0,max = 1)
    @Column
    private Integer haspqmsa;

    @Column
    private LocalDate pqmsaUpdateDate;

    @Range(min = 0,max = 1)
    @Column
    private Integer needManualUpdate;

    @Column
    private LocalDateTime creationDate;

    @Column(name = "modificationDate")
    private LocalDateTime modificationDate;

    @ToString.Exclude
    @OneToMany(mappedBy = "client")
    private List<Activity> activities = new ArrayList<>();


    @ToString.Exclude
    @OneToMany(mappedBy = "client")
    private List<Contact> contacts = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "client")
    private List<PurchasedProduct> purchasedProducts = new ArrayList<>();


    /**
     * Initializes the creation date before persisting to the database.
     */
    @PrePersist
    public void prePersist() {
        creationDate = LocalDateTime.now();
    }

    /**
     * Updates the modification date before updating the database.
     */
    @PreUpdate
    public void preUpdate() {
        modificationDate = LocalDateTime.now();
    }


}
