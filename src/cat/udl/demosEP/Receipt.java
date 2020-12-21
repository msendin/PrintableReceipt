package cat.udl.demosEP;

import cat.udl.demosEP.exceptions.DoesNotExistException;
import cat.udl.demosEP.exceptions.IsClosedException;
import cat.udl.demosEP.interfaces.ProductDB;
import cat.udl.demosEP.interfaces.ReceiptPrinter;
import cat.udl.demosEP.mocks.StubProductDB;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

class Receipt {

    private List<ReceiptLine> listOfProducts;
    private BigDecimal total;
    private BigDecimal taxes;
    private boolean isClosed;

    private ProductDB prodDB;
    private ReceiptPrinter printer;

    Receipt() {
        listOfProducts = new ArrayList<>();
        total = new BigDecimal("0");
        isClosed = false;
    }

    public void setReceiptPrinter(ReceiptPrinter p) {
        printer = p;
    }

    public void setProductDB(ProductDB pDB) {
        prodDB = pDB;
    }

    public void addLine(String productID, int numUnits) throws IsClosedException, DoesNotExistException {
        BigDecimal price;
        ReceiptLine rcline;

        if (isClosed)
            throw new IsClosedException ("Recibo ya cerrado");
        else {
            price = prodDB.getPrice(productID);
            rcline = new ReceiptLine(productID, numUnits);
            listOfProducts.add(rcline);
            total = total.add(price.multiply(BigDecimal.valueOf(rcline.getNumUnits())));
        }
    }

    public void addTaxes(BigDecimal percent) throws IsClosedException {
        if (isClosed)
            throw new IsClosedException ("Recibo ya cerrado");
        else {
            BigDecimal cent = new BigDecimal("100");
            taxes = total.multiply(percent).divide(cent,2, RoundingMode.CEILING);
            total = total.add(taxes);
            isClosed = true;
        }
    }

    BigDecimal getTotal() {
        return total;
    }


    BigDecimal getTaxes() {
        return taxes;
    }


    void printReceipt() throws DoesNotExistException {
        String pID;

        printer.init();
        for (ReceiptLine product : listOfProducts) {
            pID = product.getProductID();
            printer.addProduct(prodDB.getProduct(pID).getDescription(), product.getNumUnits(), prodDB.getPrice(pID));
        }
        printer.addTaxes(taxes);
        printer.print(total);
    }
}