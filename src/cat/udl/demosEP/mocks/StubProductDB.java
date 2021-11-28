package cat.udl.demosEP.mocks;

import cat.udl.demosEP.interfaces.ProductDB;
import cat.udl.demosEP.ProductDTO;
import cat.udl.demosEP.exceptions.DoesNotExistException;

import java.math.BigDecimal;

public class StubProductDB implements ProductDB {

    ProductDTO pdDTOkeyb;
    ProductDTO pdDTOcard;

    public StubProductDB() {
        pdDTOkeyb = new ProductDTO("KEYBOARD", "periferico de entrada", new BigDecimal("150"));
        pdDTOcard = new ProductDTO("CARD", "tarjeta de memoria", new BigDecimal("75"));
    }


    @Override
    public ProductDTO getProduct(String productID) throws DoesNotExistException {
        if (productID.equals("KEYBOARD")) {
            return pdDTOkeyb;
        }
        else if (productID.equals("CARD")) {
            return pdDTOcard;
        }
        else throw new DoesNotExistException("Product does not exist at DDBB");
    }
}