package cat.udl.demosEP.mocks;

import cat.udl.demosEP.interfaces.ReceiptPrinter;

import java.math.BigDecimal;

public class MockReceiptPrinter implements ReceiptPrinter {

    String output;

    public MockReceiptPrinter() {
        output = "";
    }

    @Override
    public void init() {
        output = "Acme S.A.\n";
        System.out.println("Acme S.A.\n");
    }

    @Override
    public void addProduct(String description, int quantity, BigDecimal price) {
        output += description + "\t" + quantity + "\t" + price + "\n";
        System.out.println(description + "\t" + quantity + "\t" + price);
    }

    @Override
    public void addTaxes(BigDecimal taxes) {
        output += "TAXES" + "\t" + taxes + "\n";
        System.out.println("TAXES" + "\t" + taxes);
    }

    @Override
    public void print(BigDecimal total) {
        output += "-------------------------" + "\n";
        output += "TOTAL" + "\t" + total;
        System.out.println("-------------------------");
        System.out.println("TOTAL" + "\t" + total);
    }

    @Override
    public String getOutput() {
        return output;
    }
}
