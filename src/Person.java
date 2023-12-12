public abstract class Person {
    private String username;
    private String password;
    protected boolean edit;

    public Person(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEdit() {
        return edit;
    }
}
