
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;


public class Main extends JFrame {
    private JPanel panel;
    private JLabel labelUsername;
    private JLabel labelPassword;
    private JTextField textUsername;
    private JPasswordField fieldPassword;
    private JButton buttonLogin;
    private GridBagConstraints constraints;

    public Main() {
        this.setTitle("Library Management System");
        this.setSize(600, 700);
        this.setResizable(false);
        initComponents();
        layout(panel,constraints);
    }
    public void initComponents(){
        panel=new JPanel();
        labelUsername = new JLabel("Username: ");
        labelPassword = new JLabel("Password: ");
        textUsername = new JTextField(20);
        fieldPassword = new JPasswordField(20);
        buttonLogin = new JButton("Login");
    }
    private void layout(JPanel newPanel,GridBagConstraints constraints){
        newPanel = new JPanel(new GridBagLayout());
        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);


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
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        newPanel.add(buttonLogin, constraints);


        buttonLogin.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e){

                String username = textUsername.getText();
                String password = String.valueOf(fieldPassword.getPassword());

                if(username.equals(""))
                {
                    JOptionPane.showMessageDialog(null,"Please enter username");
                }
                else if(password.equals(""))
                {
                    JOptionPane.showMessageDialog(null,"Please enter password");
                }
                else {
                    Connection conn = Connect.connect();
                    try {
                        Statement stmt = conn.createStatement();
                        stmt.executeUpdate("USE OurLibrary");
                        String st = ("SELECT * FROM UsersList WHERE Username='" + username + "' AND Password='" + password + "'");
                        ResultSet rs = stmt.executeQuery(st);
                        if (rs.next() == false) {
                            System.out.print("No user");
                            JOptionPane.showMessageDialog(null, "Wrong Username/Password!");

                        } else {
                            boolean isAdmin = rs.getBoolean("Admin");
                            if(isAdmin==true) {
                                new Admin().setVisible(true);
                                setVisible(false);
                            }
                            else{
                                new User().setVisible(true);
                                setVisible(false);
                            }


                        };}


                    catch(Exception ex){
                        ex.printStackTrace();
                    }
                }
            }


        });

        newPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Login"));


        add(newPanel);

        pack();
        setLocationRelativeTo(null);
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
                new Main().setVisible(true);
            }
        });
    }
}