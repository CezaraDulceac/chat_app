package ModelProviders;

import java.util.List;

public interface IMessageProvider {
    public void addMess(int id1, int roomId,int groupId, String mess, String date);
    public List<String> view();
    public void deleteMessageById(int id);
    public List<String> getAllMessages();
    public List<String> getAllMessagesByRoom(int id);
    public List<String> getAllMessagesByGroup(int id);
}
