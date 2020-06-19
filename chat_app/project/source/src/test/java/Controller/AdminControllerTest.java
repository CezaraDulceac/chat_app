package Controller;

import ModelProviders.RoomProvider;
import ModelProviders.UserProvider;

import View.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;
class AdminControllerTest {

    @Test
    void createRoom() throws IOException, ClassNotFoundException {

        AdminView av = mock(AdminView.class);
        AdminController ac = mock(AdminController.class);
        RoomProvider roomProvider = mock(RoomProvider.class);

        ac.createRoom();
        when(av.getUser1()).thenReturn("Cezara");
        when(av.getUser2()).thenReturn("Cristi");
        verify(roomProvider).addRoom(1,2,null);

    }

    @Test
    void createUser() {
        AdminView av = mock(AdminView.class);
        AdminController ac = mock(AdminController.class);
        UserProvider up = mock(UserProvider.class);

        when(av.getUsername()).thenReturn("Sabina");
        when(av.getPassword()).thenReturn("sabina");

        verify(up).addUser("Sabina","sabina");
    }

    @Test
    void deleteUser(){
        AdminView av = mock(AdminView.class);
        AdminController ac = mock(AdminController.class);
        UserProvider up = mock(UserProvider.class);

        try {
            ac.deleteUser();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        when(av.getDeleteUserId()).thenReturn("3");

        verify(up).findUserById(3);
        verify(up).deleteUserById(3);

    }
}