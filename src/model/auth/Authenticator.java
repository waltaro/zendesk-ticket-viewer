package model.auth;

import util.data.user.Account;
import util.data.user.Subdomain;

import model.ticket.TicketProcessor;
import view.Messages;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Base64;

public class Authenticator {

    // Private variables used for authentication
    private Account userAccount = new Account();
    private Subdomain subdomain = new Subdomain();
    private boolean connected;

    // Used to update the view
    private Messages message = new Messages();

    // Empty constructor
    public Authenticator() {
    }

    public void setAccountUsername(String username) {
        this.userAccount.setUsername(username);
    }

    public void setAccountPassword(String password) {
        this.userAccount.setPassword(password);
    }

    public void setUserSubdomainName(String subdomainName) {
        this.subdomain.setDomain(subdomainName);
    }

    public Account getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(Account userAccount) {
        this.userAccount = userAccount;
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

        return new String("https://" + subdomain + ".zendesk.com/api/v2/tickets.json");
    }

    public boolean connect(String encryptedString, String URI, TicketProcessor ticketProcessor) throws IOException {

        int responseCode;
        URL url = new URL(URI);

        // If the user has already connected and retrieved ticket, there is no need to do it again.
        if(isConnected()) {
            return true;
        }

        // Connect and retrieve ticket
        try {
            // Inform the user that they are logging in
            message.printLoginMessage();

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
                return message.printConnectError();
            }

        } catch (UnknownHostException e) {
            return message.printIncorrectSubdomainMessage();
        } catch (MalformedURLException e) {
            return message.printIncorrectSubdomainMessage();
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
