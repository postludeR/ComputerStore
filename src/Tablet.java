public class Tablet extends Computer{
    private String screenSize;

    public Tablet(String category, String type, String ID, String brand, String CPUfamily, String screenSize, float price) {
        super(category, type, ID, brand, CPUfamily, price);
        this.screenSize = screenSize;
    }

    @Override
    public String toString() {
        return super.toString() +
                "screenSize=" + screenSize +
                '}';
    }

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }
}
