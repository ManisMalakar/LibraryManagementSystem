import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CreateReset extends JFrame {

    private JFrame resetFrame;
    private JPanel panel;

    private JButton buttonCreateReset;
    private GridBagConstraints constraints;


    public CreateReset() {
        this.setTitle("Library Management System");
        this.setSize(1024, 768);
        this.setResizable(false);
        initComponents();
        layout(panel, constraints);
    }

    public void initComponents() {
        panel = new JPanel();


        buttonCreateReset = new JButton("Create/Reset");
    }

    private void layout(JPanel newPanel, GridBagConstraints constraints) {
        newPanel = new JPanel(new GridBagLayout());
        constraints = new GridBagConstraints();

        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        newPanel.add(buttonCreateReset, constraints);

        newPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Create/Reset"));

        // add the panel to this frame
        add(newPanel);

        pack();
        setLocationRelativeTo(null);

        buttonCreateReset.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 try {
                     Connection connection = Connect.connect();
                     ResultSet resultSet = connection.getMetaData().getCatalogs();
                     while (resultSet.next()) {
                         String databaseName = resultSet.getString(1);
                         if (databaseName.equals("OurLibrary")) {
                             Statement stmt = connection.createStatement();
                             String sql = "DROP DATABASE OurLibrary";
                             stmt.executeUpdate(sql);
                         }
                     }
                     Statement stmt = connection.createStatement();
                     String sql = "CREATE DATABASE OurLibrary";
                     stmt.executeUpdate(sql);
                     stmt.executeUpdate("USE OurLibrary");
                     String sql1 = "CREATE TABLE UsersList(UID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, Username VARCHAR(30), Password VARCHAR(30), Admin BOOLEAN)";
                     stmt.executeUpdate(sql1);
                     stmt.executeUpdate("INSERT INTO UsersList(Username, Password, Admin) VALUES('admin','admin',TRUE), ('manish', 'manish123', TRUE), ('rixant','rixant123', TRUE)");
                     stmt.executeUpdate("CREATE TABLE BooksList(BID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, BNAME VARCHAR(50), BGENRE VARCHAR(20), BPRICE INT)");
                     stmt.executeUpdate("CREATE TABLE IssuedList(IID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, UID INT, BID INT, IssuedDate VARCHAR(20), ReturnDate VARCHAR(20), Period INT, FINE INT)");
                     stmt.executeUpdate("INSERT INTO BooksList(BNAME, BGENRE, BPRICE) VALUES ('Loo', 'Drama', 350),  ('Seto Baag', 'History', 900), ('Saya','Romance', 850), ('Jiwan Kada ki Phool', 'Biography', 1050), ('Sirish ko Phool','Fiction', 750)");

                     resultSet.close();
                     JOptionPane.showMessageDialog(null, "User added successfully!");
                 } catch (Exception ex) {
                     ex.printStackTrace();
                 }
             }
        }
        );
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
                new CreateReset().setVisible(true);
            }
        });


    }
}