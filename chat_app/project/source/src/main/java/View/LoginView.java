package View;

import Controller.LoginController;
import Model.User;
import ModelProviders.IUserProvider;
import ModelProviders.UserProvider;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.io.*;

public class LoginView implements ILoginView{

    private JFrame frame;
    private JTextField USTF;
    private JTextField PASSTF;
    private JLabel wait;
    private JButton login;
    private LoginController lg;
    IUserProvider userProvider = new UserProvider();
    private ObjectInputStream input;
    private ObjectOutputStream output;

    public LoginView(ObjectInputStream input,ObjectOutputStream output) {
        this.input = input;
        this.output = output;
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 563, 425);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(0, 0, 545, 378);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        USTF = new JTextField();
        USTF.setBounds(203, 178, 173, 35);
        panel.add(USTF);
        USTF.setColumns(10);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(new Font("Sylfaen", Font.PLAIN, 20));
        lblUsername.setBounds(85, 182, 106, 26);
        panel.add(lblUsername);

        PASSTF = new JTextField();
        PASSTF.setColumns(10);
        PASSTF.setBounds(203, 233, 173, 35);
        panel.add(PASSTF);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Sylfaen", Font.PLAIN, 20));
        lblPassword.setBounds(94, 237, 97, 26);
        panel.add(lblPassword);

        login = new JButton("Login");
        login.setBackground(Color.WHITE);
        login.setFont(new Font("Sylfaen", Font.PLAIN, 20));
        login.setBounds(203, 295, 149, 40);
        login.setFocusable(false);
        panel.add(login);

        JLabel label = new JLabel("");
        label.setIcon(new ImageIcon("images\\logo.png"));
        label.setBounds(130, 13, 267, 128);
        panel.add(label);

        JLabel lblConnectingPeople = new JLabel("Connecting people");
        lblConnectingPeople.setFont(new Font("Bradley Hand ITC", Font.BOLD, 17));
        lblConnectingPeople.setBounds(203, 139, 158, 26);
        panel.add(lblConnectingPeople);

        wait = new JLabel("");
        wait.setBounds(411, 192, 69, 65);
        panel.add(wait);
        frame.setVisible(true);

        lg = new LoginController(this, input, output);
        login.addActionListener(e -> {
            try {
                lg.login();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });

    }
    @Override
    public LoginController getConteoller(){
        return lg;
    }

    @Override
    public void clear(){
       USTF.setText("");
       PASSTF.setText("");
    }
    @Override
    public void showAdminView() {
        IAdminView adm = new AdminView(input, output);
    }

    @Override
    public void showRegularView(User u) throws IOException, ClassNotFoundException {
        IUserView uv = new UserView(u, input, output);
    }

    //-------------------------------------------------------------------
    @Override
    public String getUsername() {
        return USTF.getText();
    }

    @Override
    public String getPassword() {
        return PASSTF.getText();
    }

    public JButton getLoginB(){
        return login;
    }
    @Override
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }

    @Override
    public JLabel getIcon(){
        return wait;
    }
}
