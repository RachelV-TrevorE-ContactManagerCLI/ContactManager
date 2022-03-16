package contactmgr;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import static java.nio.file.Files.readAllLines;

public class Main {
    private static List<String> contactList;
    private final Input testing = new Input();
    private static final Input menuSelector = new Input();
    private static int queryChoice;
    static Scanner bufferInput = new Scanner(System.in);

    public static void main(String[] args) {


//        System.out.println();
//        testing.yesNo("would you like to continue ");
//        testing.getInt();
//        testing.getString();

initializeContactListVariable();

    }///END OF MAIN

    public static void initializeContactListVariable(){
        Path pathToContacts = Paths.get("contacts.txt");
        try{
            contactList = Files.readAllLines(pathToContacts);
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
        switch (Main.queryChoice){
            case 1:
                System.out.println("print all contacts");
                printContactList();
                break;
            case 2:
                System.out.println("You're adding a new contact");
                createNewContact();
                break;
            case 3:
                System.out.println("search by name");
                queryContactsByName();
                break;
            case 4:
                System.out.println("delete existing contact");
                deleteExistingContact();
                break;
            case 5:
                System.out.println("Have a great day");
                break;
            default:
                System.out.println("not sure what to put here");
        }
    }

    public static void printContactList(){
        System.out.println("Name   |   Phone Number");
        for (String contact:contactList) {
            System.out.println(contact);
        }
    }

    public static void createNewContact(){
Contact latestContact;
        System.out.print("Please add First Name: ");
        String newContactfirstName = bufferInput.nextLine();

        System.out.print("Please add Last Name: ");
        String newContactLastName = bufferInput.nextLine();

        System.out.print("Please enter phone: ");
        String newContactPhone = bufferInput.nextLine();

        System.out.print("Please enter an email: ");
        String newContactEmail = bufferInput.nextLine();


        latestContact = new Contact();
        latestContact.setFirstName(newContactfirstName);
        latestContact.setLastName(newContactLastName);
        latestContact.setPhoneNumber(newContactPhone);
        latestContact.setEmail(newContactEmail);
        String nCFN= latestContact.getFirstName();
        String nCLN = latestContact.getLastName();
        String nCPN = latestContact.getPhoneNumber();
        String nCEM = latestContact.getLastName();

        Contact newContact = new Contact(nCFN,nCLN,nCPN,nCEM);
//        System.out.println(newContact.getPhoneNumber());
//        System.out.println(newContact.);


        setNewContactToContactList(newContact);


         //// call setFirstName, setLastName, set phoneNumber
         //// then append list with new object
    }

    public static void setNewContactToContactList(Contact newContact){
        //// push new contact created above to list

        contactList.add(contactList.size(), String.valueOf(newContact));
        System.out.println("new contact added to list");
        System.out.println(contactList);
    }

    public static void queryContactsByName(){
        System.out.println("query cl by name");
        /// allow query by first or last name
        /// loop through both in search function
        /// contactList(i)[0] and contactList(i)[1]

    }


    public static void deleteExistingContact(){
        System.out.println("delete existing contact");

        //// prompt warning before allowing delete
        //// add yesNo confirmation before delete
        //// manipulate list to remove selected name
        //// add yesNo confirmation for delete
    }

    public static void updateContactsFile (){
        /// Write manipulated AL back to contacts file///
    }





}/// END OF CLASS
