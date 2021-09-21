import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class Calculator3  implements ActionListener{

    JFrame frame;
    JTextField textField;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[9];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton, negButton;
    JPanel panel;

    Font myFont = new Font("Ink Free", Font.BOLD,30);

    double num1 = 0, num2 = 0, result = 0;
    char operator;

    Calculator3() {

        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 550);
        frame.setLayout(null);

        textField = new JTextField();
        textField.setBounds(50, 25, 300, 50);
        textField.setFont(myFont);
        textField.setEditable(true);

        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Delete");
        clrButton = new JButton("Clear");
        negButton = new JButton("(-)");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton;

        for(int i = 0; i< 9; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
        }
        for(int i = 0; i< 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
        }

        negButton.setBounds(50, 430, 100, 50);
        delButton.setBounds(150, 430, 100, 50);
        clrButton.setBounds(250, 430, 100, 50);

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);

        frame.add(panel);
        frame.add(negButton);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textField);
        frame.setVisible(true);

    }
    public static void main(String[] args) {

        new Calculator3();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String var1, varop2, var3;

        for(int i=0;i<10;i++) {
            if(e.getSource() == numberButtons[i]) {
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }
        if(e.getSource() == decButton) {
            textField.setText(textField.getText().concat("."));
        }
        if(e.getSource() == addButton) {
            textField.setText(textField.getText().concat("+"));
            operator = '+';
        }
        if(e.getSource() == subButton) {
            textField.setText(textField.getText().concat("-"));
            operator = '-';
        }
        if(e.getSource() == mulButton) {
            textField.setText(textField.getText().concat("*"));
            operator = '*';
        }
        if(e.getSource() == divButton) {
            textField.setText(textField.getText().concat("/"));
            operator = '/';
        }
        if(e.getSource() == equButton) {
            StringTokenizer st = new StringTokenizer((textField.getText()), "+-*/", true);

            var1 = st.nextToken();
            varop2 = st.nextToken();
            var3 = st.nextToken();
            if (Pattern.matches("[a-zA-Z]+", var1)){
                char oper = varop2.charAt(0);
                RomanToNumber rom1c = new RomanToNumber();
                RomanToNumber rom3c = new RomanToNumber();
                int a = rom1c.romanToDecimal(var1);
                int b = rom3c.romanToDecimal(var3);
                int res1 = math(a, b, oper);
                NumberToRoman sumr = new NumberToRoman ();
                String q = sumr.intToRoman(res1);
                textField.setText(q);

            }
            else {
                num1 = Double.parseDouble(var1);
                operator = varop2.charAt(0);
                num2 = Double.parseDouble(var3);

                switch (operator) {
                    case '+' -> result = num1 + num2;
                    case '-' -> result = num1 - num2;
                    case '*' -> result = num1 * num2;
                    case '/' -> result = num1 / num2;
                }
                textField.setText(String.valueOf(result));
                num1 = result;
            }
        }
        if(e.getSource() == clrButton) {
            textField.setText("");
        }
        if(e.getSource() == delButton) {
            String string = textField.getText();
            textField.setText("");
            for(int i=0; i<string.length()-1; i++) {
                textField.setText(textField.getText() + string.charAt(i));
            }
        }
        if(e.getSource() == negButton) {
            double temp = Double.parseDouble(textField.getText());
            temp *=-1;
            textField.setText(String.valueOf(temp));
        }


    }
    public static int math(int x, int y, char z){
        int res = 0;
        if (1 <= x && 10 >= x && 1 <= y && 10 >= y) {
            if (z == '+') {res = x + y;}
            else if (z == '-') {res = x - y;}
            else if (z == '*') {res = x * y;}
            else if (z == '/') {res = x / y;}
        }
        return res;
    }
}
