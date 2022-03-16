package contactmgr;

public class Contact {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

    public Contact() {

    }


    public static void main(String[] args) {
       Contact firstContact =
               new Contact("trevor","esparza","1234567890","esptrev@gmail.com");

       firstContact.sayHello();
       System.out.println(firstContact.getPhoneNumber());
       firstContact.setPhoneNumber("2345678901");
       System.out.println(firstContact.getPhoneNumber());
    }///END OF MAIN

    public void sayHello() {
        System.out.println("Hello my name is " + getFirstName());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Contact(String fName, String lName, String phoneNumber, String email) {
        this.firstName = fName;
        this.lastName = lName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}/// END OF CLASS
