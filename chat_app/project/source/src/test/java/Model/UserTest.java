package Model;

import ModelProviders.IUserProvider;
import ModelProviders.UserProvider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testAccount() {
       IUserProvider ad = new UserProvider();
       ad.addUser("Maria","maria");

        User u = ad.findUserByUsername("Maria");
        assertTrue(u.getUsername().equals("Maria"));
        assertTrue(u.getPass().equals("maria"));
    }

}