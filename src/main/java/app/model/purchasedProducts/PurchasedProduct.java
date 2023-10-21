package app.model.purchasedProducts;

import app.model.client.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDate;

/**
 * Entity class representing a purchased product in the system.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "purchasedProducts")
public class PurchasedProduct {

    /**
     * Creates a new instance of a purchased product, ID is automatically generated.
     *
     * @param productType         The type of the purchased product.
     * @param purchaseDate        The date of purchase.
     * @param pqfmeaUpdateDate    The date of PQFMEA (Potential Failure Modes and Effects Analysis) update.
     * @param pqmsaUpdateDate     The date of PQMSA (Product Quality Monitoring and System Assessment) update.
     * @param licenseSeatNumber   The license seat number.
     * @param price               The price of the purchased product.
     * @param rabat               The discount (rabat) applied to the product.
     * @param priceWithRabat      The price of the product with the applied discount.
     * @param description         Additional description or notes about the product.
     * @param softwarePatron      The patron of the software associated with the product.
     * @param client              The client to which the product is associated.
     */
    public PurchasedProduct(String productType, LocalDate purchaseDate, LocalDate pqfmeaUpdateDate,
                            LocalDate pqmsaUpdateDate, String licenseSeatNumber, Double price, Integer rabat,
                            Double priceWithRabat, String description, String softwarePatron, Client client) {

        this.productType = productType;
        this.purchaseDate = purchaseDate;
        this.pqfmeaUpdateDate = pqfmeaUpdateDate;
        this.pqmsaUpdateDate = pqmsaUpdateDate;
        this.licenseSeatNumber = licenseSeatNumber;
        this.price = price;
        this.rabat = rabat;
        this.priceWithRabat = priceWithRabat;
        this.description = description;
        this.softwarePatron = softwarePatron;
        this.client = client;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String productType;

    @Column
    private LocalDate purchaseDate;

    @Column
    private LocalDate pqfmeaUpdateDate;

    @Column
    private LocalDate pqmsaUpdateDate;

    @Column
    private String licenseSeatNumber;

    @Column
    @Min(value = 0, message = "Cena nie może być ujemna")
    private Double price;

    @Column
    @Min(value = 0, message = "Rabat nie może być ujemny")
    private Integer rabat;

    @Column
    @Min(value = 0, message = "Cena z rabatem nie może być ujemna")
    private Double priceWithRabat;

    @Column
    private String description;

    @Column
    private String softwarePatron;

    @ToString.Exclude
    @ManyToOne
    private Client client;


}
