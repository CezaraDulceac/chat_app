package View;

import Controller.LoginController;
import Model.User;

import javax.swing.*;
import java.io.IOException;

interface ILoginDataProvider {

    String getUsername();
    String getPassword();

}

interface IViewProvider {

    void showAdminView();
    public void showRegularView(User u) throws IOException, ClassNotFoundException;
    void showErrorMessage(String message);
    public JLabel getIcon();
    public LoginController getConteoller();
}

public interface ILoginView extends ILoginDataProvider, IViewProvider {
    public void clear();
}
