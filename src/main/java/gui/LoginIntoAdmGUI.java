package gui;

import dao.AdminDao;
import dao.UserDao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginIntoAdmGUI extends JFrame {
    private JButton loginIntoAdminButton;
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JPanel panel;
    private String password="password";
    private String login="login";

    LoginIntoAdmGUI(){
        setTitle("Вход в Админ-ПАНЕЛЬ");
        setContentPane(panel);
        setVisible(true);
        setBounds(500, 400, 200, 120);

        loginIntoAdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!textField1.getText().isEmpty() && !passwordField1.getText().isEmpty()){


                    if (login.equals(textField1.getText()) && password.equals(passwordField1.getText())){
                        new AuthorizedAdminGUI();
                    }
                }
            }
        });
    }
}
