package Server;


import java.io.Serializable;
import java.util.List;

public class Request implements Serializable {
    private List<Object> params;
    private TYPE t;
    public enum TYPE{
        notificare,

        login,
        loginSuccess,
        loginFail,

        createRoom,
        createRoomSuccess,
        createRoomFail,

        findRoomU,
        findRoomUSuccess,
        findRoomUFail,

        updateRoom,
        updateRoomSuccess,
        updateRoomFail,

        findRoomD,
        findRoomDSuccess,
        findRoomDFail,

        deleteRoom,

        viewRooms,
        createUser,
        createUserSuccess,
        createUserFail,

        findUserU,
        findUserUSuccess,
        findUserUFail,

        updateUser,
        updateUserSuccess,
        updateUserFail,

        findUserD,
        findUserDSuccess,
        findUserDFail,

        deleteUser,
        deleteUserSuccess,
        deleteUserFail,

        viewUsers,
        generate,

        chat,
        createChat,
        createChatSuccess,
        createChatFail,

        send,
        setRsp,
        star,
        notificareOnline,
        block,
        setGsp,
        createGroup,
        createGroupSuccess,
        createGroupFail,
        allowUser,
        denyUser

    }
    public Request(TYPE t, List<Object> params){
            this.t = t;
            this.params = params;
    }

    public List<Object> getParams() {
        return params;
    }

    public void setParams(List<Object> params) {
        this.params = params;
    }

    public TYPE getT() {
        return t;
    }

    public void setT(TYPE t) {
        this.t = t;
    }
}
