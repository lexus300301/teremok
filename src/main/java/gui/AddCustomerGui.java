package gui;

import dao.UserDao;
import domain.Customer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddCustomerGui extends JFrame{
    private JTextField fullname;
    private JTextField address;
    private JTextField phone;
    private JButton register;
    private JPanel panel;

    AddCustomerGui(){
        setTitle("Регистрация новых посетителей");
        setContentPane(panel);
        setVisible(true);
        setBounds(500, 400, 200, 120);

        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!fullname.getText().isEmpty()  && !address.getText().isEmpty() && !phone.getText().isEmpty()){
                        Customer customer = new Customer();
                        customer.setCustomerFullName(fullname.getText());
                        customer.setCustomerAddress(address.getText());
                        customer.setCustomerPhone(phone.getText());
                    try {
                        new UserDao().saveCustomer(customer);
                    } catch (SQLException throwables) {
                        JOptionPane.showMessageDialog(null, "Ошибка добавления посетителя", "Output", JOptionPane.ERROR_MESSAGE);

                        throwables.printStackTrace();
                    }
                }
                }
            });
        }
    }

