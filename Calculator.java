import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 public class Calculator {

    private JFrame frame;
    private JTextField textField;
    private String currentInput = "";

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Calculator window = new Calculator();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Calculator() {
        frame = new JFrame();
        frame.setBounds(100, 100, 400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 24));
        textField.setBounds(10, 10, 360, 50);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        int xPos = 10, yPos = 70;
        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Tahoma", Font.PLAIN, 24));
            button.setBounds(xPos, yPos, 80, 80);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    buttonClicked(e);
                }
            });
            frame.getContentPane().add(button);
            xPos += 90;
            if (xPos > 270) {
                xPos = 10;
                yPos += 90;
            }
        }

        JButton clearButton = new JButton("C");
        clearButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
        clearButton.setBounds(10, 430, 80, 50);
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearTextField();
            }
        });
        frame.getContentPane().add(clearButton);
    }

    private void buttonClicked(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("=")) {
            try {
                currentInput = evaluateExpression(currentInput);
                textField.setText(currentInput);
            } catch (Exception ex) {
                textField.setText("Error");
                currentInput = "";
            }
        } else {
            currentInput += command;
            textField.setText(currentInput);
        }
    }

    private String evaluateExpression(String expression) {
        // This method assumes simple operations with no error handling.
        // You can use something like scripting engine for better evaluation.
        // Here it's just a basic evaluation of an expression:
        try {
            double result = new ScriptEngineManager().getEngineByName("JavaScript").eval(expression);
            return String.valueOf(result);
        } catch (Exception e) {
            return "Error";
        }
    }

    private void clearTextField() {
        currentInput = "";
        textField.setText("");
    }
}
