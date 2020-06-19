package PresentationLogic;

import BussinessLogic.BankBll;

import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class View {

    public JFrame frame;
    public JTable CTable;
    public JTextField nameTF;
    public JTextField CNPTF;
    public JTextField addTF;
    public JTextField phoneTF;
    public JTextField UIDTF;
    public JTextField UNTF;
    public JTextField UCNPTF;
    public JTextField UATF;
    public JTextField UPTF;
    public JTextField TTFA;
    public JTextField MTFA;
    public JTextField DTFA;
    public JTable ATable;
    public JTextField IDTFUA;
    public JTextField TTFUA;
    public JTextField MTFUA;
    public JTextField DTFUA;
    public JTextField IDTFD;
    public JTextField IDTFTM1;
    public JTextField IDTFTM2;
    public JTextField AMTFTM;
    public JTextField IDTFPUB;
    public JTextField NTFE;
    public JTextField CNPTFE;
    public JTextField STFE;
    public JTextField IDTFUE;
    public JTextField NTFUE;
    public JTextField STFUE;
    public JTextField CNPTFUE;
    public JTextField IDTFDE;
    public JTable ETable;
    public JTextField IDTFR;
    public JButton btnAdministrator;
    public JButton btnUser;
    public JButton AE;
    public JButton UE;
    public JButton FE;
    public JButton FED;
    public JButton DE;
    public JButton EC;
    public JButton EU;
    public JButton EV;
    public JButton ED;
    public JButton GR;
    public JButton AC;
    public JButton UC;
    public JButton FC;
    public JButton btnAdd;
    public JButton btnUpdate;
    public JButton btnView;
    public JButton AA;
    public JButton btnCreateA;
    public JButton btnUpdateA;
    public JButton btnViewA;
    public JButton UA;
    public JButton FA;
    public JButton FAD;
    public JButton DA;
    public JButton btnDeleteA;
    public JButton T;
    public JButton P;
    private String userId = "";
    private JLabel IDLABEL;
    public JLabel DELETE;
    public JLabel DELETEE;
    public JLabel OK;
    public JTextField GRTF1;
    public JTextField GRTF2;
    public JTable GRTable;
    public JLabel DATE;
    public JLabel PRET;
    public int date;



    //public static void main(String[] args) {

    //View window = new View();

    // }
    public View() {
        initialize();
    }

    private void initialize() {
        BankBll bb = new BankBll();

        frame = new JFrame();
        frame.setBounds(100, 100, 797, 568);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.getContentPane().setLayout(null);

        JPanel base = new JPanel();
        base.setBackground(Color.WHITE);
        base.setBounds(0, 0, 791, 533);
        frame.getContentPane().add(base);
        base.setLayout(new CardLayout(0, 0));

        JPanel panelMain = new JPanel();
        panelMain.setBackground(Color.WHITE);
        base.add(panelMain, "1");
        panelMain.setLayout(null);

        JLabel name = new JLabel("Welcome to WorldBank");
        name.setFont(new Font("Yu Gothic UI Semilight", Font.BOLD | Font.ITALIC, 30));
        name.setBounds(200, 42, 344, 72);
        panelMain.add(name);

        JLabel bankImg = new JLabel("");
        bankImg.setIcon(new ImageIcon("images\\Untitled-1.png"));
        bankImg.setBounds(578, 27, 176, 136);
        panelMain.add(bankImg);

        btnAdministrator = new JButton("Administrator");
        btnAdministrator.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnAdministrator.setFocusPainted(false);
        btnAdministrator.setFocusTraversalKeysEnabled(false);
        btnAdministrator.setFocusable(false);
        btnAdministrator.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pass = JOptionPane.showInputDialog("Insert Passord");
                if(pass.equals("admin")) {
                    CardLayout cl = (CardLayout) base.getLayout();
                    cl.show(base, "2");
                } else
                {
                    JOptionPane.showMessageDialog(null, "Wrong Password", "Try again!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnAdministrator.setForeground(Color.BLACK);
        btnAdministrator.setBackground(SystemColor.control);
        btnAdministrator.setBounds(99, 288, 221, 81);
        panelMain.add(btnAdministrator);

        btnUser = new JButton("User");
        btnUser.setFocusPainted(false);
        btnUser.setFocusTraversalKeysEnabled(false);
        btnUser.setFocusable(false);
        btnUser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                date =  getRandomDate();
                DATE.setText(Integer.toString(date));

                userId = JOptionPane.showInputDialog("Insert User ID");
                IDLABEL.setText(userId);
                String pass = JOptionPane.showInputDialog("Insert Passord");
                if(bb.verEmployee(userId) && bb.userPass(userId).equals(pass)){ //verific daca exista userul si daca e buna parola
                    CardLayout cl = (CardLayout) base.getLayout();
                    cl.show(base, "3");
                }else{
                    JOptionPane.showMessageDialog(null, "Invalis User or Wrong Password", "Try again!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnUser.setForeground(Color.BLACK);
        btnUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnUser.setBackground(SystemColor.control);
        btnUser.setBounds(448, 288, 221, 81);
        panelMain.add(btnUser);

        JLabel lblPleaseLoginAs = new JLabel("Please login as:");
        lblPleaseLoginAs.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblPleaseLoginAs.setBounds(308, 232, 196, 41);
        panelMain.add(lblPleaseLoginAs);

        JLabel label = new JLabel("");
        label.setIcon(new ImageIcon("images\\Untitled-21.png"));
        label.setBounds(12, 186, 767, 275);
        panelMain.add(label);

        JLabel lblBanksupportworldbankcom = new JLabel("bankSupport@worldbank.com");
        lblBanksupportworldbankcom.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblBanksupportworldbankcom.setBounds(283, 494, 261, 26);
        panelMain.add(lblBanksupportworldbankcom);

        JLabel label_1 = new JLabel("");
        label_1.setIcon(new ImageIcon("images\\hand.png"));
        label_1.setBounds(12, 42, 176, 121);
        panelMain.add(label_1);


        JPanel panelAdmin = new JPanel();
        base.add(panelAdmin, "2");
        panelAdmin.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(0, 0, 791, 533);
        panelAdmin.add(panel);
        panel.setLayout(null);

//----------------------------------------------


        JButton btnLogout = new JButton("LogOut");
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearAdm();
                CardLayout cl = (CardLayout) base.getLayout();
                cl.show(base, "1");
            }
        });
        btnLogout.setBackground(SystemColor.control);
        btnLogout.setBounds(31, 27, 102, 37);
        panel.add(btnLogout);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.BLACK);
        panel_1.setBounds(0, 83, 791, 450);
        panel.add(panel_1);
        panel_1.setLayout(null);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(0, 32, 791, 405);
        panel_1.add(tabbedPane);

        JLabel lblWelcomeBack = new JLabel("Welcome Back");
        lblWelcomeBack.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        lblWelcomeBack.setBounds(296, 27, 174, 43);
        panel.add(lblWelcomeBack);

        //-----------------------TABS
        JPanel pan11 =  new JPanel();
        tabbedPane.addTab("Employees", null, pan11, null);
        pan11.setLayout(null);

        JPanel panel_13 = new JPanel();
        panel_13.setLayout(null);
        panel_13.setBackground(Color.WHITE);
        panel_13.setBounds(0, 0, 786, 376);
        pan11.add(panel_13);

        JPanel panel_14 = new JPanel();
        panel_14.setBackground(Color.WHITE);
        panel_14.setBounds(209, 0, 577, 376);
        panel_13.add(panel_14);
        panel_14.setLayout(new CardLayout(0, 0));

        JPanel panel_15 = new JPanel();
        panel_15.setLayout(null);
        panel_14.add(panel_15, "31"); //create

        NTFE = new JTextField();
        NTFE.setColumns(10);
        NTFE.setBounds(211, 84, 315, 36);
        panel_15.add(NTFE);

        CNPTFE = new JTextField();
        CNPTFE.setColumns(10);
        CNPTFE.setBounds(211, 146, 315, 36);
        panel_15.add(CNPTFE);

        STFE = new JTextField();
        STFE.setColumns(10);
        STFE.setBounds(211, 203, 315, 36);
        panel_15.add(STFE);

        JLabel lblName_1 = new JLabel("Name");
        lblName_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblName_1.setBounds(63, 94, 96, 26);
        panel_15.add(lblName_1);

        JLabel lblCnp_1 = new JLabel("CNP");
        lblCnp_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblCnp_1.setBounds(63, 146, 96, 26);
        panel_15.add(lblCnp_1);

        JLabel lblSalary = new JLabel("Salary");
        lblSalary.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblSalary.setBounds(63, 203, 78, 26);
        panel_15.add(lblSalary);

        AE = new JButton("Add Employee");
        AE.setBounds(182, 325, 144, 38);
        panel_15.add(AE);

        JPanel panel_16 = new JPanel();
        panel_16.setLayout(null);
        panel_14.add(panel_16, "32"); //update
        panel_14.add(panel_16, "32"); //update

        JPanel panel_17 = new JPanel();
        panel_17.setLayout(null);
        panel_17.setBounds(0, 0, 577, 376);
        panel_16.add(panel_17);

        IDTFUE = new JTextField();
        IDTFUE.setColumns(10);
        IDTFUE.setBounds(211, 13, 315, 36);
        panel_17.add(IDTFUE);

        NTFUE = new JTextField();
        NTFUE.setColumns(10);
        NTFUE.setBounds(211, 116, 315, 36);
        panel_17.add(NTFUE);

        STFUE = new JTextField();
        STFUE.setColumns(10);
        STFUE.setBounds(211, 173, 315, 36);
        panel_17.add(STFUE);

        CNPTFUE = new JTextField();
        CNPTFUE.setColumns(10);
        CNPTFUE.setBounds(211, 232, 315, 36);
        panel_17.add(CNPTFUE);

        JLabel lblEmployeeId = new JLabel("Employee ID");
        lblEmployeeId.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblEmployeeId.setBounds(63, 23, 96, 26);
        panel_17.add(lblEmployeeId);

        JLabel lblCnp_2 = new JLabel("Name");
        lblCnp_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblCnp_2.setBounds(63, 116, 96, 26);
        panel_17.add(lblCnp_2);

        JLabel lblSalary_1 = new JLabel("Salary");
        lblSalary_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblSalary_1.setBounds(63, 173, 78, 26);
        panel_17.add(lblSalary_1);

        JLabel lblCnp_3 = new JLabel("CNP");
        lblCnp_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblCnp_3.setBounds(63, 232, 78, 26);
        panel_17.add(lblCnp_3);

        UE = new JButton("Update Employee");
        UE.setBounds(182, 325, 144, 38);
        panel_17.add(UE);

        FE = new JButton("Find Employee");
        FE.setBounds(182, 62, 144, 38);
        panel_17.add(FE);

        JPanel panel_18 = new JPanel();
        panel_18.setLayout(null);
        panel_18.setBackground(Color.WHITE);
        panel_14.add(panel_18, "33"); //view

        JScrollPane SPEP = new JScrollPane();
        SPEP.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        SPEP.setBounds(0, 0, 577, 376);
        panel_18.add(SPEP);

        ETable = new JTable();
        ETable.setModel(new DefaultTableModel(
                new Object[][] {
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                },
                new String[] {
                        "EmployeeID",  "Name", "CNP", "Salary"
                }
        ));
        SPEP.setViewportView(ETable);
        ETable.setRowHeight(30);

        JPanel panel_19 = new JPanel();
        panel_19.setLayout(null);
        panel_14.add(panel_19, "34"); //delete

        JLabel lblEmployeeId_1 = new JLabel("Employee ID");
        lblEmployeeId_1.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblEmployeeId_1.setBounds(60, 70, 96, 26);
        panel_19.add(lblEmployeeId_1);

        IDTFDE = new JTextField();
        IDTFDE.setColumns(10);
        IDTFDE.setBounds(208, 60, 315, 36);
        panel_19.add(IDTFDE);

        FED = new JButton("Find Employee");
        FED.setBounds(179, 109, 144, 38);
        panel_19.add(FED);

        DE = new JButton("Delete Employee\r\n");
        DE.setBounds(179, 233, 144, 38);
        panel_19.add(DE);

        EC = new JButton("Create");
        EC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) panel_14.getLayout();
                cl.show(panel_14, "31");
            }
        });
        EC.setFont(new Font("Tahoma", Font.PLAIN, 17));
        EC.setBounds(43, 64, 96, 34);
        panel_13.add(EC);

        EU = new JButton("Update");
        EU.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) panel_14.getLayout();
                cl.show(panel_14, "32");
            }
        });
        EU.setFont(new Font("Tahoma", Font.PLAIN, 17));
        EU.setBounds(43, 136, 96, 34);
        panel_13.add(EU);

        EV = new JButton("View");
        EV.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) panel_14.getLayout();
                cl.show(panel_14, "33");
            }
        });
        EV.setFont(new Font("Tahoma", Font.PLAIN, 17));
        EV.setBounds(43, 287, 96, 34);
        panel_13.add(EV);

        ED = new JButton("Delete");
        ED.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) panel_14.getLayout();
                cl.show(panel_14, "34");
            }
        });
        ED.setFont(new Font("Tahoma", Font.PLAIN, 17));
        ED.setBounds(43, 212, 96, 34);
        panel_13.add(ED);

        JPanel pan12 =  new JPanel();
        pan12.setBackground(Color.WHITE);
        tabbedPane.addTab("Raports", null, pan12, null);
        pan12.setLayout(null);

        JLabel lblGenerateRaports = new JLabel("Generate Raports:");
        lblGenerateRaports.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblGenerateRaports.setBounds(0, 13, 171, 31);
        pan12.add(lblGenerateRaports);

        JLabel lblEmployeeId_2 = new JLabel("Employee ID:");
        lblEmployeeId_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblEmployeeId_2.setBounds(27, 66, 121, 24);
        pan12.add(lblEmployeeId_2);

        IDTFR = new JTextField();
        IDTFR.setBounds(12, 103, 134, 31);
        pan12.add(IDTFR);
        IDTFR.setColumns(10);

        GR = new JButton("Generate\r\n");
        GR.setBounds(14, 294, 134, 43);
        pan12.add(GR);

        GRTF1 = new JTextField();
        GRTF1.setBounds(12, 176, 136, 22);
        pan12.add(GRTF1);
        GRTF1.setColumns(10);

        GRTF2 = new JTextField();
        GRTF2.setColumns(10);
        GRTF2.setBounds(12, 231, 136, 22);
        pan12.add(GRTF2);

        JLabel lblFrom_1 = new JLabel("From:");
        lblFrom_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblFrom_1.setBounds(12, 147, 63, 24);
        pan12.add(lblFrom_1);

        JLabel lblTo_1 = new JLabel("To:");
        lblTo_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblTo_1.setBounds(12, 211, 63, 24);
        pan12.add(lblTo_1);

        JPanel panel_20 = new JPanel();
        panel_20.setBounds(176, 0, 610, 375);
        pan12.add(panel_20);
        panel_20.setLayout(null);

        JScrollPane scrollPane_2 = new JScrollPane();
        scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane_2.setBounds(0, 0, 610, 376);
        panel_20.add(scrollPane_2);

        GRTable = new JTable();
        GRTable.setModel(new DefaultTableModel(
                new Object[][] {
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                },
                new String[] {
                        "ID", "Empployee ID", "Description", "Date"
                }
        ));
        GRTable.setRowHeight(30);
        scrollPane_2.setViewportView(GRTable);


        //---------------------------regular users
        JPanel panelRU = new JPanel();
        base.add(panelRU, "3");
        panelRU.setLayout(null);

        JPanel panel_2 = new JPanel();
        panel_2.setBackground(Color.WHITE);
        panel_2.setBounds(0, 0, 791, 533);
        panelRU.add(panel_2);

        JButton btnLogout1 = new JButton("LogOut");
        btnLogout1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userId = "";
                clearRU();
                CardLayout cl = (CardLayout) base.getLayout();
                cl.show(base, "1");
            }
        });
        panel_2.setLayout(null);
        btnLogout1.setBackground(SystemColor.control);
        btnLogout1.setBounds(26, 26, 94, 34);
        panel_2.add(btnLogout1);

        JPanel panel_3 = new JPanel();
        panel_3.setBackground(Color.BLACK);
        panel_3.setBounds(0, 83, 791, 450);
        panel_2.add(panel_3);
        panel_3.setLayout(null);

        JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane_1.setBounds(0, 31, 791, 406);
        panel_3.add(tabbedPane_1);

        JPanel pan21 =  new JPanel();
        pan21.setBackground(Color.WHITE);
        tabbedPane_1.addTab("Clients", null, pan21, null);
        pan21.setLayout(null);

        JPanel panel_4 = new JPanel();
        panel_4.setBounds(209, 0, 577, 376);
        panel_4.setBackground(Color.WHITE);
        pan21.add(panel_4);
        panel_4.setLayout(new CardLayout(0, 0));

        JPanel addPanel = new JPanel();
        panel_4.add(addPanel, "11");
        addPanel.setLayout(null);

        nameTF = new JTextField();
        nameTF.setBounds(212, 73, 315, 36);
        addPanel.add(nameTF);
        nameTF.setColumns(10);

        CNPTF = new JTextField();
        CNPTF.setColumns(10);
        CNPTF.setBounds(212, 135, 315, 36);
        addPanel.add(CNPTF);

        addTF = new JTextField();
        addTF.setColumns(10);
        addTF.setBounds(212, 201, 315, 36);
        addPanel.add(addTF);

        phoneTF = new JTextField();
        phoneTF.setColumns(10);
        phoneTF.setBounds(212, 266, 315, 36);
        addPanel.add(phoneTF);

        JLabel lblName = new JLabel("Name:");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblName.setBounds(64, 76, 78, 26);
        addPanel.add(lblName);

        JLabel lblCnp = new JLabel("CNP");
        lblCnp.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblCnp.setBounds(64, 135, 78, 26);
        addPanel.add(lblCnp);

        JLabel lblAddress = new JLabel("Address:");
        lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblAddress.setBounds(64, 201, 78, 26);
        addPanel.add(lblAddress);

        JLabel lblPhone = new JLabel("Phone:");
        lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblPhone.setBounds(64, 266, 78, 26);
        addPanel.add(lblPhone);

        AC = new JButton("Add client");
        AC.setBounds(212, 325, 96, 38);
        addPanel.add(AC);

        JPanel updatePanel = new JPanel();
        panel_4.add(updatePanel, "12");
        updatePanel.setLayout(null);

        JLabel label_2 = new JLabel("Name:");
        label_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
        label_2.setBounds(56, 112, 78, 26);
        updatePanel.add(label_2);

        UIDTF = new JTextField();
        UIDTF.setColumns(10);
        UIDTF.setBounds(209, 13, 315, 36);
        updatePanel.add(UIDTF);

        JLabel label_3 = new JLabel("ID number:");
        label_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
        label_3.setBounds(56, 16, 96, 26);
        updatePanel.add(label_3);

        UNTF = new JTextField();
        UNTF.setColumns(10);
        UNTF.setBounds(209, 109, 315, 36);
        updatePanel.add(UNTF);

        JLabel label_4 = new JLabel("CNP");
        label_4.setFont(new Font("Tahoma", Font.PLAIN, 17));
        label_4.setBounds(61, 158, 78, 26);
        updatePanel.add(label_4);

        UCNPTF = new JTextField();
        UCNPTF.setColumns(10);
        UCNPTF.setBounds(209, 158, 315, 36);
        updatePanel.add(UCNPTF);

        JLabel label_5 = new JLabel("Address:");
        label_5.setFont(new Font("Tahoma", Font.PLAIN, 17));
        label_5.setBounds(61, 207, 78, 26);
        updatePanel.add(label_5);

        UATF = new JTextField();
        UATF.setColumns(10);
        UATF.setBounds(209, 207, 315, 36);
        updatePanel.add(UATF);

        JLabel label_6 = new JLabel("Phone:");
        label_6.setFont(new Font("Tahoma", Font.PLAIN, 17));
        label_6.setBounds(61, 256, 78, 26);
        updatePanel.add(label_6);

        UPTF = new JTextField();
        UPTF.setColumns(10);
        UPTF.setBounds(209, 256, 315, 36);
        updatePanel.add(UPTF);

        UC = new JButton("Update client");
        UC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        UC.setBounds(209, 315, 120, 38);
        updatePanel.add(UC);

        FC = new JButton("Find client");
        FC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        FC.setBounds(194, 58, 120, 38);
        updatePanel.add(FC);

        JPanel viewPanel = new JPanel();
        viewPanel.setBackground(Color.WHITE);
        panel_4.add(viewPanel, "13");
        viewPanel.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 0, 577, 376);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        viewPanel.add(scrollPane);

        CTable = new JTable();
        CTable.setModel(new DefaultTableModel(
                new Object[][] {
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                },
                new String[] {
                        "Name", "ID number", "CNP", "Address", "Phone"
                }
        ));
        CTable.setBackground(Color.WHITE);
        CTable.setRowHeight(30);
        scrollPane.setViewportView(CTable);

        btnAdd = new JButton("Add");
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) panel_4.getLayout();
                cl.show(panel_4, "11");
            }
        });
        btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 17));
        btnAdd.setBounds(43, 64, 96, 34);
        pan21.add(btnAdd);

        btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) panel_4.getLayout();
                cl.show(panel_4, "12");
            }
        });
        btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 17));
        btnUpdate.setBounds(43, 151, 96, 34);
        pan21.add(btnUpdate);

        btnView = new JButton("View");
        btnView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) panel_4.getLayout();
                cl.show(panel_4, "13");
            }
        });
        btnView.setFont(new Font("Tahoma", Font.PLAIN, 17));
        btnView.setBounds(43, 235, 96, 34);
        pan21.add(btnView);



        JPanel pan22 =  new JPanel();
        pan22.setBackground(Color.WHITE);
        tabbedPane_1.addTab("Accounts", null, pan22, null);
        pan22.setLayout(null);

        JPanel panel_5 = new JPanel();
        panel_5.setBackground(Color.WHITE);
        panel_5.setBounds(209, 0, 577, 376);
        pan22.add(panel_5);
        panel_5.setLayout(new CardLayout(0, 0));

        JPanel panel_6 = new JPanel();
        panel_6.setLayout(null);

        TTFA = new JTextField();
        TTFA.setColumns(10);
        TTFA.setBounds(206, 81, 315, 36);
        panel_6.add(TTFA);

        MTFA = new JTextField();
        MTFA.setColumns(10);
        MTFA.setBounds(206, 138, 315, 36);
        panel_6.add(MTFA);

        DTFA = new JTextField();
        DTFA.setColumns(10);
        DTFA.setBounds(206, 197, 315, 36);
        panel_6.add(DTFA);

        JLabel lblType = new JLabel("Type");
        lblType.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblType.setBounds(58, 81, 96, 26);
        panel_6.add(lblType);

        JLabel lblMoney = new JLabel("Money:");
        lblMoney.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblMoney.setBounds(58, 138, 78, 26);
        panel_6.add(lblMoney);

        JLabel lblDate = new JLabel("Date");
        lblDate.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblDate.setBounds(58, 197, 78, 26);
        panel_6.add(lblDate);

        AA = new JButton("Add Account\r\n");
        AA.setBounds(182, 325, 144, 38);
        panel_6.add(AA);

        JPanel panel_7 = new JPanel();
        panel_7.setLayout(null);

        JPanel panel_8 = new JPanel();
        panel_8.setBackground(Color.WHITE);

        panel_8.setLayout(null);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(0, 0, 577, 376);
        scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel_8.add(scrollPane_1);

        ATable = new JTable();
        ATable.setModel(new DefaultTableModel(
                new Object[][] {
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                },
                new String[] {
                        "ID number", "Type", "Amount of money", "Date"
                }
        ));
        ATable.setRowHeight(30);
        scrollPane_1.setViewportView(ATable);


        btnCreateA = new JButton("Create");
        btnCreateA.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) panel_5.getLayout();
                cl.show(panel_5, "21");
            }
        });
        btnCreateA.setFont(new Font("Tahoma", Font.PLAIN, 17));
        btnCreateA.setBounds(43, 64, 96, 34);
        pan22.add(btnCreateA);

        btnUpdateA = new JButton("Update");
        btnUpdateA.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) panel_5.getLayout();
                cl.show(panel_5, "22");
            }
        });
        btnUpdateA.setFont(new Font("Tahoma", Font.PLAIN, 17));
        btnUpdateA.setBounds(43, 136, 96, 34);
        pan22.add(btnUpdateA);

        btnViewA = new JButton("View");
        btnViewA.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) panel_5.getLayout();
                cl.show(panel_5, "23");
            }
        });
        btnViewA.setFont(new Font("Tahoma", Font.PLAIN, 17));
        btnViewA.setBounds(43, 287, 96, 34);
        pan22.add(btnViewA);


        panel_5.add(panel_6, "21");

        JLabel lblTypePersonal = new JLabel("type : personal /professional");
        lblTypePersonal.setBounds(206, 44, 201, 26);
        panel_6.add(lblTypePersonal);
        panel_5.add(panel_7, "22");

        JPanel panel_9 = new JPanel();
        panel_9.setLayout(null);
        panel_9.setBounds(0, 0, 577, 376);
        panel_7.add(panel_9);

        IDTFUA = new JTextField();
        IDTFUA.setColumns(10);
        IDTFUA.setBounds(211, 13, 315, 36);
        panel_9.add(IDTFUA);

        TTFUA = new JTextField();
        TTFUA.setColumns(10);
        TTFUA.setBounds(211, 116, 315, 36);
        panel_9.add(TTFUA);

        MTFUA = new JTextField();
        MTFUA.setColumns(10);
        MTFUA.setBounds(211, 173, 315, 36);
        panel_9.add(MTFUA);

        DTFUA = new JTextField();
        DTFUA.setColumns(10);
        DTFUA.setBounds(211, 232, 315, 36);
        panel_9.add(DTFUA);

        JLabel label_7 = new JLabel("ID number:");
        label_7.setFont(new Font("Tahoma", Font.PLAIN, 17));
        label_7.setBounds(63, 23, 96, 26);
        panel_9.add(label_7);

        JLabel label_8 = new JLabel("Type");
        label_8.setFont(new Font("Tahoma", Font.PLAIN, 17));
        label_8.setBounds(63, 116, 96, 26);
        panel_9.add(label_8);

        JLabel label_9 = new JLabel("Money:");
        label_9.setFont(new Font("Tahoma", Font.PLAIN, 17));
        label_9.setBounds(63, 173, 78, 26);
        panel_9.add(label_9);

        JLabel label_10 = new JLabel("Date");
        label_10.setFont(new Font("Tahoma", Font.PLAIN, 17));
        label_10.setBounds(63, 232, 78, 26);
        panel_9.add(label_10);

        UA = new JButton("Update Account");
        UA.setBounds(182, 325, 144, 38);
        panel_9.add(UA);

        FA = new JButton("Find Account");
        FA.setBounds(182, 62, 144, 38);
        panel_9.add(FA);
        panel_5.add(panel_8, "23");

        JPanel panel_10 = new JPanel();
        panel_5.add(panel_10, "24");
        panel_10.setLayout(null);

        JLabel label_11 = new JLabel("ID number:");
        label_11.setFont(new Font("Tahoma", Font.PLAIN, 17));
        label_11.setBounds(60, 70, 96, 26);
        panel_10.add(label_11);

        IDTFD = new JTextField();
        IDTFD.setColumns(10);
        IDTFD.setBounds(208, 60, 315, 36);
        panel_10.add(IDTFD);

        FAD = new JButton("Find Account");
        FAD.setBounds(179, 109, 144, 38);
        panel_10.add(FAD);

        DA = new JButton("Delete Account\r\n");
        DA.setBounds(179, 233, 144, 38);
        panel_10.add(DA);

        DELETE = new JLabel("");
        DELETE.setFont(new Font("Tahoma", Font.PLAIN, 16));
        DELETE.setBounds(208, 181, 154, 26);
        panel_10.add(DELETE);

        DELETEE = new JLabel("");
        DELETEE.setFont(new Font("Tahoma", Font.PLAIN, 16));
        DELETEE.setBounds(208, 181, 96, 26);
        panel_19.add(DELETEE);

        btnDeleteA = new JButton("Delete");
        btnDeleteA.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) panel_5.getLayout();
                cl.show(panel_5, "24");
            }
        });
        btnDeleteA.setFont(new Font("Tahoma", Font.PLAIN, 17));
        btnDeleteA.setBounds(43, 212, 96, 34);
        pan22.add(btnDeleteA);


        JPanel pan23 =  new JPanel();
        tabbedPane_1.addTab("Transfer Money", null, pan23, null);
        pan23.setLayout(null);

        JPanel panel_11 = new JPanel();
        panel_11.setBackground(Color.WHITE);
        panel_11.setBounds(0, 0, 786, 376);
        pan23.add(panel_11);
        panel_11.setLayout(null);

        JLabel lblTansfer = new JLabel("Transfer");
        lblTansfer.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblTansfer.setBounds(253, 13, 129, 38);
        panel_11.add(lblTansfer);

        JLabel lblFrom = new JLabel("FROM:");
        lblFrom.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblFrom.setBounds(171, 51, 86, 38);
        panel_11.add(lblFrom);

        JLabel lblTo = new JLabel("TO:");
        lblTo.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblTo.setBounds(171, 174, 86, 38);
        panel_11.add(lblTo);

        IDTFTM1 = new JTextField();
        IDTFTM1.setBounds(253, 97, 213, 35);
        panel_11.add(IDTFTM1);
        IDTFTM1.setColumns(10);

        IDTFTM2 = new JTextField();
        IDTFTM2.setColumns(10);
        IDTFTM2.setBounds(253, 210, 213, 35);
        panel_11.add(IDTFTM2);

        JLabel lblAmmount = new JLabel("Ammount:\r\n");
        lblAmmount.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblAmmount.setBounds(127, 290, 106, 38);
        panel_11.add(lblAmmount);

        AMTFTM = new JTextField();
        AMTFTM.setColumns(10);
        AMTFTM.setBounds(253, 290, 213, 35);
        panel_11.add(AMTFTM);

        JLabel lblIdNumber_2 = new JLabel("ID number 1:");
        lblIdNumber_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblIdNumber_2.setBounds(127, 97, 106, 35);
        panel_11.add(lblIdNumber_2);

        JLabel lblIdNumber_3 = new JLabel("ID number 2:");
        lblIdNumber_3.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblIdNumber_3.setBounds(127, 210, 106, 35);
        panel_11.add(lblIdNumber_3);

        T = new JButton("Transfer");
        T.setBounds(588, 163, 115, 71);
        panel_11.add(T);

        OK = new JLabel("");
        //OK.setIcon(new ImageIcon("images\\a.png"));
        OK.setBounds(606, 273, 88, 73);
        panel_11.add(OK);

        JPanel pan24 =  new JPanel();
        tabbedPane_1.addTab("Process utilities bills", null, pan24, null);
        pan24.setLayout(null);

        JPanel panel_12 = new JPanel();
        panel_12.setBackground(Color.WHITE);
        panel_12.setBounds(0, 0, 786, 376);
        pan24.add(panel_12);
        panel_12.setLayout(null);

        JLabel lblProcessUtilityBills = new JLabel("Process Utility Bills");
        lblProcessUtilityBills.setFont(new Font("Tahoma", Font.PLAIN, 19));
        lblProcessUtilityBills.setBounds(280, 57, 164, 35);
        panel_12.add(lblProcessUtilityBills);

        IDTFPUB = new JTextField();
        IDTFPUB.setBounds(352, 151, 230, 35);
        panel_12.add(IDTFPUB);
        IDTFPUB.setColumns(10);

        JLabel lblIdNumber_4 = new JLabel("ID Number:");
        lblIdNumber_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblIdNumber_4.setBounds(211, 147, 109, 38);
        panel_12.add(lblIdNumber_4);

        P = new JButton("Process");
        P.setBounds(310, 237, 103, 45);
        panel_12.add(P);

        JLabel lblTotal = new JLabel("Total:");
        lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblTotal.setBounds(469, 242, 51, 31);
        panel_12.add(lblTotal);

        PRET = new JLabel("0");
        PRET.setFont(new Font("Tahoma", Font.PLAIN, 18));
        PRET.setBounds(532, 242, 51, 31);
        panel_12.add(PRET);

        JLabel label_12 = new JLabel("Welcome Back");
        label_12.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        label_12.setBounds(275, 17, 174, 43);
        panel_2.add(label_12);

        JLabel lblEmployeeId_3 = new JLabel("Employee ID:");
        lblEmployeeId_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblEmployeeId_3.setBounds(517, 0, 117, 39);
        panel_2.add(lblEmployeeId_3);

        IDLABEL = new JLabel("1");
        IDLABEL.setFont(new Font("Tahoma", Font.PLAIN, 18));
        IDLABEL.setBounds(646, 5, 56, 28);
        panel_2.add(IDLABEL);

        JLabel lblDate_1 = new JLabel("Date:");
        lblDate_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblDate_1.setBounds(517, 42, 47, 28);
        panel_2.add(lblDate_1);

        DATE = new JLabel("1");
        DATE.setFont(new Font("Tahoma", Font.PLAIN, 18));
        DATE.setBounds(576, 42, 72, 28);
        panel_2.add(DATE);

        frame.setVisible(true);
    }

    public int getUserId(){
        return Integer.parseInt(userId);
    }

    public int getRandomDate(){
        int month  = (int) ((Math.random()*((12-1)+1))+1);
        int day = (int) ((Math.random()*((30-1)+1))+1); //presupunem ca toate lunile au 30 de zile

        String monthS = Integer.toString(month);
        String dayS;
        if(day >= 1 && day <= 9){
            dayS = "0" + day;
        }else {
            dayS = ""+ day;
        }

        return Integer.parseInt(monthS+ dayS);
    }

    public int getDate(){
        return date;
    }

    public void clearRU(){
        nameTF.setText("");
        CNPTF.setText("");
        addTF.setText("");
        phoneTF.setText("");
        UIDTF.setText("");
        UNTF.setText("");
        UCNPTF.setText("");
        UATF.setText("");
        ATable.setModel(new DefaultTableModel(
                new Object[][] {
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                },
                new String[] {
                        "ID number", "Type", "Amount of money", "Date"
                }
        ));
        CTable.setModel(new DefaultTableModel(
                new Object[][] {
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                },
                new String[] {
                        "Name", "ID number", "CNP", "Address", "Phone"
                }
        ));

        TTFA.setText("");
        MTFA.setText("");
        DTFA.setText("");
        IDTFUA.setText("");
        TTFUA.setText("");
        MTFUA.setText("");
        DTFUA.setText("");

        IDTFD.setText("");
        IDTFTM1.setText("");
        IDTFTM2.setText("");
        AMTFTM.setText("");
        IDTFPUB.setText("");
        UPTF.setText("");

    }

    public void clearAdm(){
        NTFE.setText("");
        CNPTFE.setText("");
        STFE.setText("");
        IDTFUE.setText("");
        NTFUE.setText("");
        STFUE.setText("");
        CNPTFUE.setText("");
        ETable.setModel(new DefaultTableModel(
                new Object[][] {
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                },
                new String[] {
                        "EmployeeID", "Name", "CNP", "Salary"
                }
        ));
        GRTable.setModel(new DefaultTableModel(
                new Object[][] {
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                },
                new String[] {
                        "ID", "Employee ID", "Description", "Date"
                }
        ));

        IDTFDE.setText("");
        IDTFR.setText("");
        GRTF1.setText("");
        GRTF2.setText("");
    }

    public void thisActionListener(ActionListener a) {
        btnAdministrator.addActionListener(a);
        btnUser.addActionListener(a);
        AE.addActionListener(a);
        UE.addActionListener(a);
        FE.addActionListener(a);
        FED.addActionListener(a);
        DE.addActionListener(a);
        EC.addActionListener(a);
        EU.addActionListener(a);
        EV.addActionListener(a);
        ED.addActionListener(a);
        GR.addActionListener(a);
        AC.addActionListener(a);
        UC.addActionListener(a);
        FC.addActionListener(a);
        btnAdd.addActionListener(a);
        btnUpdate.addActionListener(a);
        btnView.addActionListener(a);
        AA.addActionListener(a);
        btnCreateA.addActionListener(a);
        btnUpdateA.addActionListener(a);
        btnViewA.addActionListener(a);
        UA.addActionListener(a);
        FA.addActionListener(a);
        FAD.addActionListener(a);
        DA.addActionListener(a);
        btnDeleteA.addActionListener(a);
        T.addActionListener(a);
        P.addActionListener(a);
    }
}
