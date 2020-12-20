package cat.udl.demosEP.interfaces;

import cat.udl.demosEP.exceptions.DoesNotExistException;
import cat.udl.demosEP.exceptions.IsClosedException;
import org.junit.jupiter.api.Test;

public interface ReceiptPrinterInterfaceTest {
    @Test
    void printReceiptTest() throws IsClosedException, DoesNotExistException;
}
