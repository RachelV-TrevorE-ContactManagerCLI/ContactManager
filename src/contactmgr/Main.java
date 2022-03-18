package contactmgr;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class Main {

    private static ArrayList<Contact> contactList;
    private static final Input testing = new Input();
    private static final Input menuSelector = new Input();
    private static int queryChoice;
    private static final Path pathToContacts = Paths.get("contacts.txt");
    private static String nCFN;
    private static String nCPN;
    private static String nCEMl;

    public static void main(String[] args) {
        initializeContactListVariable();
        writeContactsToFile();
    }///END OF MAIN

    private static void writeContactsToFile() {

        ArrayList <String> contactStrings = new ArrayList<>();

        for (Contact contact: contactList) {
            String contactString = contact.getFirstName() + " | " + contact.getLastName() + " | " + contact.getPhoneNumber() + " | " +
                    contact.getEmail();
            contactStrings.add(contactString);
        }
        try {

            Files.write(pathToContacts,contactStrings);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void initializeContactListVariable(){

        contactList = new ArrayList<>();

        try{

            List<String> stringOfContacts = Files.readAllLines(pathToContacts);

            for (String contact : stringOfContacts) {

                String [] dataParts = contact.split("\\|");
                String firstName = dataParts[0].trim();
                String lastName = dataParts[1].trim();
                String phoneNumber = dataParts[2].trim();
                String emailAddy = dataParts[3].trim();

                Contact contactFromFile  = new Contact(firstName,lastName,phoneNumber,emailAddy);
                contactList.add(contactFromFile);
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        populateBuffer();
    }


    public static void populateBuffer(){
        System.out.print("1. View contacts.\n" +
                "2. Add a new contact.\n" +
                "3. Search a contact by name.\n" +
                "4. Delete an existing contact.\n" +
                "5. Exit.\n" +
                "Enter an option (1, 2, 3, 4 or 5):  ");

        queryChoice = menuSelector.getInt();
        queryContactsByMenuSelection(queryChoice);

    }


    public static void queryContactsByMenuSelection(int queryChoice){
        switch (queryChoice){
            case 1:
                System.out.println("Printing......");
                printContactList();
                populateBuffer();
                break;
            case 2:
                System.out.println("You're adding a new contact");
                createNewContact();
                populateBuffer();
                break;
            case 3:
                queryContactsByName();
                populateBuffer();
                break;
            case 4:
                deleteExistingContact();
                populateBuffer();
                break;
            case 5:
                System.out.println("Have a great day");
                break;
            default:
                System.out.println("not sure what to put here");
        }
    }


    public static void printContactList(){
        for (Contact contact:contactList) {
            System.out.printf("|| %-12s |  %-12s |  %12s |  %24s ||\n" ,
                    contact.getFirstName(),contact.getLastName(),contact.getPhoneNumber(),contact.getEmail());
        }
    }


    public static void createNewContact(){
        Contact contactFromBuffer = new Contact();

        System.out.print("Please add First Name: ");
        nCFN= testing.getString();
        verifyFirstName(nCFN);
        contactFromBuffer.setFirstName(nCFN);

        System.out.print("Please add Last Name: ");
        String nCLN = testing.getString();
        contactFromBuffer.setLastName(nCLN);

        System.out.print("Please enter a 7 or 10 digit phone number: ");
        nCPN = testing.getString();
        verifyPhoneNumber(nCPN);
        contactFromBuffer.setPhoneNumber(nCPN);

        System.out.print("Please enter an email: ");
        nCEMl = testing.getString();
        verifyEmail(nCEMl);
        contactFromBuffer.setEmail(nCEMl);


        setNewContactToContactList(contactFromBuffer);

    }


    public static String verifyFirstName (String name){
        if(name.length() == 0){
            System.out.print("Please add First Name: ");
            nCFN = testing.getString();
            verifyFirstName(nCFN);
        }
        return nCFN;
    }

    public static String verifyPhoneNumber (String phone) {
        if(phone.length() != 7 && phone.length() != 10){
            System.out.print("Please enter a 7 or 10 digit phone number: ");
            nCPN = testing.getString();
            verifyPhoneNumber(nCPN);
        }
        if(!(verifyPhoneIsNumeric(nCPN))){
            System.out.print("Please enter a 7 or 10 digit phone number: ");
            nCPN = testing.getString();
            verifyPhoneNumber(nCPN);
        }
        return nCPN;
    }

        public static boolean verifyPhoneIsNumeric (String strNum) {
            if (strNum == null) {
                return false;
            }
            try {
               int d = Integer.parseInt(strNum);
            } catch (NumberFormatException nfe) {
                return false;
            }
            return true;
        }

        public static String verifyEmail(String email){
           if(!isEmailValid(email)){
               System.out.print("Please enter valid email address: ");
               nCEMl = testing.getString();
               verifyEmail(nCEMl);
           }
            return nCEMl;
        }

        public static boolean isEmailValid(String email) {
            final Pattern EMAIL_REGEX = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+" +
                    "(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+" +
                    "[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);
            return EMAIL_REGEX.matcher(email).matches();
        }




    public static void setNewContactToContactList(Contact newContact){

        contactList.add(newContact);
        System.out.println("Contact Added.....");

        printContactList();
    }

    public static void queryContactsByName(){
        System.out.print("Enter a Name to search for: ");
        String searchQuery = testing.getString();
        for (Contact contact: contactList) {
            if(contact.getFullName().matches("(?i).*" + searchQuery + ".*")){
                System.out.println(contact);
            }
        }

    }


    public static void deleteExistingContact(){
        boolean carryOn = testing.yesNo("YOU ARE DELETING A CONTACT! CONTINUE: (Y/N) ");
        if(!carryOn){
            System.out.println("Exiting......");
        }else{
            System.out.println("Enter Contact to delete");
            String contactToDelete = testing.getString();
            carryOn = testing.yesNo("WOULD YOU LIKE TO DELETE CONTACT ABOVE?: (Y/N) ");

            if(!carryOn){
                System.out.println("Exiting......");
            }else{
                System.out.println("DELETING CONTACT.....");
                contactList.removeIf(contact -> contact.getFirstName().equalsIgnoreCase(contactToDelete));
                contactList.removeIf(contact -> contact.getLastName().equalsIgnoreCase(contactToDelete));
                contactList.removeIf(contact -> contact.getPhoneNumber().equalsIgnoreCase(contactToDelete));
            }
        }
    }







}/// END OF CLASS
