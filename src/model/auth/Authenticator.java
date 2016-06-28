package model.auth;

import util.data.Account;
import util.data.Subdomain;

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

    private boolean connectError() {
        System.out.println("\nThere has been an error with the API or your user details");
        return false;
    }

    public boolean connect(String encryptedString, String URI) throws IOException {

        try {
            int responseCode;
            URL url = new URL(URI);

            // Open secure connection
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            // Set request headers
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Authorization", "Basic " + encryptedString);

            // Get the response from the server
            responseCode = connection.getResponseCode();

            if(responseCode == 200) {
                // TODO: retrieve tickets here
                setConnected(true);
                return true;
            } else {
                return connectError();
            }

        } catch (UnknownHostException e) {
            return connectError();
        } catch (MalformedURLException e) {
            return connectError();
        }
    }

    public void login() throws IOException {

        // Encrypt user account details in Base64 -- used for authentication
        String encryptedString = encryptUserDetails(userAccount.getUsername(), userAccount.getPassword());

        // Retrieve the full url used for authentication
        String zendeskURL = getZendeskURLFromSubdomain(subdomain.getDomain());

        // Connect to the Zendesk API and retrieve the tickets
        connect(encryptedString, zendeskURL);

    }

}
