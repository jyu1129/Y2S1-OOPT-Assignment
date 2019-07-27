import java.util.Scanner;

public class MenuOptions {
    private Scanner scanner = new Scanner(System.in);

    public boolean employeeMenuOptions(){

        int menuOption;

        do {
            System.out.println("Menu Options");
            System.out.println("------------");
            System.out.println("1. Sales Order");
            System.out.println("2. Logout");
            System.out.println("0. Exit");
            System.out.print("> ");

            menuOption = scanner.nextInt();
            switch (menuOption) {
                case 1:
                    break;
                case 2:
                    return true;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("No such option!");
                    break;
            }
        } while (true);
    }

    public boolean managerMenuOptions(){

        int menuOption;

        do {
            System.out.println("Menu Options");
            System.out.println("------------");
            System.out.println("1. Special manager option");
            System.out.println("2. Special manager option");
            System.out.println("3. Logout");
            System.out.println("0. Exit");
            System.out.print("> ");

            menuOption = scanner.nextInt();
            switch (menuOption) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    return true;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("No such option!");
                    break;
            }
        } while (true);
    }
}
