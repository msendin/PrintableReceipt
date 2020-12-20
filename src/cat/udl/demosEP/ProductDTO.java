package cat.udl.demosEP;

import java.math.BigDecimal;

public class ProductDTO {

    String productID;
    String description;
    BigDecimal price;



    public ProductDTO (String pID, String des, BigDecimal pr) {
        productID = pID;
        description = des;
        price = pr;
    }



    String getDescription() {return description;}
    BigDecimal getPrice() {return price;}

    void setDescription (String des) {
        description = des;
    }
    void setPrice (BigDecimal pr) {
        price = pr;
    }
}


