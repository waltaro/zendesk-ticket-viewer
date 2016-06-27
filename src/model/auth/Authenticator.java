package model.auth;

import util.data.Account;
import util.data.Subdomain;

import java.util.Scanner;

public class Authenticator {

    // Private variables used for authentication
    private Account userAccount = new Account();
    private Subdomain subdomainName = new Subdomain();

    // Scanner used for user input;
    private Scanner input = new Scanner(System.in);

    // Empty constructor
    public Authenticator() {
    }

    private void printWelcomeMessage() {
        System.out.println("Welcome to the Zendesk Ticket Viewer!\n");
    }

    private void setAccountDetails() {

        // Requests the user to enter their username
        System.out.print("Please enter your username: ");
        userAccount.setUsername(input.nextLine());

        // Requests the user to enter their password
        System.out.print("Please enter your password: ");
        userAccount.setPassword(input.nextLine());

        // Sets the user account linked to the Zendesk subdomain
        subdomainName.setAccount(userAccount);

    }

    private void setDomainDetails() {

        // Requests the user to enter their domain name on Zendesk
        System.out.print("Please enter your subdomain name on Zendesk (https://subdomain.zendesk.com/): ");

        // Sets the domain name to be used for authentication
        subdomainName.setDomain(input.nextLine());

    }

    public void getUserDetails() {

        // Welcomes the user
        printWelcomeMessage();

        // Requests the user to enter their domain name on Zendesk
        setDomainDetails();

        // Requests the user to getUserDetails to their Zendesk account
        setAccountDetails();

    }

    public void login() {

        // Do requests and stuff here

        System.out.println(userAccount.getUsername());

    }

}
