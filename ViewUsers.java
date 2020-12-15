import net.proteanit.sql.DbUtils;
import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ViewUsers extends JFrame {

    private JFrame userFrame;


    public void displayUsers() {
        userFrame = new JFrame("Users List");
        Connection connection = Connect.connect();
        String sql = "select * from UsersList";
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("USE OurLibrary");
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            JTable book_list= new JTable();
            book_list.setModel(DbUtils.resultSetToTableModel(rs));
            JScrollPane scrollPane = new JScrollPane(book_list);

            userFrame.add(scrollPane);


            userFrame.setSize(1024, 720);
            userFrame.setVisible(true);
            userFrame.setLocationRelativeTo(null);
        } catch (SQLException e1) {
            JOptionPane.showMessageDialog(null, e1);
        }
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
                new ViewUsers().displayUsers();
            }
        });
    }


}
