package app.purchasedProducts;

import app.client.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "purchasedProducts")
public class PurchasedProduct {

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
    private Double price;

    @Column
    private Integer rabat;

    @Column
    private Double priceWithRabat;

    @Column
    private String description;

    @Column
    private String softwarePatron;

    @ToString.Exclude
    @ManyToOne
    private Client client;


}
