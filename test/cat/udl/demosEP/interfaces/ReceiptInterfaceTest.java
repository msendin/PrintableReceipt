package cat.udl.demosEP.interfaces;

import cat.udl.demosEP.exceptions.DoesNotExistException;
import cat.udl.demosEP.exceptions.IsClosedException;
import org.junit.jupiter.api.Test;

public interface ReceiptInterfaceTest {

    @Test
    void addLineTest() throws IsClosedException, DoesNotExistException;

    @Test
    void addTaxesTest() throws IsClosedException;

    @Test
    void getIsClosedExceptionTest();

    @Test
    void getDoesNotExistExceptionTest();
}
