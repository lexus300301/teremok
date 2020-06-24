package gui;

import dao.UserDao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthorizedAdminGUI extends JFrame{
    private JPanel panel1;
    private JButton viewOrders;
    private JButton viewCustomers;
    private JButton addCustomer;
    private JButton orderOrders;

    AuthorizedAdminGUI(){

        setTitle("ГЛАВНОЕ ОКНО");
        UserDao dao = new UserDao();
        setContentPane(panel1);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setBounds(500, 400, 556, 220);

        viewCustomers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CustomerTableGUI();
            }
        });
        viewOrders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OrderTableGUI();
            }
        });
        orderOrders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DescTableOrder();
            }
        });
        addCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddCustomerGui();
            }
        });


    }
}
