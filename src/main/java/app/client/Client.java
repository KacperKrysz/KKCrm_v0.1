package app.client;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Scope(value = WebApplicationContext.SCOPE_SESSION,proxyMode = ScopedProxyMode.TARGET_CLASS)
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "fullName", length = 100)
    private String fullName;

    @Column(name = "shortName", length = 100)
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

    @Column(name = "softwarePatron", length = 100)
    private String softwarePatron;

    @Column(name = "additionalInfo", length = 100)
    private String additionalInfo;


    @Range(min = 0,max = 1)
    @Column(name = "haspqfmea")
    private int haspqfmea;

    @Column(name = "pqfmeaUpdateDate")
    private LocalDateTime pqfmeaUpdateDate;

    @Range(min = 0,max = 1)
    @Column(name = "haspqfmeaPlus")
    private int haspqfmeaPlus;

    @Column(name = "pqfmeaPlusUpdateDate")
    private LocalDateTime pqfmeaPlusUpdateDate;

    @Range(min = 0,max = 1)
    @Column(name = "haspqmsa")
    private int haspqmsa;

    @Column(name = "pqmsaUpdateDate")
    private LocalDateTime pqmsaUpdateDate;

    @Range(min = 0,max = 1)
    @Column(name = "needManualUpdate")
    private int needManualUpdate;

    @Column(name = "creationDate")
    private LocalDateTime creationDate;

    @Column(name = "modificationDate")
    private LocalDateTime modificationDate;

    @PrePersist
    public void prePersist() {
        creationDate = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        modificationDate = LocalDateTime.now();
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setProperties(String fullName, String shortName, String type, String industry,
                  String address, String nip, String source, String contactPerson,
                  String trainingPatron, String softwarePatron, String additionalInfo,
                  int haspqfmea, LocalDateTime pqfmeaUpdateDate, int haspqfmeaPlus,
                  LocalDateTime pqfmeaPlusUpdateDate, int haspqmsa, LocalDateTime pqmsaUpdateDate,
                  int needManualUpdate) {
        this.fullName = fullName;
        this.shortName = shortName;
        this.type = type;
        this.industry = industry;
        this.address = address;
        this.nip = nip;
        this.source = source;
        this.contactPerson = contactPerson;
        this.trainingPatron = trainingPatron;
        this.softwarePatron = softwarePatron;
        this.additionalInfo = additionalInfo;
        this.haspqfmea = haspqfmea;
        this.pqfmeaUpdateDate = pqfmeaUpdateDate;
        this.haspqfmeaPlus = haspqfmeaPlus;
        this.pqfmeaPlusUpdateDate = pqfmeaPlusUpdateDate;
        this.haspqmsa = haspqmsa;
        this.pqmsaUpdateDate = pqmsaUpdateDate;
        this.needManualUpdate = needManualUpdate;
    }
}
