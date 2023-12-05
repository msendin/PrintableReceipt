package cat.udl.demosEP;

import cat.udl.demosEP.exceptions.DoesNotExistException;
import cat.udl.demosEP.exceptions.IsClosedException;
import cat.udl.demosEP.exceptions.IsNotClosedException;
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

class EmptyReceiptTest implements ReceiptInterfaceTest, ReceiptPrinterInterfaceTest {

    private Receipt receipt;
    private ProductDB prodDB;
    private ReceiptPrinter rP;

    @BeforeEach
    void setUp() {
        receipt = new Receipt();
        prodDB = new StubProductDB();
        receipt.setProductDB(prodDB);
        rP = new MockReceiptPrinter();
        receipt.setReceiptPrinter(rP);
    }

    @Override
    @Test
    public void addLineTest() throws IsClosedException, DoesNotExistException {
        String pID = "KEYBOARD";
        receipt.addLine(pID,10);
        assertEquals(new BigDecimal("1500.00"),receipt.getTotal());
    }

    @Override
    @Test
    public void addTaxesTest() throws IsClosedException {
        BigDecimal perc = new BigDecimal("15");
        receipt.addTaxes(perc);
        assertEquals(new BigDecimal("0.00"),receipt.getTaxes());
        assertEquals(new BigDecimal("0.00"),receipt.getTotal());
    }

    @Override
    @Test
    public void getDoesNotExistExceptionTest() {
        assertThrows(DoesNotExistException.class,
                () -> {
                    String pID = "LAPTOP";
                    receipt.addLine(pID,10);
                });
    }

    @Override
    @Test
    public void printReceiptTest() throws IsClosedException, DoesNotExistException, IsNotClosedException {
        String expO;
        BigDecimal perc = new BigDecimal("15");

        receipt.addTaxes(perc);
        receipt.printReceipt();
        expO = "Acme S.A.";
        expO += "\nTAXES\t" + receipt.getTaxes();
        expO += "\n-------------------------\n";
        expO += "TOTAL" + "\t" + receipt.getTotal();

        assertEquals(expO,rP.getOutput());
    }

    @Override
    @Test
    public void printPlusReceiptTest() throws IsClosedException, DoesNotExistException, IsNotClosedException {
        String expO;
        String pID = "CARD";
        receipt.addLine(pID,10);
        BigDecimal perc = new BigDecimal("15");
        receipt.addTaxes(perc);

        receipt.printReceipt();
        expO = "Acme S.A.\n";
        expO += prodDB.getProduct("CARD").getDescription() + "\t" + 10 + "\t" + prodDB.getProduct("CARD").getPrice();
        expO += "\nTAXES\t" + receipt.getTaxes();
        expO += "\n-------------------------\n";
        expO += "TOTAL" + "\t" + receipt.getTotal();

        assertEquals(expO,rP.getOutput());
    }

    @Override
    @Test
    public void getIsNotClosedExceptionTest() {
        assertThrows(IsNotClosedException.class,
                () -> receipt.printReceipt());
    }
}
