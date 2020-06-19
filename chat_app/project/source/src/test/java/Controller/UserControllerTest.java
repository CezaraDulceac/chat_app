package Controller;


import View.UserView;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Test
    void starCHAT() {

        UserView uv = mock(UserView.class);
        UserController uc = mock(UserController.class);

        when(uv.getRoomList().getSelectedIndex()).thenReturn(8);
        try {
            verify(uc).starSendR();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void blockCHAT() {

        UserView uv = mock(UserView.class);
        UserController uc = mock(UserController.class);

        when(uv.getRoomList().getSelectedIndex()).thenReturn(8);
        try {
            verify(uc).blockSendR();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    void createROOM() {

        UserView uv = mock(UserView.class);
        UserController uc = mock(UserController.class);

        try {
            verify(uc).createGroupSendR();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void allowUser() {

    UserView uv = mock(UserView.class);
    UserController uc = mock(UserController.class);

        try {
            verify(uc).allowUserSendR();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void denyUser() {

        UserView uv = mock(UserView.class);
        UserController uc = mock(UserController.class);

        try {
            verify(uc).denyUserSendR();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}