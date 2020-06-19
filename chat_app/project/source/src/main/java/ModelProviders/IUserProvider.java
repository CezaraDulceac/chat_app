package ModelProviders;

import Model.User;

import java.util.List;

public interface IUserProvider {
    public void addUser(String username, String password);
    public List<String> view();
    public void deleteUserById(int id);
    public void updateUserById(int id, String username, String password);
    public boolean verifyUser(int id);
    public User findUserById(int id);
    public boolean verifyUserByUsername(String username);
    public boolean verifyUserAndPass(String username,String pass);
    public User findUserByUsername(String s);
    public List<String> getAllUsernames();
    public User verUserByUsername(String s);
    public String  getUsernameById(int id);
    public void setStatusById(int id, String stat);
    public String getStatusById(int id);

}
