import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

class Manager extends PersonDetails implements Serializable {
    private static int nextManagerID = 1;
    private String managerID;
    private String password;
    private String jobTitle;

    public Manager(){}

    public Manager(PersonDetails personDetails, String password, String jobTitle) {
        super(personDetails);
        this.managerID = String.format("M%04d", nextManagerID);
        this.password = password;
        this.jobTitle = jobTitle;
    }

    // Add or edit product...
    public boolean modifyProduct(ArrayList<Product> product) {
        Scanner input = new Scanner(System.in);
        boolean valid;
        String enteredProductId;

        do {
            // Prompt user to enter a product ID
            System.out.print("Enter a product ID: ");
            enteredProductId = input.nextLine();

            // Validate the entered product ID
            if (enteredProductId.length() != 5) {
                System.out.println("Not a valid product ID.");
                valid = false;
            } else
                valid = true;
        } while (!valid);

        // Check if the product exists
        boolean productExist = false;
        int productIndex = -1;
        for (int i = 0; i < product.size(); i++) {
            if (enteredProductId.equalsIgnoreCase(product.get(i).getProdId())) {
                productExist = true;
                productIndex = i;
            }
        }

        // If product exist then edit product
        if (productExist) {
            if (editProduct(productIndex, product)) {
                System.out.println("Product edited successfully.");
                return true;
            } else {
                System.out.println("Product not edited.");
                return false;
            }
        }

        // If product does not exist, add product using the given product ID
        else {
            if (addProduct(enteredProductId, product)) {
                System.out.println("Product added successfully.");
                return true;
            } else {
                System.out.println("Product not added.");
                return false;
            }
        }
    }

    // Sub-method for modifyProduct method, return true if edited successfully
    private boolean editProduct(int i, ArrayList<Product> product) {
        Scanner input = new Scanner(System.in);
        // Show all detail of the product
        System.out.println();
        System.out.format(
                "Product ID: %s\nProduct Name: %s\nProduct Type: %s\nStock Quantity: %d\nPrice: RM %,.2fea\n\n",
                product.get(i).getProdId(), product.get(i).getProdName(), product.get(i).getProdType(),
                product.get(i).getStockQuantity(), product.get(i).getPrice());

        // Prompt user to select a part to edit
        System.out.println("1. Product Name");
        System.out.println("2. Product Type");
        System.out.println("3. Stock Quantity");
        System.out.println("4. Product Price");
        System.out.print("Enter a number: ");
        int choice = input.nextInt();
        input.nextLine();

        // Use a buffer to hold the new data
        String stringBuf = "";
        double doubleBuf = 0;

        if (choice > 0 && choice <= 4) {
            switch (choice) {
                case 1:
                    System.out.print("Enter the new product name: ");
                    stringBuf = input.nextLine();
                    break;
                case 2:
                    System.out.print("Enter the new product type: ");
                    stringBuf = input.nextLine();
                    break;
                case 3:
                    System.out.print("Enter the new stock quantity: ");
                    doubleBuf = input.nextDouble();
                    break;
                case 4:
                    System.out.print("Enter the new product price: RM ");
                    doubleBuf = input.nextDouble();
                    break;
                default:
                    break;
            }
        }

        // Get confirmation from user then put the data into the database
        char confirmation;
        do {
            System.out.print("Do you really want to edit the detail? (Y/N): ");
            confirmation = input.next().toUpperCase().charAt(0);
            input.nextLine();
        } while (confirmation != 'Y' && confirmation != 'N');

        input.close();

        if (confirmation == 'Y') {
            switch (choice) {
                case 1:
                    System.out.println();
                    product.get(i).setProdName(stringBuf);
                    break;
                case 2:
                    System.out.println();
                    product.get(i).setProdType(stringBuf);
                    break;
                case 3:
                    System.out.println();
                    product.get(i).setStockQuantity((int) doubleBuf);
                    break;
                case 4:
                    System.out.println();
                    product.get(i).setPrice((int) doubleBuf);
                    break;
                default:
                    System.out.println("Something went wrong.");
            }
            return true;
        }

        // Return failed to edit item if user rejected the confirmation
        else {
            return false;
        }
    }

    // Sub-method for modifyProduct method, return true if added successfully
    private boolean addProduct(String productId, ArrayList<Product> product) {
        Scanner input = new Scanner(System.in);
        String prodName, prodType;
        int stockQuantity;
        double price;

        // Prompt user to enter the detail of the new product
        System.out.println("Product does not exist, adding new product.");
        System.out.println("Product ID: " + productId);
        System.out.print("Product Name: ");
        prodName = input.nextLine();
        System.out.print("Product Type: ");
        prodType = input.nextLine();
        System.out.print("Stock Quantity: ");
        stockQuantity = input.nextInt();
        System.out.print("Single Price: ");
        price = input.nextDouble();

        // Get confirmation from user then put the data into the database
        char confirmation;
        do {
            System.out.print("Do you really want to add the product? (Y/N): ");
            confirmation = input.next().toUpperCase().charAt(0);
            input.nextLine();
        } while (confirmation != 'Y' && confirmation != 'N');

        input.close();

        if (confirmation == 'Y') {
            product.add(new Product(prodName, prodType, price, stockQuantity));
            return true;
        }

        // Return failed to add item if user rejected the confirmation
        else {
            return false;
        }
    }

    // Add or edit employees...
    public boolean modifyStaff(ArrayList<Employee> employees) {
        boolean valid;
        String enteredStaffId;
        Scanner input = new Scanner(System.in);

        do {
            // Prompt user to enter a product ID
            System.out.print("Enter a employees ID: ");
            enteredStaffId = input.nextLine();

            // Validate the entered product ID
            if (enteredStaffId.length() != 5) {
                System.out.println("Not a valid employees ID.");
                valid = false;
            } else
                valid = true;
        } while (!valid);

        // Check if the employees exists
        boolean staffExist = false;
        int staffIndex = -1;
        for (int i = 0; i < employees.size(); i++) {
            if (enteredStaffId.equalsIgnoreCase(employees.get(i).getEmployeeID())) {
                staffExist = true;
                staffIndex = i;
            }
        }

        // If employees exist then edit employees
        if (staffExist) {
            if (editStaff(staffIndex, employees)) {
                System.out.println("Staff edited successfully.");
                return true;
            } else {
                System.out.println("Staff not edited.");
                return false;
            }
        }

        // If employees does not exist, add employees using the next employees ID
        else {
            if (addStaff(employees)) {
                System.out.println("Staff added successfully.");
                return true;
            } else {
                System.out.println("Staff not added.");
                return false;
            }
        }
    }

    private boolean editStaff(int i, ArrayList<Employee> staff) {
        Scanner input = new Scanner(System.in);
        // Show all detail of the product
        System.out.println();
        System.out.format(
                "Staff ID: %s\nPassword: %s\nBranch: %s\nFirst Name: %s\nLast Name: %s\nGender: %s\nPhone No: %s\nEmail: %s\nIc No: %s\n",
                staff.get(i).getEmployeeID(), staff.get(i).getPassword(), staff.get(i).getBranch().getBranchName(),
                staff.get(i).getFirstName(), staff.get(i).getLastName(), staff.get(i).getGender(),
                staff.get(i).getPhoneNo(), staff.get(i).getEmail(), staff.get(i).getIcNo());
        System.out.println();

        // Prompt user to select a part to edit
        System.out.println("1. Password");
        System.out.println("2. First Name");
        System.out.println("3. Last Name");
        System.out.println("4. Gender");
        System.out.println("5. Phone No.");
        System.out.println("6. Email");
        System.out.print("Enter a number: ");
        int choice = input.nextInt();
        input.nextLine();

        // Use a buffer to hold the new data
        String stringBuf = "";
        char charBuf = 'M';

        if (choice > 0 && choice <= 6) {
            switch (choice) {
                case 1:
                    System.out.print("Enter the new Password: ");
                    stringBuf = input.nextLine();
                    break;
                case 2:
                    System.out.print("Enter the new First Name: ");
                    stringBuf = input.nextLine();
                    break;
                case 3:
                    System.out.print("Enter the new Last Name: ");
                    stringBuf = input.nextLine();
                    break;
                case 4:
                    System.out.print("Enter the new Gender (M/F) : ");
                    charBuf = input.next().charAt(0);
                    break;
                case 5:
                    System.out.print("Enter the new Phone No.: ");
                    stringBuf = input.nextLine();
                    break;
                case 6:
                    System.out.print("Enter the new Email: ");
                    stringBuf = input.nextLine();
                    break;
                default:
                    break;
            }
        }

        // Get confirmation from user then put the data into the database
        char confirmation;
        do {
            System.out.print("Do you really want to edit the detail? (Y/N): ");
            confirmation = input.next().toUpperCase().charAt(0);
            input.nextLine();
        } while (confirmation != 'Y' && confirmation != 'N');

        input.close();

        if (confirmation == 'Y') {
            switch (choice) {
                case 1:
                    System.out.println();
                    staff.get(i).setPassword(stringBuf);
                    break;
                case 2:
                    System.out.println();
                    staff.get(i).setFirstName(stringBuf);
                    break;
                case 3:
                    System.out.println();
                    staff.get(i).setLastName(stringBuf);
                    break;
                case 4:
                    System.out.println();
                    staff.get(i).setGender(charBuf);
                    break;
                case 5:
                    System.out.println();
                    staff.get(i).setPhoneNo(stringBuf);
                    break;
                case 6:
                    System.out.println();
                    staff.get(i).setEmail(stringBuf);
                    break;
                default:
                    System.out.println("Something went wrong.");
            }
            return true;
        }

        // Return failed to edit item if user rejected the confirmation
        else {
            return false;
        }
    }

    private boolean addStaff(ArrayList<Employee> staff) {
        Scanner input = new Scanner(System.in);
        String password, firstName, lastName, phoneNo, email, icNo, jobTitle;
        char gender;

        // Prompt user to enter the detail of the new staff
        System.out.println("Staff does not exist, adding new staff.");
        System.out.println("Staff ID: " + String.format("S%04d", Employee.getNextEmployeeID()));
        System.out.print("First Name: ");
        firstName = input.nextLine();
        System.out.print("Last Name: ");
        lastName = input.nextLine();
        System.out.print("Phone No.: ");
        phoneNo = input.nextLine();
        System.out.print("Email: ");
        email = input.nextLine();
        System.out.print("IC No.: ");
        icNo = input.nextLine();
        System.out.print("Gender (M/F): ");
        gender = input.nextLine().charAt(0);
        System.out.print("Password: ");
        password = input.nextLine();
        System.out.print("Job Title: ");
        jobTitle = input.nextLine();

        // Get confirmation from user then put the data into the database
        char confirmation;
        do {
            System.out.print("Do you really want to add the product? (Y/N): ");
            confirmation = input.next().toUpperCase().charAt(0);
            input.nextLine();
        } while (confirmation != 'Y' && confirmation != 'N');

        input.close();

        if (confirmation == 'Y') {
            staff.add(new Employee(new PersonDetails(firstName,lastName,gender,phoneNo,email,icNo), jobTitle, password, new Branch()));
            return true;
        }

        // Return failed to add item if user rejected the confirmation
        else {
            return false;
        }
    }

    //Getter
    public String getManagerID() {
        return managerID;
    }

    public String getPassword() {
        return password;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public static int getNextManagerID() {
        return nextManagerID;
    }

    //Setter
    public void setManagerID(String managerID) {
        this.managerID = managerID;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "managerID='" + managerID + '\'' +
                ", password='" + password + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", phoneNo='" + phoneNo + '\'' +
                ", email='" + email + '\'' +
                ", icNo='" + icNo + '\'' +
                '}';
    }
}
