import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double inputWidth = 0.0;
        double inputHeight = 0.0;
        boolean validInput = false;



        while (!validInput) {
            try {
                System.out.print("Enter width: ");
                inputWidth = scanner.nextDouble();
                if (inputWidth <= 0) {
                    System.out.println("width must be > 0");
                    continue;
                }
                validInput = true;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid number format.");
                scanner.next();
            }
        }

        validInput = false;

        while (!validInput) {
            try {
                System.out.print("Enter height: ");
                inputHeight = scanner.nextDouble();
                if (inputHeight <= 0) {
                    System.out.println("height must be > 0.");
                    continue;
                }
                validInput = true;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid number format.");
                scanner.next();
            }
        }

        scanner.close();

        Rectangle customRectangle = new Rectangle(inputWidth, inputHeight);

        System.out.println("Rectangle: " + customRectangle);
    }
}