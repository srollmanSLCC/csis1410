package problem;

import javax.swing.*;
import java.awt.*;

/**
 * About component
 */
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
            dialog.setLocation(Tutor.frame.getLocation());
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

        JLabel lblProjectBusinessContact = new JLabel("Final Project: Elementary Tutoring Program");
        lblProjectBusinessContact.setHorizontalAlignment(SwingConstants.CENTER);
        lblProjectBusinessContact.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblProjectBusinessContact.setBounds(12, 96, 358, 16);
        getContentPane().add(lblProjectBusinessContact);

        JLabel lblProgrammers = new JLabel("Programmers: Steven Rollman, Amar Barucija, Jacob Jenn");
        lblProgrammers.setHorizontalAlignment(SwingConstants.CENTER);
        lblProgrammers.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblProgrammers.setBounds(12, 114, 358, 16);
        getContentPane().add(lblProgrammers);

        JLabel lblDate = new JLabel("Created: Fall 2018");
        lblDate.setHorizontalAlignment(SwingConstants.CENTER);
        lblDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDate.setBounds(12, 142, 358, 16);
        getContentPane().add(lblDate);
    }
}

