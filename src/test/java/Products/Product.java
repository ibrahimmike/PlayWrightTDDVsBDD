package Products;

public class Product implements Comparable<Product>{
    private String productName = null;
    private String productDescription = null;

    private double productPrice = 0.0;

    private int quantity = 0;

    public Product(String productName, String productDescription, double productPrice){
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.quantity = 1; // it is a default value as the service doesn't allow the increase or decrease the amounts of quantities.
    }


    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public int compareTo(Product o) {
        if(this.getProductName().compareToIgnoreCase(o.getProductName())==0
                && this.productDescription.compareToIgnoreCase(o.productDescription)==0
                && this.getProductPrice() == o.getProductPrice()){

            return 1;
        }else {

            return 0;
        }
    }
}
