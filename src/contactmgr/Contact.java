package contactmgr;

public class Contact {
        private String contactInfo;

    public static void main(String[] args) {
        Contact firstContact = new Contact("korbin");
        System.out.print(firstContact + "says hello");
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public Contact(String contactData) {
        setContactInfo(contactData);
    }
}/// END OF CLASS
