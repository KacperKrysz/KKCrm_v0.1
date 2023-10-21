package app.controllers;

import app.model.client.Client;
import app.model.client.ClientDao;
import app.model.purchasedProducts.PurchasedProduct;
import app.model.purchasedProducts.PurchasedProductDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Controller responsible for managing purchased products.
 */
@Controller
@RequestMapping("/pproduct")
public class PurchasedProductController {

    private final PurchasedProductDao purchasedProductDao;
    private final ClientDao clientDao;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    LocalDate purchaseDateFormatted = null;
    LocalDate pqfmeaUpdateDateFormatted = null;
    LocalDate pqmsaUpdateDateFormatted = null;

    /**
     * Constructs a new PurchasedProductController.
     *
     * @param purchasedProductDao The data access object for purchased products.
     * @param clientDao           The data access object for clients.
     */
    public PurchasedProductController(PurchasedProductDao purchasedProductDao, ClientDao clientDao) {
        this.purchasedProductDao = purchasedProductDao;
        this.clientDao = clientDao;
    }

    /**
     * Displays the view for a specific purchased product.
     *
     * @param purchasedProductId The identifier of the purchased product.
     * @param clientId          The identifier of the client associated with the purchased product.
     * @param model             The Spring Model for passing data to the view.
     * @return The purchased product view.
     */
    @RequestMapping(("/{clientId}/{purchasedProductId}"))
    public String activityView(@PathVariable Long purchasedProductId, @PathVariable long clientId, Model model) {
        model.addAttribute("purchasedProduct",purchasedProductDao.findById(purchasedProductId));
        model.addAttribute("clientId",clientId).addAttribute("clientName",clientDao.findById(clientId).getFullName());
        return "purchasedProductView";
    }

    /**
     * Displays the form for adding a new purchased product.
     *
     * @param clientId The identifier of the client for which the purchased product will be added.
     * @return The add purchased product form.
     */
    @GetMapping("/add/{clientId}")
    public String addPProduct(@PathVariable Long clientId) {
        return "forms/addPurchasedProductForm";
    }

    /**
     * Performs the addition of a new purchased product.
     *
     * @param productType          The type of the purchased product.
     * @param purchaseDate         The purchase date of the product.
     * @param pqfmeaUpdateDate     The update date for PQ-FMEA.
     * @param pqmsaUpdateDate      The update date for PQ-MSA.
     * @param licenseSeatNumber    The license seat number.
     * @param price                The price of the product.
     * @param rabat                The discount value.
     * @param priceWithRabat       The price with discount applied.
     * @param description          Additional description of the product.
     * @param softwarePatron       The software patron.
     * @param clientId             The identifier of the client to which the product is associated.
     * @return A redirection to the client's page.
     */
    @PostMapping("/add/{clientId}")
    public String addPProductPerform(@RequestParam String productType, @RequestParam String purchaseDate,
                                     @RequestParam String pqfmeaUpdateDate, @RequestParam String pqmsaUpdateDate,
                                     @RequestParam String licenseSeatNumber, @RequestParam String price,
                                     @RequestParam String rabat, @RequestParam String priceWithRabat,
                                     @RequestParam String description, @RequestParam String softwarePatron, @PathVariable Long clientId){


        if (!purchaseDate.isEmpty()) {
            purchaseDateFormatted = LocalDate.parse(purchaseDate,formatter);
        }

        if (!pqfmeaUpdateDate.isEmpty()) {
            pqfmeaUpdateDateFormatted = LocalDate.parse(pqfmeaUpdateDate,formatter);
        }

        if (!pqmsaUpdateDate.isEmpty()) {
            pqmsaUpdateDateFormatted = LocalDate.parse(pqmsaUpdateDate,formatter);
        }

        if (productType.equals("PQ-FMEA+")) {
            Client client = clientDao.findById(clientId);
            client.setHaspqfmeaPlus(1);
            client.setPqfmeaPlusUpdateDate(pqfmeaUpdateDateFormatted);
            clientDao.update(client);
        }


        if (productType.equals("PQ-MSA+")) {
            Client client = clientDao.findById(clientId);
            client.setHaspqmsa(1);
            client.setPqmsaUpdateDate(pqmsaUpdateDateFormatted);
            clientDao.update(client);

        }


        if (productType.equals("PQ-FMEA")) {
            Client client = clientDao.findById(clientId);
            client.setHaspqfmea(1);
            client.setPqfmeaUpdateDate(pqfmeaUpdateDateFormatted);
            clientDao.update(client);

        }

        Integer rabatValue = (rabat.isEmpty()) ? null : Integer.valueOf(rabat);
        Double priceWithRabatValue = (priceWithRabat.isEmpty()) ? null : Double.valueOf(priceWithRabat);

        PurchasedProduct purchasedProduct = new PurchasedProduct(productType,purchaseDateFormatted,pqfmeaUpdateDateFormatted,
                pqmsaUpdateDateFormatted,licenseSeatNumber, Double.valueOf(price),
                rabatValue,
                priceWithRabatValue,
                description,softwarePatron, clientDao.findById(clientId));
        purchasedProductDao.savePurchasedProduct(purchasedProduct);

        return "redirect:/clients/" + clientId;
    }

    /**
     * Displays the form for editing an existing purchased product.
     *
     * @param clientId           The identifier of the client to which the purchased product is associated.
     * @param purchasedProductId  The identifier of the purchased product to be edited.
     * @param model              The Spring Model for passing data to the view.
     * @return The edit purchased product form.
     */
    @GetMapping("/edit/{clientId}/{purchasedProductId}")
    public String editPProduct(Model model,@PathVariable Long purchasedProductId,@PathVariable Long clientId) {
        model.addAttribute("clientId",clientId);
        model.addAttribute("purchasedProduct",purchasedProductDao.findById(purchasedProductId));
        return "forms/editPurchasedProductForm";
    }

    /**
     * Performs the update of an existing purchased product.
     *
     * @param productType          The type of the purchased product.
     * @param purchaseDate         The purchase date of the product.
     * @param pqfmeaUpdateDate     The update date for PQ-FMEA.
     * @param pqmsaUpdateDate      The update date for PQ-MSA.
     * @param licenseSeatNumber    The license seat number.
     * @param price                The price of the product.
     * @param rabat                The discount value.
     * @param priceWithRabat       The price with discount applied.
     * @param description          Additional description of the product.
     * @param softwarePatron       The software patron.
     * @param clientId             The identifier of the client to which the product is associated.
     * @param purchasedProductId   The identifier of the purchased product to be updated.
     * @return A redirection to the client's page.
     */
    @PostMapping("/edit/{clientId}/{purchasedProductId}")
    public String editPProductPerform(@RequestParam String productType, @RequestParam String purchaseDate,
                                      @RequestParam String pqfmeaUpdateDate, @RequestParam String pqmsaUpdateDate,
                                      @RequestParam String licenseSeatNumber, @RequestParam String price,
                                      @RequestParam String rabat, @RequestParam String priceWithRabat,
                                      @RequestParam String description, @RequestParam String softwarePatron,
                                      @PathVariable Long clientId, @PathVariable Long purchasedProductId){

        if (!purchaseDate.isEmpty()) {
            purchaseDateFormatted = LocalDate.parse(purchaseDate,formatter);
        }

        if (!pqfmeaUpdateDate.isEmpty()) {
            pqfmeaUpdateDateFormatted = LocalDate.parse(pqfmeaUpdateDate,formatter);
        }

        if (!pqmsaUpdateDate.isEmpty()) {
            pqmsaUpdateDateFormatted = LocalDate.parse(pqmsaUpdateDate,formatter);
        }

        Integer rabatValue = (rabat.isEmpty()) ? null : Integer.valueOf(rabat);
        Double priceWithRabatValue = (priceWithRabat.isEmpty()) ? null : Double.valueOf(priceWithRabat);



        PurchasedProduct purchasedProduct = purchasedProductDao.findById(purchasedProductId);

        purchasedProduct.setProductType(productType);
        purchasedProduct.setPurchaseDate(purchaseDateFormatted);
        purchasedProduct.setPqfmeaUpdateDate(pqfmeaUpdateDateFormatted);
        purchasedProduct.setPqmsaUpdateDate(pqmsaUpdateDateFormatted);
        purchasedProduct.setLicenseSeatNumber(licenseSeatNumber);
        purchasedProduct.setPrice(Double.valueOf(price));
        purchasedProduct.setRabat(rabatValue);
        purchasedProduct.setPriceWithRabat(priceWithRabatValue);
        purchasedProduct.setDescription(description);
        purchasedProduct.setSoftwarePatron(softwarePatron);

        purchasedProductDao.update(purchasedProduct);

        return "redirect:/clients/" + clientId;
    }

    /**
     * Deletes a purchased product.
     *
     * @param purchasedProductId The identifier of the purchased product to be deleted.
     * @return A redirection to the client's page.
     */
    @GetMapping("/delete/{purchasedProductId}")
    public String deleteActivity(@PathVariable long purchasedProductId ) {
        String clientId = String.valueOf(purchasedProductDao.findById(purchasedProductId).getClient().getId());
        purchasedProductDao.delete(purchasedProductDao.findById(purchasedProductId));
        return "redirect:/clients/" + clientId;
    }


}
