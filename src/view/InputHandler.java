package view;

import model.auth.Authenticator;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {

    // Scanner used for user input;
    private Scanner input = new Scanner(System.in);

    // Used for menu loop
    private boolean quit = false;

    public InputHandler() {

    }

    public boolean isQuit() {
        return quit;
    }

    public void setQuit(boolean quit) {
        this.quit = quit;
    }

    public void printWelcomeMessage() {
        System.out.println("Welcome to the Zendesk Ticket Viewer!");
    }

    public void printMenu() {
        System.out.println("\nPlease select from the following choices below: \n");
        System.out.println("\t1 - View all tickets");
        System.out.println("\t2 - View single ticket");
        System.out.println("\t3 - Quit\n");
        System.out.print("Input: ");
    }

    private void printError() {
        System.out.println("Please try again.");
    }

    private void quit() {
        System.out.println("\nThank you for using the Zendesk Ticket Viewer");
        setQuit(true);
    }

    private void setAccountDetails(Authenticator authenticator) {

        // Requests the user to enter their username
        System.out.print("\nPlease enter your username: ");
        authenticator.getUserAccount().setUsername(input.nextLine());

        // Requests the user to enter their password
        System.out.print("Please enter your password: ");
        authenticator.getUserAccount().setPassword(input.nextLine());

        // Sets the user account linked to the Zendesk subdomain
        authenticator.setUserAccount(authenticator.getUserAccount());

    }

    private void setDomainDetails(Authenticator authenticator) {

        // Requests the user to enter their domain name on Zendesk
        System.out.print("\nPlease enter your subdomain name on Zendesk (https://subdomain.zendesk.com/): ");

        // Clear buffer
        input.nextLine();

        // Sets the domain name to be used for authentication
        authenticator.getSubdomain().setDomain(input.nextLine());

    }

    // TODO: should add error checking here.. but I'm trusting the user.. :P
    public void getUserDetails(Authenticator authenticator) {

        if(!authenticator.isConnected()) {
            // Requests the user to enter their domain name on Zendesk
            setDomainDetails(authenticator);

            // Requests the user to getUserDetails to their Zendesk account
            setAccountDetails(authenticator);
        }

    }

    public void getMenuChoice(Authenticator authenticator) throws IOException {

        try{
            switch (input.nextInt()) {
                case 1:
                    getUserDetails(authenticator);
                    authenticator.login();
                    break;

                case 2:
                    getUserDetails(authenticator);
                    authenticator.login();
                    break;

                case 3:
                    quit();
                    break;

                default:
                    printError();
                    break;
            }
        } catch (InputMismatchException error) {
            // Clear input
            input.nextLine();
            System.out.println("Your input was not recognised.");
        }

    }
}
