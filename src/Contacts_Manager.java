import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;


public class Contacts_Manager {
//    initializing the path to contacts file
    public static String filename = "contacts.txt";
//    empty list for delete method to put all the contacts into EXCEPT the chosen contact
    public static List<String> deleteList = new ArrayList<>();
// This method will render all contacts in contacts file path
    public static void renderAll() {
//        header for display chart when rendered
        System.out.println("Name    | Phone Number\n----------------------");
        try {
//            IF the file has not yet been created then it will create it
            Path datafile = Paths.get(filename);
            if (Files.notExists(datafile)) {
                Files.createFile(datafile);
            }
//            List of contacts to display using a for loop
            List<String> files = Files.readAllLines(datafile);
            for (String file : files) {
                System.out.println(file);
            }
        } catch (IOException iox) {
            iox.printStackTrace();
        }
    }
// add contact method
    public static void addContact() {
        try {
            Path dataFile = Paths.get(filename);
            Scanner sc = new Scanner(System.in);
            sc.useDelimiter("\n");
            System.out.println("Add a contact name");
            String name = sc.next();
            System.out.println("Add a contact number");
            String number = sc.next();
            Contacts newContact = new Contacts(name, number);
//            This specific format to set the table properly
            String contact = String.format("%-7s | %s",newContact.getName(),newContact.getPhoneNumber());

            Files.write(dataFile, Arrays.asList(contact), StandardOpenOption.APPEND);
            System.out.println("Your contact has been added!");
        } catch (IOException iox) {
            iox.printStackTrace();
        }

    }
// search method
    public static void search() {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        System.out.println("Search a contact by name");
        String input = sc.next();
        try {
            Path data = Paths.get(filename);
            List<String> fileNames = Files.readAllLines(data);
            for (String name : fileNames) {
                if (name.toLowerCase().contains(input.toLowerCase())) {
                    System.out.println("Name    | Phone Number\n----------------------");

                    System.out.println(name);
                }
            }
        } catch (IOException iox) {
            iox.printStackTrace();
        }


    }

    public static void delete() {
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        System.out.println("Type a contact name to delete");
        renderAll();
        String input = sc.next();
        try {
            Path data = Paths.get(filename);
            List<String> fileNames = Files.readAllLines(data);
            for (String name : fileNames) {
                if (name.toLowerCase().contains(input.toLowerCase())) {
                    System.out.println("Deleting: " + name);
                    continue;
                }
//                adds all other contacts EXCEPT chosen into new list to render
                deleteList.add(name);
            }
            Files.write(data, deleteList);
        } catch (IOException iox) {
            iox.printStackTrace();
        }

    }
// The main menu * where everything gets put together *
    public static void mainMenu() {
        Scanner sc = new Scanner(System.in);
            System.out.println("1. View contacts\n2. Add a new contact\n3. Search a contact by name\n4. Delete an existing contact\n5. Exit\nEnter a number!");
            int in = sc.nextInt();
            if(in == 1){
                renderAll();
                mainMenu();
            } else if (in == 2) {
                addContact();
                mainMenu();
            } else if (in == 3) {
                search();
                mainMenu();
            } else if (in == 4) {
                delete();
                mainMenu();
            } else if (in == 5) {
                System.out.println("Goodbye!");
            }

    }

    public static void main(String[] args) {
        Path dataFile = Paths.get(filename);
        List<Contacts> contacts = new ArrayList<>();
//        Initial contacts to start off with
        contacts.add(new Contacts("Miguel", "9789831339"));
        contacts.add(new Contacts("Isiah", "9788354917"));
        contacts.add(new Contacts("Rin", "5106557707"));
        contacts.add(new Contacts("Senju", "8005882300"));
        List<String> contactStrings = new ArrayList<>();
        for (Contacts contact : contacts) {
//            Format to display table properly
            contactStrings.add(String.format("%-7s | %s",contact.getName(),contact.getPhoneNumber()));
        }
        try {
            Files.write(dataFile, contactStrings);
        } catch (IOException ixo) {
            ixo.printStackTrace();
        }


      mainMenu();


    }
}
