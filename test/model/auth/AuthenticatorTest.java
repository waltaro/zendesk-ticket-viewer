package model.auth;

import model.ticket.TicketProcessor;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class AuthenticatorTest
{

    Authenticator auth = new Authenticator();
    TicketProcessor ticketProcessor = new TicketProcessor();

    @Test
    public void testEncryptUserDetails()
    {
        assertEquals("dXNlcm5hbWU6cGFzc3dvcmQ=", auth.encryptUserDetails("username", "password"));
    }

    @Test
    public void testGetURI()
    {
        assertEquals("https://subdomain.zendesk.com/api/v2/tickets.json", auth.getZendeskURLFromSubdomain("subdomain"));
    }

    @Test
    public void testConnect() throws IOException
    {
        assertEquals(false, auth.connect("notgoingtwork", auth.getZendeskURLFromSubdomain("subdomain"), ticketProcessor));
    }

}