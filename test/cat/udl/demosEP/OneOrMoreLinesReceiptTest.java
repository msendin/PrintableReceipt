package cat.udl.demosEP;

import cat.udl.demosEP.exceptions.DoesNotExistException;
import cat.udl.demosEP.exceptions.IsClosedException;
import cat.udl.demosEP.interfaces.ProductDB;
import cat.udl.demosEP.interfaces.ReceiptInterfaceTest;
import cat.udl.demosEP.interfaces.ReceiptPrinter;
import cat.udl.demosEP.interfaces.ReceiptPrinterInterfaceTest;
import cat.udl.demosEP.mocks.MockReceiptPrinter;
import cat.udl.demosEP.mocks.StubProductDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OneOrMoreLinesReceiptTest implements ReceiptInterfaceTest, ReceiptPrinterInterfaceTest {

    Receipt receipt;
    ProductDB prodDB;
    ReceiptPrinter rP;


    @BeforeEach
    void setUp() throws IsClosedException, DoesNotExistException {
        String pID;

        receipt = new Receipt();
        prodDB = new StubProductDB();
        receipt.setProductDB(prodDB);
        rP = new MockReceiptPrinter();
        receipt.setReceiptPrinter(rP);

        pID = "KEYBOARD";
        receipt.addLine(pID, 1);
        pID = "CARD";
        receipt.addLine(pID, 2);
    }

    @Override
    @Test
    public void addLineTest() throws IsClosedException, DoesNotExistException {
        String pID = "KEYBOARD";
        receipt.addLine(pID, 10);
        assertEquals(new BigDecimal("1800"), receipt.getTotal());
    }

    @Override
    @Test
    public void addTaxesTest() throws IsClosedException {
        BigDecimal perc = new BigDecimal("15");
        receipt.addTaxes(perc);
        assertEquals(new BigDecimal("45"), receipt.getTaxes());
        assertEquals(new BigDecimal("345"), receipt.getTotal());
    }

    @Override
    @Test
    public void getIsClosedExceptionTest() {
        assertThrows(IsClosedException.class,
                () -> {
                    BigDecimal perc = new BigDecimal("15");
                    receipt.addTaxes(perc);
                    String pID = "CARD";
                    receipt.addLine(pID, 10);
                    //receipt.addTaxes(perc);
                });
    }

    @Override
    @Test
    public void getDoesNotExistExceptionTest() {
        assertThrows(DoesNotExistException.class,
                () -> {
                    String pID = "LAPTOP";
                    receipt.addLine(pID, 10);
                });
    }

    @Override
    @Test
    public void printReceiptTest() throws IsClosedException, DoesNotExistException {
        String expO;

        BigDecimal perc = new BigDecimal("15");
        receipt.addTaxes(perc);

        receipt.printReceipt();

        expO = "Acme S.A.\n";

        expO += prodDB.getProduct("KEYBOARD").getDescription() + "\t" + 1 + "\t" + new BigDecimal("150").toString() + "\n";
        expO += prodDB.getProduct("CARD").getDescription() + "\t" + 2 + "\t" + new BigDecimal("75").toString();

        expO += "\nTAXAS\t" + receipt.getTaxes();
        expO += "\n-------------------------\n";
        expO += "TOTAL" + "\t" + receipt.getTotal();

        assertEquals(expO, rP.getOutput());
    }
}

