public class Laptop extends Computer{
    private String memory;
    private String SSD;
    private String screenSize;

    public Laptop(String category, String type, String ID, String brand, String CPUfamily, String memory, String SSD, String screenSize, float price) {
        super(category, type, ID, brand, CPUfamily, price);
        this.memory = memory;
        this.SSD = SSD;
        this.screenSize = screenSize;
    }

    @Override
    public String toString() {
        return  super.toString() +
                "memory='" + memory + '\'' +
                ", SSD='" + SSD + '\'' +
                ", screenSize=" + screenSize +
                '}';
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getSSD() {
        return SSD;
    }

    public void setSSD(String SSD) {
        this.SSD = SSD;
    }

    public String getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(String screenSize) {
        this.screenSize = screenSize;
    }
}
