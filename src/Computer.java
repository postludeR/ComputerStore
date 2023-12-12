public abstract class Computer {
    private String category;
    private String type;
    private String ID;
    private String brand;
    private String CPUfamily;
    private float price;

    public Computer(String category, String type, String ID, String brand, String CPUfamily, float price) {
        this.category = category;
        this.type = type;
        this.ID = ID;
        this.brand = brand;
        this.CPUfamily = CPUfamily;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", ID='" + ID + '\'' +
                ", brand='" + brand + '\'' +
                ", CPUfamily='" + CPUfamily + '\'' +
                ", price=" + price +
                '}';
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCPUfamily() {
        return CPUfamily;
    }

    public void setCPUfamily(String CPUfamily) {
        this.CPUfamily = CPUfamily;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}