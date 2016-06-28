package model.auth;

import util.data.Account;
import util.data.Subdomain;

import model.data.TicketProcessor;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Base64;

public class Authenticator {

    // Private variables used for authentication
    private Account userAccount = new Account();
    private Subdomain subdomain = new Subdomain();
    private boolean connected;

    // Empty constructor
    public Authenticator() {
    }

    public Account getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(Account userAccount) {
        this.userAccount = userAccount;
    }

    public Subdomain getSubdomain() {
        return subdomain;
    }

    public void setSubdomain(Subdomain subdomain) {
        this.subdomain = subdomain;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public String encryptUserDetails(String username, String password) {

        String unencyptedString = username + ":" + password;

        return Base64.getEncoder().encodeToString(unencyptedString.getBytes());
    }

    public String getZendeskURLFromSubdomain(String subdomain) {

        String URI = "https://" + subdomain + ".zendesk.com/api/v2/tickets.json";

        return URI;
    }

    private boolean connectError(String message) {
        System.out.println("\n" + message);
        return false;
    }

    public boolean connect(String encryptedString, String URI, TicketProcessor ticketProcessor) throws IOException {

        int responseCode;
        URL url = new URL(URI);

        // If the user has already connected and retrieved data, there is no need to do it again.
        if(isConnected()) {
            return true;
        }

        // Connect and retrive data
        try {
            // Inform user that they are logging in
            System.out.println("\nLogging in...");

            // Open secure connection
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            // Set request headers
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Basic " + encryptedString);

            // Get the response from the server
            responseCode = connection.getResponseCode();

            if(responseCode == 200) {
                // TODO: refactor ticket processing and storage
                ticketProcessor.setDataStream(connection.getInputStream());

                setConnected(true);
                return true;
            } else {
                return connectError("The API is down or your user details were incorrect");
            }

        } catch (UnknownHostException e) {
            return connectError("Your subdomain name was incorrect, please try again.");
        } catch (MalformedURLException e) {
            return connectError("Your subdomain name was incorrect, please try again.");
        }
    }

    public void login(TicketProcessor ticketProcessor) throws IOException {

        // Encrypt user account details in Base64 -- used for authentication
        String encryptedString = encryptUserDetails(userAccount.getUsername(), userAccount.getPassword());

        // Retrieve the full url used for authentication
        String zendeskURL = getZendeskURLFromSubdomain(subdomain.getDomain());

        // Connect to the Zendesk API and retrieve the tickets
        connect(encryptedString, zendeskURL, ticketProcessor);

    }

}
