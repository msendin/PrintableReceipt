package cat.udl.demosEP.mocks;

import cat.udl.demosEP.interfaces.ProductDB;
import cat.udl.demosEP.ProductDTO;
import cat.udl.demosEP.exceptions.DoesNotExistException;

import java.math.BigDecimal;

public class StubProductDB implements ProductDB {

    @Override
    public BigDecimal getPrice (String productID) throws DoesNotExistException {
        if (productID.equals("KEYBOARD"))
            return new BigDecimal("150");
        else if (productID.equals("CARD"))
            return new BigDecimal("75");
        else {
            throw new DoesNotExistException("Product does not exist at DDBB");
        }
    }

    @Override
    public ProductDTO getProduct(String productID) throws DoesNotExistException {
        ProductDTO pdDTO;
        if (productID.equals("KEYBOARD")) {
            pdDTO = new ProductDTO("KEYBOARD", "periferico de entrada", new BigDecimal("150"));
            return pdDTO;
        }
        else if (productID.equals("CARD")) {
            pdDTO = new ProductDTO("CARD", "tarjeta de memoria", new BigDecimal("75"));
            return pdDTO;
        }
        else throw new DoesNotExistException("Product does not exist at DDBB");
    }
}