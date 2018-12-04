package M5A2;

import javax.swing.*;
import java.awt.*;

public class App
{
    // Declare RegExes.
    private final String addressRegex = "^\\d+\\s[A-z]+\\s[A-z]+";
    private final String nameRegex = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
    private final String phoneRegex = "^\\(?(\\d{3})\\)?[-. ]?(\\d{3})[-. ]?(\\d{4})$";

    // Other variables;
    private static JFrame frame;
    private JTextField txtName, txtAddress, txtPhone;
    private JLabel lblName, lblAddress, lblPhone;
    private JButton btnValidate;

    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            try
            {
                App app = new App();
                app.frame.setVisible(true);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the application. No Args Constructor
     */
    public App()
    {
        initialize();
    }

    private void initialize()
    {
        // initialize our variables
        frame = new JFrame();
        txtName = new JTextField();
        txtAddress = new JTextField();
        txtPhone = new JTextField();
        btnValidate = new JButton("Validate");
        lblName = new JLabel("Name:");
        lblAddress = new JLabel("Address:");
        lblPhone = new JLabel("Phone:");

        // Set up the main frame.
        frame.setBounds(650, 450, 270, 180);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);

        // Set up text entry boxes.
        txtName.setBounds(80, 10, 150, 20);
        txtName.setColumns(10);
        txtAddress.setBounds(80, 40, 150, 20);
        txtAddress.setColumns(10);
        txtPhone.setBounds(80, 70, 150, 20);
        txtPhone.setColumns(10);

        // Set up labels.
        lblName.setBounds(10, 12, 64, 14);
        lblAddress.setBounds(10, 42, 64, 14);
        lblPhone.setBounds(10, 72, 64, 14);

        // Set up our button
        btnValidate.setBounds(11, 100, 89, 23);

        frame.getContentPane().add(txtName);
        frame.getContentPane().add(txtAddress);
        frame.getContentPane().add(txtPhone);
        frame.getContentPane().add(lblName);
        frame.getContentPane().add(lblAddress);
        frame.getContentPane().add(lblPhone);
        frame.getContentPane().add(btnValidate);

        btnValidate.addActionListener(e -> {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("Name matches RegEx: %s\n", txtName.getText().matches(nameRegex)));
            sb.append(String.format("Address matches RegEx: %s\n", txtAddress.getText().matches(addressRegex)));
            sb.append(String.format("Phone matches RegEx: %s", txtPhone.getText().matches(phoneRegex)));

            JOptionPane.showMessageDialog(frame, sb.toString());
        });
    }
}
