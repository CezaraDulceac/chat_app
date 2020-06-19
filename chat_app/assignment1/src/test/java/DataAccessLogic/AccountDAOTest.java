package DataAccessLogic;

import Model.Account;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountDAOTest {

    @Test
    void testAccount() {
        AccountDAO ad = new AccountDAO();
        Account a = new Account("professional", 2300,210);
        ad.add(a);

        List<String> ls = ad.view();
        String myAcc = ls.get(ls.size() - 1);
        assertTrue(myAcc.contains("professional 2300 210"));
    }

}