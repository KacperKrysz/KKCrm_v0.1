package app.purchasedProducts;

import app.activity.Activity;
import app.client.ClientDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/pproduct")
public class PurchasedProductController {

    private final PurchasedProductDao purchasedProductDao;
    private final ClientDao clientDao;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    LocalDate purchaseDateFormatted = null;
    LocalDate pqfmeaUpdateDateFormatted = null;
    LocalDate pqmsaUpdateDateFormatted = null;

    public PurchasedProductController(PurchasedProductDao purchasedProductDao, ClientDao clientDao) {
        this.purchasedProductDao = purchasedProductDao;
        this.clientDao = clientDao;
    }

    @RequestMapping(("/{clientId}/{purchasedProductId}"))
    public String activityView(@PathVariable Long purchasedProductId, @PathVariable long clientId, Model model) {
        model.addAttribute("purchasedProduct",purchasedProductDao.findById(purchasedProductId));
        model.addAttribute("clientId",clientId).addAttribute("clientName",clientDao.findById(clientId).getFullName());
        return "purchasedProductView";
    }


    @GetMapping("/delete/{purchasedProductId}")
    public String deleteActivity(@PathVariable long purchasedProductId ) {
        String clientId = String.valueOf(purchasedProductDao.findById(purchasedProductId).getClient().getId());
        purchasedProductDao.delete(purchasedProductDao.findById(purchasedProductId));
        return "redirect:/clients/" + clientId;
    }

    @GetMapping("/add/{clientId}")
    public String addPProduct() {
        return "forms/addPurchasedProductForm";
    }

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



    @GetMapping("/edit/{clientId}/{purchasedProductId}")
    public String editPProduct(Model model,@PathVariable Long purchasedProductId) {
        model.addAttribute("activity",purchasedProductDao.findById(purchasedProductId));
        return "forms/editPurchasedProductForm";
    }

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




}
