package cat.udl.demosEP.interfaces;

import cat.udl.demosEP.ProductDTO;
import cat.udl.demosEP.exceptions.DoesNotExistException;

import java.math.BigDecimal;

public interface ProductDB {
    BigDecimal getPrice (String productID) throws DoesNotExistException;
    ProductDTO getProduct(String productID) throws DoesNotExistException;
}
