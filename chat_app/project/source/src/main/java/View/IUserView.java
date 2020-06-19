package View;

import javax.swing.*;

interface IMenuProvider {

    public JLabel getUser1();
    public JButton getCB();
    public JButton getCCB();
    public JScrollPane getRSP();
    public JList getRoomList();
}
interface IChatProvider{

    public JLabel getUser2();
    public void setUser2(String s);
    public JLabel getStatus();
    public void setStatus(String s);
    public JScrollPane getConversation();
    public JButton getSB();
    public JList getConvList();
    public void setRSP(JList list);
    public void switchPanels();
    public void setUser1(String s);
    public void setRoomList(JList list);
    public void setConvList(JList s);
    public void setConversation(JList list);
    public String getSendTF();
    public void setSendTF();
    public int userId();
    public void setGSP(JList list);
    public JList getGroupList();
    public void setGroupList(JList list);
}
public interface IUserView extends IMenuProvider, IChatProvider{
    JPanel getMain();
    public JFrame getFrame();
    public String getUsername();
}
