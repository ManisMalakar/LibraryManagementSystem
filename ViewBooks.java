import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import net.proteanit.sql.DbUtils;

public class ViewBooks extends JFrame {

    private JFrame booksFrame;
    private JButton buttonBack;

    public void displayBooks(){
        booksFrame = new JFrame("Books Available");
        buttonBack = new JButton("<<Back");

        Connection conn =Connect.connect();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("USE OurLibrary");
            String st = ("SELECT * FROM BooksList");
            ResultSet rs=stmt.executeQuery(st);;
            JTable booksList= new JTable();
            booksList.setModel(DbUtils.resultSetToTableModel(rs));

            JScrollPane scrollPane = new JScrollPane(booksList);
            booksFrame.add(scrollPane);
            booksFrame.setSize(400, 200);
            booksFrame.setVisible(true);
            booksFrame.setLocationRelativeTo(null);


        }
        catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex);
        }

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
                new ViewBooks().displayBooks();
            }
        });
    }

}
