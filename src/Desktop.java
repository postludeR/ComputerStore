public class Desktop extends Computer{
    private String memory;
    private String SSD;

    public Desktop(String category, String type, String ID, String brand, String CPUfamily, String memory, String SSD, float price) {
        super(category, type, ID, brand, CPUfamily, price);
        this.memory = memory;
        this.SSD = SSD;
    }

    @Override
    public String toString() {
        return super.toString()  +
                "memory='" + memory + '\'' +
                ", SSD='" + SSD + '\'' +
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
}
