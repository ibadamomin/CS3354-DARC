package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.List;

public class Main implements ActionListener {

    private static JPanel panel;
    private static JFrame frame;
    private static JLabel userLabel;
    private static JTextField userText;
    private static JLabel passwordLabel;
    private static JPasswordField passwordText;
    private static JButton button;
    private static List<String> users = new ArrayList<String>();
    private static Map<String, String> userMap = new HashMap<>();
    private static int userNum = 0;

    public static boolean userAuthorization(String username, String password) {
        //for testing purposes only
        List<String> usersTest = new ArrayList<String>();
        usersTest.add("johndoe");
        usersTest.add("ibadmomin");
        if (usersTest.contains(username)) {
            System.out.println("Sucessfully logged in!");
            return true;
//            if (users.contains(username)) {
//                System.out.println("Sucessfully logged in!");
//                return true;
            } else {
                System.out.println("Invalid username or password.");
                return false;
            }
    }

    public static void writeUsers(int userNum, String name, String phoneNumber, String email, String username, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"))) {
            writer.write(username + "=" + name + "," + phoneNumber + "," + email + "," + username + "," + password);
            writer.newLine(); // Add a newline after each entry

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("User dictionary saved to " + "users.txt");
    }

    static boolean signup(String name, String phoneNumber, String email, String username, String password) {
        //User user = new User(name, phoneNumber, email, username, password);
        userNum++;
        writeUsers(userNum, name, phoneNumber, email, username, password);
        return true;
    }

    public static void readUsers() {
        // Read user data from the text file
        try (BufferedReader reader = new BufferedReader(new FileReader("/Users/ibadmomin/Desktop/CS3354/DRAC_FinalDeliverable/src/users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split each line into key and value based on the "=" separator
                String[] parts = line.split("=");
                if (parts.length == 2) {
                    // Add the key-value pair to the user dictionary
                    userMap.put(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Display the user dictionary
        System.out.println("User dictionary read from " + "users.txt");
        for (Map.Entry<String, String> entry : userMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static boolean addCard(String cardName, double cardPrice, String cardColor, String cardImage) {
        Cards card = new Cards(cardName, cardPrice, cardColor, cardImage);
        frame.setVisible(false);
        panel.setVisible(false);
        panel = new JPanel();
        frame = new JFrame("Cards");
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);

        String cardNameString = card.getCardName();
        double cardPriceDouble = card.getCardPrice();
        String cardColorString = card.getCardColor();
        String cardImageString = card.getCardImage();

        showCards(cardNameString, cardPriceDouble, cardColorString, cardImageString);
        return true;
    }

    private static void showCards(String cardNameString, double cardPriceDouble, String cardColorString, String cardImageString) {
        JLabel cardNameLabel;
        cardNameLabel = new JLabel("Card Name: " + cardNameString);
        cardNameLabel.setBounds(10, 20, 80, 25);
        panel.add(cardNameLabel);

        JLabel cardPriceLabel;
        cardPriceLabel = new JLabel("Price: " + Double.toString(cardPriceDouble));
        cardPriceLabel.setBounds(30, 80, 80, 25);
        panel.add(cardPriceLabel);

        JLabel cardColorLabel;
        cardColorLabel = new JLabel("Color: " + cardColorString);
        cardColorLabel.setBounds(50, 80, 80, 25);
        panel.add(cardColorLabel);

        JLabel cardImageLabel;
        cardImageLabel = new JLabel("Image: " + cardImageString);
        cardImageLabel.setBounds(70, 110, 80, 25);
        panel.add(cardImageLabel);

        frame.setVisible(true);
    }


    public static void main(String[] args) {
        readUsers();
//        try (BufferedReader reader = new BufferedReader(new FileReader("/Users/ibadmomin/Desktop/CS3354/DRAC_FinalDeliverable/src/users.txt"))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                users.add(line);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        for (String user : users) {
//            System.out.println(user);
//        }

        users.add("johndoe");
        users.add("ibadmomin");

        panel = new JPanel();
        frame = new JFrame("Login");
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);

        panel.setLayout(null);
        userLabel = new JLabel("Username");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);
        userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);
        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);
        passwordText = new JPasswordField();
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);
        button = new JButton("Login");
        button.setBounds(10, 80, 80, 25);
        button.addActionListener(new Main());
        panel.add(button);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = userText.getText();
        String password = passwordText.getText();
        if (!userAuthorization(user, password)) {
            JFrame messageFrame = new JFrame("Error");
            JOptionPane.showMessageDialog(messageFrame, "Login failed. Would you like to sign up?",
                    "Error", JOptionPane.ERROR_MESSAGE);
            int signUpOption = JOptionPane.showConfirmDialog(messageFrame, "Would you like to sign up?",
                    "Sign Up", JOptionPane.YES_NO_OPTION);
            if (signUpOption == JOptionPane.YES_OPTION) {
                JFrame signUpFrame = new JFrame("Sign Up");
                JLabel nameLabel = new JLabel("Name: ");
                JTextField nameField = new JTextField(20);
                JLabel phoneNumberLabel = new JLabel("Phone Number: ");
                JTextField phoneNumberField = new JTextField(20);
                JLabel emailLabel = new JLabel("Email: ");
                JTextField emailField = new JTextField(20);
                JLabel usernameLabel = new JLabel("Username: ");
                JTextField usernameField = new JTextField(20);
                JLabel passwordLabel = new JLabel("Password: ");
                JPasswordField passwordField = new JPasswordField(20);
                JButton signUpButton = new JButton("Sign Up");
                signUpFrame.setLayout(new FlowLayout());
                signUpFrame.add(nameLabel);
                signUpFrame.add(nameField);
                signUpFrame.add(phoneNumberLabel);
                signUpFrame.add(phoneNumberField);
                signUpFrame.add(emailLabel);
                signUpFrame.add(emailField);
                signUpFrame.add(usernameLabel);
                signUpFrame.add(usernameField);
                signUpFrame.add(passwordLabel);
                signUpFrame.add(passwordField);
                signUpFrame.add(signUpButton);
                signUpFrame.setSize(300, 350);
                signUpFrame.setVisible(true);
                signUpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                signUpButton.addActionListener(new Main() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (signup(nameField.getText(), phoneNumberField.getText(), emailField.getText(),
                                usernameField.getText(), passwordField.getText()))
                            JOptionPane.showMessageDialog(signUpFrame, "Successfully signed up!",
                                    "Success", JOptionPane.INFORMATION_MESSAGE);
                        else {
                            JOptionPane.showMessageDialog(signUpFrame, "Sign up failed.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                            System.exit(0);
                        }
                    }
                });
            } else {
                System.exit(0);
            }
        } else {
            JFrame messageFrame = new JFrame("Success");
            JOptionPane.showMessageDialog(messageFrame, "Successfully logged in!",
                    "Success", JOptionPane.INFORMATION_MESSAGE);

            button.setVisible(false);
            userText.setVisible(false);
            passwordText.setVisible(false);
            userLabel.setVisible(false);
            passwordLabel.setVisible(false);
            frame.setVisible(false);

            frame = new JFrame("DRAC");
            frame.setSize(300, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            frame.add(panel);

            panel.setLayout(null);
            JLabel cardName = new JLabel("Card Name");
            cardName.setBounds(10, 20, 80, 25);
            panel.add(cardName);
            JTextField cardNameText = new JTextField(20);
            cardNameText.setBounds(100, 20, 165, 25);
            panel.add(cardNameText);
            JLabel cardPrice = new JLabel("Card Price");
            cardPrice.setBounds(10, 50, 80, 25);
            panel.add(cardPrice);
            JTextField cardPriceText = new JTextField(20);
            cardPriceText.setBounds(100, 50, 165, 25);
            panel.add(cardPriceText);
            JLabel cardColor = new JLabel("Card Color");
            cardColor.setBounds(10, 80, 80, 25);
            panel.add(cardColor);
            JTextField cardColorText = new JTextField(20);
            cardColorText.setBounds(100, 80, 165, 25);
            panel.add(cardColorText);
            JLabel cardImage = new JLabel("Card Image");
            cardImage.setBounds(10, 110, 80, 25);
            panel.add(cardImage);
            JTextField cardImageText = new JTextField(20);
            cardImageText.setBounds(100, 110, 165, 25);
            panel.add(cardImageText);
            JButton addCardButton = new JButton("Add Card");
            addCardButton.setBounds(10, 140, 100, 25);
            panel.add(addCardButton);
            frame.setVisible(true);

            addCardButton.addActionListener(new Main() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String cardNameString = cardNameText.getText();
                    double cardPriceDouble = Double.parseDouble(cardPriceText.getText());
                    String cardColorString = cardColorText.getText();
                    String cardImageString = cardImageText.getText();
                    if(addCard(cardNameString, cardPriceDouble, cardColorString, cardImageString)){
                        JFrame messageFrame = new JFrame("Success");
                        JOptionPane.showMessageDialog(messageFrame, "Successfully added card!",
                                "Success", JOptionPane.INFORMATION_MESSAGE);

//                        JLabel cardName;
//                        cardName = new JLabel(cardNameString);
//                        cardName.setBounds(10, 20, 80, 25);
//                        panel.add(cardName);
//
//                        JLabel cardPrice;
//                        cardPrice = new JLabel(Double.toString(cardPriceDouble));
//                        cardPrice.setBounds(10, 50, 80, 25);
//                        panel.add(cardPrice);
//
//                        JLabel cardColor;
//                        cardColor = new JLabel(cardColorString);
//                        cardColor.setBounds(10, 80, 80, 25);
//                        panel.add(cardColor);
//
//                        JLabel cardImage;
//                        cardImage = new JLabel(cardImageString);
//                        cardImage.setBounds(10, 110, 80, 25);
//                        panel.add(cardImage);
//
//                        frame.setVisible(true);

                    } else {
                        JFrame messageFrame = new JFrame("Error");
                        JOptionPane.showMessageDialog(messageFrame, "Failed to add card.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            frame.setVisible(true);


        }

    }
}