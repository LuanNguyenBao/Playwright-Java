package core.enums;

public enum MENU {
    SERVICES("Services"),
    INSIGHTS("Insights"),
    ABOUT("About"),
    CAREERS("Careers"),
    CONTACT_US("Contact us");

    private String name;

    MENU(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
