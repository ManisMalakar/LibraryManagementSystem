import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import net.proteanit.sql.DbUtils;

public class ViewIssuedBooks extends JFrame {

    private JFrame issuedBooksFrame;

    public void displayIssuedBooks(){
        issuedBooksFrame = new JFrame("Issued Books List");

        Connection conn =Connect.connect();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("USE OurLibrary");
            String st = ("SELECT * FROM IssuedList");
            ResultSet rs=stmt.executeQuery(st);;
            JTable booksList= new JTable();
            booksList.setModel(DbUtils.resultSetToTableModel(rs));

            JScrollPane scrollPane = new JScrollPane(booksList);
            issuedBooksFrame.add(scrollPane);
            issuedBooksFrame.setSize(400, 200);
            issuedBooksFrame.setVisible(true);
            issuedBooksFrame.setLocationRelativeTo(null);


        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
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
                new ViewIssuedBooks().displayIssuedBooks();
            }
        });
    }

}
