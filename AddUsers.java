
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;
import javax.swing.*;



public class AddUsers extends JFrame  {
    private JPanel panel;
    private JLabel labelUsername;
    private JLabel labelPassword;
    private JTextField textUsername;
    private JPasswordField fieldPassword;
    private JButton buttonCreate;
    private JButton buttonBack;
    private JRadioButton radioUser;
    private JRadioButton radioAdmin;
    private GridBagConstraints constraints;

    public AddUsers() {
        this.setTitle("Library Management System");
        this.setSize(1024, 768);
        this.setResizable(false);
        initComponents();
        layout(panel,constraints);
    }

    public void initComponents(){
        panel=new JPanel();

        labelUsername = new JLabel("Username : ");
        labelPassword = new JLabel("Password : ");

        textUsername = new JTextField(20);
        fieldPassword = new JPasswordField(20);

        radioUser=new JRadioButton("User");
        radioAdmin=new JRadioButton("Admin");

        buttonCreate = new JButton("Create");
        buttonBack = new JButton("<<Back");
    }
    private void layout(JPanel newPanel,GridBagConstraints constraints){
        newPanel = new JPanel(new GridBagLayout());
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        newPanel.add(labelUsername, constraints);

        constraints.gridx = 1;
        newPanel.add(textUsername, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        newPanel.add(labelPassword, constraints);

        constraints.gridx = 1;
        newPanel.add(fieldPassword, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        newPanel.add(radioUser, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        newPanel.add(radioAdmin, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.WEST;
        newPanel.add(buttonBack,constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        newPanel.add(buttonCreate, constraints);

        newPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Add User"));

        // add the panel to this frame
        add(newPanel);

        pack();
        setLocationRelativeTo(null);

        buttonCreate.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String username = textUsername.getText();
                String password = String.valueOf(fieldPassword.getPassword());
                Boolean admin = false;

                if (radioAdmin.isSelected()) admin = true;

                Connection conn = Connect.connect();
                try {
                    Statement stmt = conn.createStatement();
                    stmt.executeUpdate("USE OurLibrary");

                    stmt.executeUpdate("INSERT INTO UsersList(USERNAME,PASSWORD,ADMIN) VALUES ('" + username + "','" + password + "'," + admin + ")");
                    JOptionPane.showMessageDialog(null, "User added successfully!");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }});


        buttonBack.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                new Admin().setVisible(true);


            } });

    }




    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AddUsers().setVisible(true);
            }
        });
    }
}