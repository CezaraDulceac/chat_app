package ModelProviders;

import Model.Room;

import java.util.List;

public interface IRoomProvider {
    public Room findRoomById(int id);
    public void addRoom(int user1, int user2, String date);
    public List<String> view();
    public void deleteRoomById(int id);
    public void updateRoomById(int id, int user1, int user2);
    public boolean verifyRoom(int id);
    public boolean verifyRoomByUsers(int user1, int user2);
    public List<String> getAllRoomsUser2();
    public int findRoomIdByUsernames(int user1, int user2);
    public List<String> getAllRoomsByUser(int id);
    public List<String> gerRoomdsID();
    public List<String> getAllRoomsByUserStar(int id, int star);
    public void setRoomStar(int idR, int star);
    public void setRoomBlock(int idR, int block);
    public List<String> getAllRoomsByUserBlockAndNoStar(int id, int block, int star);

}
