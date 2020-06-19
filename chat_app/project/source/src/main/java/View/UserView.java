package View;

import Client.ReaderThread;
import Controller.UserController;
import Model.User;
import javax.swing.*;
import java.awt.Color;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class UserView implements IUserView{

    private JFrame frame;
    private JTextField sendTF;
    private JLabel user1;
    private JLabel user2;
    private JButton CB;
    private JButton CCB;
    private JScrollPane RSP;
    private  JScrollPane GSP;
    private JLabel status;
    private JScrollPane conversation;
    private JButton SB;
    private JList roomList;
    private JList groupList;
    private JList convList;
    private JPanel main;


    private int Id = -1;
    private String username = "USERNAME";


    private ObjectInputStream input;
    private ObjectOutputStream output;

    public UserView(User u, ObjectInputStream input, ObjectOutputStream output) throws IOException, ClassNotFoundException {
        this.input = input;
        this.output = output;

        this.Id = u.getId();
        this.username = u.getUsername();
        //this.statuss = u.getStatus();

        UserController uc = new UserController(this, input, output);
        initialize(uc);
    }

    private void initialize(UserController uc) throws IOException, ClassNotFoundException {
        //System.out.println(statuss);
        frame = new JFrame();
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setBounds(100, 100, 656, 874);
        frame.getContentPane().setLayout(null);

        main = new JPanel();
        main.setBackground(Color.WHITE);
        main.setBounds(0, 0, 638, 827);
        frame.getContentPane().add(main);
        main.setLayout(new CardLayout(0, 0));

        JPanel menu = new JPanel();
        menu.setBackground(Color.WHITE);
        main.add(menu, "menu");
        menu.setLayout(null);

        JSeparator separator = new JSeparator();
        separator.setForeground(Color.BLACK);
        separator.setBounds(2, 143, 638, 9);
        menu.add(separator);

        JLabel label = new JLabel("");
        label.setIcon(new ImageIcon("images\\pic.png"));
        label.setBounds(93, 0, 110, 109);
        menu.add(label);

        user1 = new JLabel(username);
        user1.setFont(new Font("Sylfaen", Font.PLAIN, 30));
        user1.setBounds(223, 32, 243, 55);
        menu.add(user1);

        JLabel label_1 = new JLabel("");
        label_1.setIcon(new ImageIcon("images\\miniLogo.png"));
        label_1.setBounds(464, 0, 162, 100);
        menu.add(label_1);

        JSeparator separator_1 = new JSeparator();
        separator_1.setForeground(Color.BLACK);
        separator_1.setBounds(2, 271, 638, 9);
        menu.add(separator_1);

        CB = new JButton("CHAT");
        CB.setBackground(Color.WHITE);
        CB.setFont(new Font("Sylfaen", Font.PLAIN, 16));
        CB.setBounds(246, 221, 123, 37);
        menu.add(CB);

        CCB = new JButton("Create CHAT");
        CCB.setBackground(Color.WHITE);
        CCB.setFont(new Font("Sylfaen", Font.PLAIN, 16));
        CCB.setBounds(160, 157, 123, 37);
        menu.add(CCB);

        JButton Sb = new JButton("Star CHAT");
        Sb.setFont(new Font("Sylfaen", Font.PLAIN, 16));
        Sb.setBackground(Color.WHITE);
        Sb.setBounds(12, 157, 123, 37);
        menu.add(Sb);

        RSP = new JScrollPane();
        RSP.setBounds(0, 273, 638, 415);

        //--------------------------------------------------------------------------------
        uc.setRSPSendR();

        //----------------------------------------------------------------------------------
        menu.add(RSP);


        JSeparator separator_2 = new JSeparator();
        separator_2.setForeground(Color.BLACK);
        separator_2.setBounds(0, 0, 638, 16);
        menu.add(separator_2);

        JButton btnNewButton = new JButton("LogOut");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        btnNewButton.setBackground(Color.WHITE);
        btnNewButton.setBounds(10, 23, 77, 47);
        menu.add(btnNewButton);

        JSeparator separator_3 = new JSeparator();
        separator_3.setOrientation(SwingConstants.VERTICAL);
        separator_3.setForeground(Color.BLACK);
        separator_3.setBounds(303, 114, 9, 95);
        menu.add(separator_3);

        JButton BC = new JButton("Block CHAT");
        BC.setFont(new Font("Sylfaen", Font.PLAIN, 16));
        BC.setBackground(Color.WHITE);
        BC.setBounds(12, 221, 123, 37);
        menu.add(BC);

        JSeparator separator_4 = new JSeparator();
        separator_4.setOrientation(SwingConstants.VERTICAL);
        separator_4.setForeground(Color.BLACK);
        separator_4.setBounds(192, 209, 9, 61);
        menu.add(separator_4);

        JSeparator separator_5 = new JSeparator();
        separator_5.setOrientation(SwingConstants.VERTICAL);
        separator_5.setForeground(Color.BLACK);
        separator_5.setBounds(419, 207, 9, 63);
        menu.add(separator_5);

        JSeparator separator_6 = new JSeparator();
        separator_6.setForeground(Color.BLACK);
        separator_6.setBounds(191, 208, 228, 9);
        menu.add(separator_6);

        JButton CR = new JButton("Create ROOM");
        CR.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        CR.setFont(new Font("Sylfaen", Font.PLAIN, 16));
        CR.setBackground(Color.WHITE);
        CR.setBounds(327, 157, 136, 37);
        menu.add(CR);

        JButton AU = new JButton("Allow User");
        AU.setFont(new Font("Sylfaen", Font.PLAIN, 16));
        AU.setBackground(Color.WHITE);
        AU.setBounds(485, 155, 127, 37);
        menu.add(AU);

        JButton BU = new JButton("Block User");
        BU.setFont(new Font("Sylfaen", Font.PLAIN, 16));
        BU.setBackground(Color.WHITE);
        BU.setBounds(485, 220, 127, 37);
        menu.add(BU);

        JSeparator separator_7 = new JSeparator();
        separator_7.setForeground(Color.BLACK);
        separator_7.setBounds(2, 112, 638, 9);
        menu.add(separator_7);

        JLabel lblPrivateChat = new JLabel("Private CHAT");
        lblPrivateChat.setFont(new Font("Sylfaen", Font.PLAIN, 17));
        lblPrivateChat.setBounds(79, 111, 127, 33);
        menu.add(lblPrivateChat);

        JLabel lblPublicRoom = new JLabel("Public ROOM");
        lblPublicRoom.setFont(new Font("Sylfaen", Font.PLAIN, 17));
        lblPublicRoom.setBounds(416, 113, 127, 33);
        menu.add(lblPublicRoom);

        JLabel label_2 = new JLabel("");
        label_2.setIcon(new ImageIcon("images\\Untitled-6.png"));
        label_2.setBounds(0, 112, 639, 158);
        menu.add(label_2);
        GSP = new JScrollPane();
        GSP.setBounds(0, 689, 638, 138);
        menu.add(GSP);

        uc.setGSPSendR();

        JPanel chat = new JPanel();
        chat.setBackground(Color.WHITE);
        main.add(chat, "chat");
        chat.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(0, 0, 638, 122);
        chat.add(panel);
        panel.setLayout(null);

        JLabel label_3 = new JLabel("");
        label_3.setIcon(new ImageIcon("images\\pic.png"));
        label_3.setBounds(118, 13, 128, 97);
        panel.add(label_3);

        user2 = new JLabel("USERNAME2");
        user2.setFont(new Font("Sylfaen", Font.PLAIN, 30));
        user2.setBounds(258, 26, 243, 55);
        panel.add(user2);

        status = new JLabel("");
        status.setFont(new Font("Sylfaen", Font.PLAIN, 16));
        status.setBounds(258, 81, 73, 29);
        //panel.add(status);
        //status.setText(statuss);
        JButton back = new JButton("");
        back.setIcon(new ImageIcon("images\\sageata.png"));
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) main.getLayout();
                cl.show(main, "menu");
            }
        });
        back.setBounds(12, 13, 54, 49);
        panel.add(back);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(0, 123, 638, 526);
        chat.add(panel_1);
        panel_1.setLayout(null);

        conversation = new JScrollPane();
        conversation.setBounds(0, 0, 638, 526);
        panel_1.add(conversation);


        JPanel panel_2 = new JPanel();
        panel_2.setBackground(Color.WHITE);
        panel_2.setBounds(0, 648, 638, 179);
        chat.add(panel_2);
        panel_2.setLayout(null);

        sendTF = new JTextField();
        sendTF.setBounds(24, 24, 493, 142);
        panel_2.add(sendTF);
        sendTF.setColumns(10);

        SB = new JButton("Send");
        SB.setFont(new Font("Sylfaen", Font.PLAIN, 15));
        SB.setBounds(529, 65, 97, 51);
        panel_2.add(SB);

        frame.setVisible(true);



        CCB.addActionListener(e -> {
            try {
                uc.createChatSendR();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        CB.addActionListener(e -> {
            try {
                uc.chatSendR();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        SB.addActionListener(e -> {
            try {
                uc.sendSendR();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        Sb.addActionListener(e -> {
            try {
                uc.starSendR();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        BC.addActionListener(e -> {
            try {
                uc.blockSendR();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        CR.addActionListener(e -> {
            try {
                uc.createGroupSendR();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        AU.addActionListener(e -> {
            try {
                uc.allowUserSendR();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        BU.addActionListener(e -> {
            try {
                uc.denyUserSendR();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                uc.closeThreadReader();
            }
        });


    }



    @Override
    public int userId(){
        return Id;
    }
    @Override
    public JPanel getMain(){
        return main;
    }
    @Override
    public JFrame getFrame(){
        return frame;
    }
//-------------------------------------------
    //          MENU

    @Override
    public JLabel getUser1() {
        return user1;
    }
    @Override
    public void setUser1(String s) {
        user1.setText(s);
    }
    @Override
    public JButton getCB() {
        return CB;
    }
    @Override
    public JButton getCCB() {
        return CCB;
    }
    @Override
    public JScrollPane getRSP() {
        return RSP;
    }
    @Override
    public JList getRoomList() {
        return roomList;
    }
    @Override
    public void setRoomList(JList list) {
        roomList = list;
    }
    @Override
    public JList getGroupList() {
        return groupList;
    }
    @Override
    public void setGroupList(JList list) {
        groupList = list;
    }

//-------------------------------------------
    //          CHAT ROOMS
    @Override
    public JLabel getUser2() {
        return user2;
    }
    @Override
    public void setUser2(String s) {
       user2.setText(s);
    }
    @Override
    public String getSendTF() {
        return sendTF.getText();
    }
    @Override
    public void setSendTF() {
        sendTF.setText("");
    }
    @Override
    public JLabel getStatus() {
        return status;
    }
    @Override
    public void setStatus(String s) {
        status.setText(s);
    }
    @Override
    public JScrollPane getConversation() {
        return conversation;
    }
    @Override
    public JButton getSB() {
        return SB;
    }
    @Override
    public JList getConvList() {
        return convList;
    }
    @Override
    public void setConvList(JList s) {
        convList = s;
    }

    @Override
    public String getUsername(){
        return username;
    }
    @Override
    public void setRSP(JList list){
        RSP.setViewportView(list);
        RSP.getViewport().getView().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                groupList.clearSelection();
            }
        });
        frame.invalidate();
        frame.validate();
        frame.repaint();
    }
    @Override
    public void setGSP(JList list){
        GSP.setViewportView(list);
        GSP.getViewport().getView().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                roomList.clearSelection();
            }
        });
        frame.invalidate();
        frame.validate();
        frame.repaint();
    }

    @Override
    public void setConversation(JList list){
        conversation.setViewportView(list);
    }

    @Override
    public void switchPanels(){
        CardLayout cl = (CardLayout) main.getLayout();
        cl.show(main, "chat");
    }
}
