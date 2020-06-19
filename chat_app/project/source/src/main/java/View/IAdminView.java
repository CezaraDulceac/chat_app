package View;

import javax.swing.*;
interface ICHATRoomsProvider {
     public String getUser1();
     public String getUser2();
     public JButton getCreateButton();

     public String getRoomId();
     public String getUser1Update();
     public String getUser2Update();
     public void setUser1Update(String s);
     public void setUser2Update(String s);
     public JButton getUpdateFindRoomButton();
     public JButton getUpdateUpdateRoomButton();

     public String getDeleteRoomId();
     public JButton getDeleteFindRoomButton();
     public JButton getDeleteRoomButton();
     public void setDeleteRoomLabel(String s);

     public JTable getRoomsTable();
     public JButton getViewRoomButton();
}
interface ICHATUsersProvider{
     public String getUsername();
     public String getPassword();
     public JButton getAddUser();

     public String getUserId();
     public String getUsernameUpdate();
     public String getPasswordUpdate();
     public void setUsernameUpdate(String s);
     public void setPasswordUpdate(String s);
     public JButton getUpdateFindUserButton();
     public JButton getUpdateUpdateUserButton();

     public String getDeleteUserId();
     public JButton getDeleteFindUserButton();
     public JButton getDeleteUserButton();
     public void setDeleteUserLabel(String s);
     public JTable getUsersTable();
     public JButton getViewUserButton();
}
interface ICHATReportsProvider{
     public int getSelectedFormatButton();
     public JButton getRepChooseButton();
     public JButton getRepGenerateButton();
     public void setPathLabel(String s);
}
public interface IAdminView extends ICHATRoomsProvider, ICHATUsersProvider, ICHATReportsProvider {
     public JFrame getFrame();
}
