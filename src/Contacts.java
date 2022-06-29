public class Contacts {
    private String name;
    private String phoneNumber;

    public Contacts(String name,String number){
        this.name = name;
        this.phoneNumber = number;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
