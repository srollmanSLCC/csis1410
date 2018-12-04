package M5A5;

import javax.swing.*;
import java.awt.*;

public class About extends JDialog
{
    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {
        try
        {
            About dialog = new About();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setLocation(App.frame.getLocation());
            dialog.setVisible(true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Create the About dialog.
     */
    public About()
    {
        getContentPane().setBackground(Color.WHITE);
        setBounds(100, 100, 400, 231);
        getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(12, 0, 358, 68);
        getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("CSIS 1410");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBounds(12, 68, 358, 22);
        getContentPane().add(lblNewLabel_1);

        JLabel lblProjectBusinessContact = new JLabel("Project: M5-A5 - Business Contacts");
        lblProjectBusinessContact.setHorizontalAlignment(SwingConstants.CENTER);
        lblProjectBusinessContact.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblProjectBusinessContact.setBounds(12, 96, 358, 16);
        getContentPane().add(lblProjectBusinessContact);

        JLabel lblProgrammer = new JLabel("Programmer: Steven Rollman");
        lblProgrammer.setHorizontalAlignment(SwingConstants.CENTER);
        lblProgrammer.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblProgrammer.setBounds(12, 114, 358, 16);
        getContentPane().add(lblProgrammer);
    }
}

