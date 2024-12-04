package cat.udl.demosEP;

class ReceiptLine {
    private final int numUnits;
    private final String productID;

    ReceiptLine (String productID, int num) {
        this.productID = productID;
        numUnits = num;
    }

    int getNumUnits () {
        return numUnits;
    }

    String getProductID() {
        return productID;
    }
}