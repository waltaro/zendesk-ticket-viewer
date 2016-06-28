package model.auth;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class AuthenticatorTest {

    Authenticator auth = new Authenticator();

    @Test
    public void testEncryptUserDetails() {
        assertEquals("dXNlcm5hbWU6cGFzc3dvcmQ=", auth.encryptUserDetails("username", "password"));
    }

    @Test
    public void testGetURI() {
        assertEquals("https://subdomain.zendesk.com/api/v2/tickets.json", auth.getZendeskURLFromSubdomain("subdomain"));
    }

    @Test
    public void testConnect() throws IOException {
        assertEquals(false, auth.connect("notgoingtwork", auth.getZendeskURLFromSubdomain("subdomain")));
    }

}