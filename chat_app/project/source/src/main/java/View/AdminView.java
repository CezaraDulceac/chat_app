package View;

import Controller.AdminController;

import javax.swing.*;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.CardLayout;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class AdminView implements IAdminView{

    private JFrame frame;
    private JTextField CRU1tf;
    private JTextField CRU2tf;
    private JTextField URIDtf;
    private JTextField UU1tf;
    private JTextField UU2tf;
    private JTextField DRIDtf;
    private JTable tableA;
    private JPanel baseR;
    private JPanel baseU;
    private JTextField CUUtf;
    private JTextField CUPtf;
    private JTextField UIDtf;
    private JTextField UUtf;
    private JTextField UPtf;
    private JTable tableU;
    private JTextField DUIDtf;
    private JButton CR;
    private JButton FR;
    private JButton UR;
    private JButton DFR;
    private JButton DR;
    private JButton AU;
    private JButton FU;
    private JButton UU;
    private JButton DU;
    private JButton DFU;
    private JButton RC;
    private JButton GG;
    private JButton AVB;
    private JButton UView;
    private JLabel roomL;
    private JLabel userL;
    private JLabel pathL;
    private JRadioButton rdbtnPDF;
    private JRadioButton rdbtnhtml;

    private ObjectInputStream input;
    private ObjectOutputStream output;

    public AdminView(ObjectInputStream input,ObjectOutputStream output) {
        this.input = input;
        this.output = output;
        initialize();
    }

    private void initialize(){
        frame = new JFrame();
        frame.setBounds(100, 100, 814, 586);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(0, 0, 796, 539);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JButton logout = new JButton("LogOut");
        logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        logout.setBackground(SystemColor.menu);
        logout.setBounds(52, 37, 102, 37);
        panel.add(logout);

        JLabel label = new JLabel("Welcome Back");
        label.setFont(new Font("Sylfaen", Font.PLAIN, 25));
        label.setBounds(346, 33, 174, 43);
        panel.add(label);

        JPanel main = new JPanel();
        main.setLayout(null);
        main.setBackground(Color.BLACK);
        main.setBounds(0, 99, 803, 443);
        panel.add(main);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBackground(Color.WHITE);
        tabbedPane.setBounds(0, 33, 803, 410);
        main.add(tabbedPane);

        JPanel room = new JPanel();
        room.setBackground(Color.WHITE);
        tabbedPane.addTab("CHAT Rooms", null, room, null);
        room.setLayout(null);

        AVB = new JButton("View");
        AVB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) baseR.getLayout();
                cl.show(baseR, "view");
            }
        });
        AVB.setForeground(Color.WHITE);
        AVB.setBackground(Color.BLACK);
        AVB.setFont(new Font("Tahoma", Font.PLAIN, 17));
        AVB.setBounds(34, 284, 96, 34);
        room.add(AVB);

        JButton ADB = new JButton("Delete");
        ADB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) baseR.getLayout();
                cl.show(baseR, "delete");
            }
        });
        ADB.setForeground(Color.WHITE);
        ADB.setBackground(Color.BLACK);
        ADB.setFont(new Font("Tahoma", Font.PLAIN, 17));
        ADB.setBounds(34, 209, 96, 34);
        room.add(ADB);

        JButton AUB = new JButton("Update");
        AUB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) baseR.getLayout();
                cl.show(baseR, "update");
            }
        });
        AUB.setForeground(Color.WHITE);
        AUB.setBackground(Color.BLACK);
        AUB.setFont(new Font("Tahoma", Font.PLAIN, 17));
        AUB.setBounds(34, 133, 96, 34);
        room.add(AUB);

        JButton ACB = new JButton("Create");
        ACB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) baseR.getLayout();
                cl.show(baseR, "create");
            }
        });
        ACB.setForeground(Color.WHITE);
        ACB.setBackground(Color.BLACK);
        ACB.setFont(new Font("Tahoma", Font.PLAIN, 17));
        ACB.setBounds(34, 61, 96, 34);
        room.add(ACB);

        baseR = new JPanel();
        baseR.setBackground(Color.WHITE);
        baseR.setBounds(178, 0, 620, 380);
        room.add(baseR);
        baseR.setLayout(new CardLayout(0, 0));

        JPanel create = new JPanel();
        create.setBackground(Color.WHITE);
        baseR.add(create, "create");
        create.setLayout(null);

        CRU1tf = new JTextField();
        CRU1tf.setColumns(10);
        CRU1tf.setBounds(220, 105, 315, 36);
        create.add(CRU1tf);

        CRU2tf = new JTextField();
        CRU2tf.setColumns(10);
        CRU2tf.setBounds(220, 162, 315, 36);
        create.add(CRU2tf);

        JLabel lblUser = new JLabel("User1 :");
        lblUser.setForeground(Color.BLACK);
        lblUser.setFont(new Font("Sylfaen", Font.PLAIN, 19));
        lblUser.setBounds(100, 111, 78, 26);
        create.add(lblUser);

        CR = new JButton("Create Room");
        CR.setBackground(Color.WHITE);
        CR.setBounds(220, 278, 144, 38);
        create.add(CR);

        JLabel lblUser_1 = new JLabel("User2 :");
        lblUser_1.setForeground(Color.BLACK);
        lblUser_1.setFont(new Font("Sylfaen", Font.PLAIN, 19));
        lblUser_1.setBounds(100, 162, 78, 26);
        create.add(lblUser_1);

        JPanel update = new JPanel();
        update.setBackground(Color.WHITE);
        baseR.add(update, "update");
        update.setLayout(null);

        URIDtf = new JTextField();
        URIDtf.setColumns(10);
        URIDtf.setBounds(225, 13, 315, 36);
        update.add(URIDtf);

        JLabel lblRoomId = new JLabel("Room ID :");
        lblRoomId.setFont(new Font("Sylfaen", Font.PLAIN, 20));
        lblRoomId.setBounds(77, 23, 96, 26);
        update.add(lblRoomId);

        FR = new JButton("Find Room");
        FR.setBounds(196, 74, 144, 38);
        update.add(FR);

        JLabel lblUser_2 = new JLabel("User1 :");
        lblUser_2.setFont(new Font("Sylfaen", Font.PLAIN, 20));
        lblUser_2.setBounds(77, 144, 96, 26);
        update.add(lblUser_2);

        UU1tf = new JTextField();
        UU1tf.setColumns(10);
        UU1tf.setBounds(225, 144, 315, 36);
        update.add(UU1tf);

        UU2tf = new JTextField();
        UU2tf.setColumns(10);
        UU2tf.setBounds(225, 201, 315, 36);
        update.add(UU2tf);

        UR = new JButton("Update Room");
        UR.setBounds(196, 281, 144, 38);
        update.add(UR);

        JLabel lblUser_3 = new JLabel("User2 :");
        lblUser_3.setFont(new Font("Sylfaen", Font.PLAIN, 20));
        lblUser_3.setBounds(77, 206, 96, 26);
        update.add(lblUser_3);

        JPanel delete = new JPanel();
        delete.setBackground(Color.WHITE);
        baseR.add(delete, "delete");
        delete.setLayout(null);

        DRIDtf = new JTextField();
        DRIDtf.setColumns(10);
        DRIDtf.setBounds(219, 75, 315, 36);
        delete.add(DRIDtf);

        JLabel label_2 = new JLabel("Room ID :");
        label_2.setFont(new Font("Sylfaen", Font.PLAIN, 20));
        label_2.setBounds(71, 85, 96, 26);
        delete.add(label_2);

        DFR = new JButton("Find Room");
        DFR.setBounds(190, 136, 144, 38);
        delete.add(DFR);

        DR = new JButton("Delete Room");
        DR.setBounds(194, 281, 144, 38);
        delete.add(DR);

        roomL = new JLabel("");
        roomL.setFont(new Font("Sylfaen", Font.PLAIN, 20));
        roomL.setBounds(209, 218, 113, 36);
        delete.add(roomL);

        JPanel view = new JPanel();
        view.setBackground(Color.WHITE);
        baseR.add(view, "view");
        view.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBackground(Color.WHITE);
        scrollPane.setBounds(0, 0, 620, 380);
        view.add(scrollPane);

        tableA = new JTable();
        tableA.setSelectionBackground(Color.LIGHT_GRAY);
        tableA.setGridColor(Color.BLACK);
        tableA.setBackground(Color.WHITE);
        tableA.setModel(new DefaultTableModel(
                new Object[][] {
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                },
                new String[] {
                        "Id", "User1", "User1","Creation Date"
                }
        ));
        tableA.setRowHeight(30);
        scrollPane.setViewportView(tableA);

        JPanel user = new JPanel();
        user.setBackground(Color.WHITE);
        tabbedPane.addTab("CHAT Users", null, user, null);
        user.setLayout(null);

        JButton UView = new JButton("View");
        UView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) baseU.getLayout();
                cl.show(baseU, "viewU");
            }
        });
        UView.setForeground(Color.WHITE);
        UView.setFont(new Font("Tahoma", Font.PLAIN, 17));
        UView.setBackground(Color.BLACK);
        UView.setBounds(34, 284, 96, 34);
        user.add(UView);

        JButton button_3 = new JButton("Delete");
        button_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) baseU.getLayout();
                cl.show(baseU, "deleteU");
            }
        });
        button_3.setForeground(Color.WHITE);
        button_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
        button_3.setBackground(Color.BLACK);
        button_3.setBounds(34, 209, 96, 34);
        user.add(button_3);

        JButton button_4 = new JButton("Update");
        button_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) baseU.getLayout();
                cl.show(baseU, "updateU");
            }
        });
        button_4.setForeground(Color.WHITE);
        button_4.setFont(new Font("Tahoma", Font.PLAIN, 17));
        button_4.setBackground(Color.BLACK);
        button_4.setBounds(34, 133, 96, 34);
        user.add(button_4);

        JButton button_5 = new JButton("Create");
        button_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) baseU.getLayout();
                cl.show(baseU, "createU");

            }
        });
        button_5.setForeground(Color.WHITE);
        button_5.setFont(new Font("Tahoma", Font.PLAIN, 17));
        button_5.setBackground(Color.BLACK);
        button_5.setBounds(34, 61, 96, 34);
        user.add(button_5);

        baseU = new JPanel();
        baseU.setBounds(178, 0, 620, 380);
        user.add(baseU);
        baseU.setLayout(new CardLayout(0, 0));

        JPanel createU = new JPanel();
        createU.setBackground(Color.WHITE);
        baseU.add(createU, "createU");
        createU.setLayout(null);

        CUUtf = new JTextField();
        CUUtf.setColumns(10);
        CUUtf.setBounds(210, 75, 315, 36);
        createU.add(CUUtf);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setForeground(Color.BLACK);
        lblUsername.setFont(new Font("Sylfaen", Font.PLAIN, 19));
        lblUsername.setBounds(90, 81, 108, 26);
        createU.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setFont(new Font("Sylfaen", Font.PLAIN, 19));
        lblPassword.setBounds(90, 132, 108, 26);
        createU.add(lblPassword);

        CUPtf = new JTextField();
        CUPtf.setColumns(10);
        CUPtf.setBounds(210, 132, 315, 36);
        createU.add(CUPtf);

        AU = new JButton("Add User");
        AU.setBackground(Color.WHITE);
        AU.setBounds(210, 248, 144, 38);
        createU.add(AU);

        JPanel updateU = new JPanel();
        updateU.setBackground(Color.WHITE);
        baseU.add(updateU, "updateU");
        updateU.setLayout(null);

        UIDtf = new JTextField();
        UIDtf.setColumns(10);
        UIDtf.setBounds(230, 33, 315, 36);
        updateU.add(UIDtf);

        JLabel lblUserId = new JLabel("User ID :");
        lblUserId.setFont(new Font("Sylfaen", Font.PLAIN, 20));
        lblUserId.setBounds(82, 43, 96, 26);
        updateU.add(lblUserId);

        FU = new JButton("Find User");
        FU.setBounds(201, 94, 144, 38);
        updateU.add(FU);

        UUtf = new JTextField();
        UUtf.setColumns(10);
        UUtf.setBounds(230, 164, 315, 36);
        updateU.add(UUtf);

        JLabel lblUsername_1 = new JLabel("Username:");
        lblUsername_1.setFont(new Font("Sylfaen", Font.PLAIN, 20));
        lblUsername_1.setBounds(82, 164, 136, 26);
        updateU.add(lblUsername_1);

        JLabel lblPassword_1 = new JLabel("Password:");
        lblPassword_1.setFont(new Font("Sylfaen", Font.PLAIN, 20));
        lblPassword_1.setBounds(82, 226, 114, 26);
        updateU.add(lblPassword_1);

        UPtf = new JTextField();
        UPtf.setColumns(10);
        UPtf.setBounds(230, 221, 315, 36);
        updateU.add(UPtf);

        UU = new JButton("Update User");
        UU.setBounds(201, 301, 144, 38);
        updateU.add(UU);

        JPanel deleteU = new JPanel();
        deleteU.setBackground(Color.WHITE);
        baseU.add(deleteU, "deleteU");
        deleteU.setLayout(null);

        DU = new JButton("Delete User");
        DU.setBounds(208, 266, 144, 38);
        deleteU.add(DU);

        DFU = new JButton("Find User");
        DFU.setBounds(204, 121, 144, 38);
        deleteU.add(DFU);

        DUIDtf = new JTextField();
        DUIDtf.setColumns(10);
        DUIDtf.setBounds(233, 60, 315, 36);
        deleteU.add(DUIDtf);

        JLabel lblUserId_1 = new JLabel("User ID :");
        lblUserId_1.setFont(new Font("Sylfaen", Font.PLAIN, 20));
        lblUserId_1.setBounds(85, 70, 96, 26);
        deleteU.add(lblUserId_1);

        userL = new JLabel("");
        userL.setFont(new Font("Sylfaen", Font.PLAIN, 20));
        userL.setBounds(223, 198, 113, 36);
        deleteU.add(userL);

        JPanel viewU = new JPanel();
        viewU.setBackground(Color.WHITE);
        baseU.add(viewU, "viewU");
        viewU.setLayout(null);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(0, 0, 620, 380);
        viewU.add(scrollPane_1);
        scrollPane_1.setBackground(Color.WHITE);


        tableU = new JTable();
        tableU.setModel(new DefaultTableModel(
                new Object[][] {
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                },
                new String[] {
                        "Id", "Username", "Password","Creation Date"
                }
        ));
        tableU.setRowHeight(30);
        scrollPane_1.setViewportView(tableU);

        JPanel raport = new JPanel();
        raport.setBackground(Color.WHITE);
        tabbedPane.addTab("CHAT Reports", null, raport, null);
        raport.setLayout(null);

        JLabel lblGenerateAppReports = new JLabel("Generate App Reports");
        lblGenerateAppReports.setFont(new Font("Sylfaen", Font.PLAIN, 25));
        lblGenerateAppReports.setBounds(249, 13, 267, 50);
        raport.add(lblGenerateAppReports);

        rdbtnPDF = new JRadioButton(".pdf");
        rdbtnPDF.setBackground(Color.WHITE);
        rdbtnPDF.setBounds(337, 81, 74, 25);
        raport.add(rdbtnPDF);

        rdbtnhtml = new JRadioButton(".html");
        rdbtnhtml.setBackground(Color.WHITE);
        rdbtnhtml.setBounds(337, 111, 74, 25);
        raport.add(rdbtnhtml);

        ButtonGroup G = new ButtonGroup();
        G.add(rdbtnPDF);
        G.add(rdbtnhtml);

        JLabel lblGenerateAs = new JLabel("Choose format:");
        lblGenerateAs.setFont(new Font("Sylfaen", Font.PLAIN, 20));
        lblGenerateAs.setBounds(164, 87, 153, 38);
        raport.add(lblGenerateAs);

        JLabel lblChooseLocation = new JLabel("Choose location:");
        lblChooseLocation.setFont(new Font("Sylfaen", Font.PLAIN, 20));
        lblChooseLocation.setBounds(164, 169, 153, 38);
        raport.add(lblChooseLocation);

        RC = new JButton("Choose");
        RC.setBounds(336, 169, 108, 38);
        raport.add(RC);

        JLabel lblLocationChoosed = new JLabel("Location choosed: ");
        lblLocationChoosed.setFont(new Font("Sylfaen", Font.PLAIN, 20));
        lblLocationChoosed.setBounds(164, 220, 164, 38);
        raport.add(lblLocationChoosed);

        pathL = new JLabel("-");
        pathL.setFont(new Font("Sylfaen", Font.PLAIN, 19));
        pathL.setBounds(336, 220, 449, 38);
        raport.add(pathL);

        GG = new JButton("Generate");
        GG.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        GG.setBounds(312, 303, 120, 50);
        raport.add(GG);

        JLabel label_1 = new JLabel("");
        label_1.setIcon(new ImageIcon("images\\miniLogo.png"));
        label_1.setBounds(583, 13, 153, 86);
        panel.add(label_1);
        frame.setVisible(true);


        AdminController ac = new AdminController(this, input, output);
        CR.addActionListener(e -> {
            try {
                ac.createRoom();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        FR.addActionListener(e-> {
            try {
                ac.findRoomU();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        UR.addActionListener(e -> {
            try {
                ac.updateRoom();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        DFR.addActionListener(e -> {
            try {
                ac.findRoomD();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        DR.addActionListener(e -> {
            try {
                ac.deleteRoom();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        AVB.addActionListener(e -> {
            try {
                ac.viewRooms();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });


        AU.addActionListener(e -> {
            try {
                ac.createUser();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        FU.addActionListener(e -> {
            try {
                ac.findUserU();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        UU.addActionListener(e -> {
            try {
                ac.updateUser();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        DFU.addActionListener(e -> {
            try {
                ac.findUserD();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        DU.addActionListener(e -> {
            try {
                ac.deleteUser();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        UView.addActionListener(e -> {
            try {
                ac.viewUsers();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });

        RC.addActionListener(E -> {
            try {
                ac.choose();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        GG.addActionListener(e -> {
            try {
                ac.generate();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
    }

    @Override
    public JFrame getFrame(){
        return frame;
    }
//----------------------------------------------------
//                      CHAT ROOM
    //------------------------------------------------
    //                  CREATE
    @Override
    public String getUser1() {
        return CRU1tf.getText();
    }
    @Override
    public String getUser2() {
        return CRU2tf.getText();
    }
    @Override
    public JButton getCreateButton() {
        return CR;
    }
    //------------------------------------------------
    //                  UPDATE
    @Override
    public String getRoomId() {
        return URIDtf.getText();
    }
    @Override
    public String getUser1Update() {
        return UU1tf.getText();
    }
    @Override
    public String getUser2Update() {
        return UU2tf.getText();
    }
    @Override
    public void setUser1Update(String s) {
         UU1tf.setText(s);
    }
    @Override
    public void setUser2Update(String s) {
         UU2tf.setText(s);
    }
    @Override
    public JButton getUpdateFindRoomButton() {
        return FR;
    }
    @Override
    public JButton getUpdateUpdateRoomButton() {
        return UR;
    }
    //------------------------------------------------
    //                  DELETE
    @Override
    public String getDeleteRoomId() {
        return DRIDtf.getText();
    }
    @Override
    public JButton getDeleteFindRoomButton() {
        return DFR;
    }
    @Override
    public JButton getDeleteRoomButton() {
        return DR;
    }
    @Override
    public void setDeleteRoomLabel(String s) {roomL.setText(s);}
    //------------------------------------------------
    //                  VIEW
    @Override
    public JButton getViewRoomButton() {
        return AVB;
    }

    @Override
    public JTable getRoomsTable() {
        return tableA;
    }

//----------------------------------------------------
//                      CHAT USER
    //------------------------------------------------
    //                  CREATE
    @Override
    public String getUsername() {
    return CUUtf.getText();
}
    @Override
    public String getPassword() {
        return CUPtf.getText();
    }
    @Override
    public JButton getAddUser() {
        return AU;
    }
    //------------------------------------------------
    //                  UPDATE
    @Override
    public String getUserId() {
        return UIDtf.getText();
    }
    @Override
    public String getUsernameUpdate() {
        return UUtf.getText();
    }
    @Override
    public String getPasswordUpdate() {
        return UPtf.getText();
    }
    @Override
    public void setUsernameUpdate(String s) {
        UUtf.setText(s);
    }
    @Override
    public void setPasswordUpdate(String s) {
        UPtf.setText(s);
    }
    @Override
    public JButton getUpdateFindUserButton() {
        return FU;
    }
    @Override
    public JButton getUpdateUpdateUserButton() {
        return UU;
    }
    //------------------------------------------------
    //                  DELETE
    @Override
    public String getDeleteUserId() {
        return DUIDtf.getText();
    }
    @Override
    public JButton getDeleteFindUserButton() {
        return DFU;
    }
    @Override
    public JButton getDeleteUserButton() {
        return DU;
    }
    @Override
    public void setDeleteUserLabel(String s) {userL.setText(s);}
    //------------------------------------------------
    //                  VIEW
    @Override
    public JButton getViewUserButton() {
        return UView;
    }
    @Override
    public JTable getUsersTable() {
        return tableU;
    }

//----------------------------------------------------
//                      CHAT REPORTS
    @Override
    public int getSelectedFormatButton(){
        if(rdbtnPDF.isSelected()){
            return 1;
        }else if(rdbtnhtml.isSelected()){
            return 2;
        }else{
            JOptionPane.showMessageDialog(frame, "no check button selected");
            return -1;
        }
    }
    @Override
    public JButton getRepChooseButton(){
        return RC;
    }
    @Override
    public JButton getRepGenerateButton(){
        return GG;
    }
    @Override
    public void setPathLabel(String s){
        pathL.setText(s);
    }

}
