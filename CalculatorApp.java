import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * CalculatorApp
 */
public class CalculatorApp extends JFrame implements ActionListener {
    private JTextField textField; //ユーザーが数値を入力し，結果を表示するためのテキストフィールド
    private JButton[] numberButtons; //数字用のボタン
    private JButton[] operatorButtons; //演算子用のボタン
    private JButton clearButton, equalButton; //C, =用ボタン
    private JPanel panel; //コンポーネントをまとめるためのパネル

    private double num1 = 0, num2 = 0, result = 0; //2つの数値と結果を格納するための変数
    private char operator; //演算子を格納するための変数

    public CalculatorApp() {
        setTitle("簡易計算機");
        setSize(400, 400);
    }

    public void actionPerformed(ActionEvent e) {
        ;
    }

    public static void main(String[] args) {
        CalculatorApp app = new CalculatorApp();
        app.setVisible(true);
    }
}