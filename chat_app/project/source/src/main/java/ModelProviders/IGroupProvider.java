package ModelProviders;

import Model.Grup;

import java.util.List;

public interface IGroupProvider {
    public void addGroup(int ownerId, int userId, String name);
//    public List<String> view();
    public boolean verifuUserInRoomByIds(int groupId, int userId);
    public List<Grup> getAllGroupsByUser(int id);
    public Grup findGroupByName(String name);
    public Grup findGroupByNameAndUser(String name, int user);
    public boolean verifyGroupByName(String name);
    public void denyUser(int owner,int userId);
}
