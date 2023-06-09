import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * CalculatorApp
 */
//public classなので他クラスからアクセス可能
//extends JFrameでJFrameクラスを継承
//implements ActionListenerでActionListenerインターフェースを実装
//CalculatorAppがActionListenerを実装することでアクションイベントを処理できる
public class CalculatorApp extends JFrame implements ActionListener {
    //JTextFieldはSwingコンポーネントの一つ
    //JTextFieldのインスタンスを参照するためのプライベートインスタンス変数textFieldを宣言
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //ウィンドウを閉じたらアプリケーションも終了

        textField = new JTextField(); //JTextFieldクラスのインスタンスをnewで作成し，それをtextFieldインスタンス変数に代入
        textField.setEditable(false); //JTextFieldの編集を不可に設定(数値のボタンを用意しているのでそれで)
        textField.setHorizontalAlignment(JTextField.RIGHT); //テキストフィールドで表示する数値を右に寄せる
        textField.setFont(new Font("Aril", Font.PLAIN, 40)); //Arialフォント，プレーンスタイル，フォントサイズ24

        numberButtons = new JButton[10]; //0~9の10個の数字ボタンを作成するためのインスタンス変数
        for(int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i)); //int型のiをString型に変換して新しいボタンを作成
            numberButtons[i].addActionListener(this); //addActionListenerはボタンがクリックされた際のアクションイベントを処理するために使用(ここではactionFerformedがアクションイベントを処理する)
        }

        operatorButtons = new JButton[4]; //+, -, *, /
        String[] operators = {"+", "-", "*", "/"};
        for(int i = 0; i < 4; i++) {
            operatorButtons[i] = new JButton(operators[i]);
            operatorButtons[i].addActionListener(this);
        }

        clearButton = new JButton("C");
        clearButton.addActionListener(this);
        equalButton = new JButton("=");
        equalButton.addActionListener(this);

        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4)); //縦5マス，横4マス

        panel.add(clearButton); //左上に"C"追加
        panel.add(new JLabel("")); //スペース確保
        panel.add(new JLabel("")); //スペース確保
        panel.add(operatorButtons[0]); //右上に"+"を追加

        //電卓の表示に合わせて数字と演算子を代入(1~9,-,*,/)
        int buttonIndex = 1;
        for (int i = 1; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttonIndex < 10) {
                    panel.add(numberButtons[buttonIndex++]);
                }
            }
            panel.add(operatorButtons[i]);
        }
        panel.add(numberButtons[0]);
        panel.add(new JLabel(""));
        panel.add(new JLabel(""));
        panel.add(equalButton);

        setLayout(new BorderLayout()); //BorderLayoutはコンポーネントを東西南北中央に配置できる(レイアウトを整える)
        add(textField, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    //オーバーライド：スーパークラスのメソッドをサブクラスで書き直すこと
    //@Overrideが付与されているメソッドはオーバーライドされていなかったらエラーを出力するようになってる
    //クラスはactionPerformedメソッドをオーバーライドしてイベントを処理
    //ActionListenerインターフェースにactionPerformedメソッドが定義されているが，CalculatorAppクラスで再定義している
    @Override
    public void actionPerformed(ActionEvent e) {
        //ActionEvent eはボタンがクリックされたとき
        for(int i = 0; i < 10; i++) {
            if(e.getSource() == numberButtons[i]) { //アクションイベントのソースが数字ボタンと等しいか
                textField.setText(textField.getText() + i); //現在のテキストフィールドの値を取得し，クリックされたボタンと連結する
                // "12"という数字を打つ際に"1"と"2"を順番に押すので，入力されている"1"と取得してから入力した"2"を連結して"12"を作成する
            }
        }

        //"C"が押されたら空にする
        if(e.getSource() == clearButton) {
            textField.setText("");
        }

        for(int i = 0; i < 4; i++) {
            if(e.getSource() == operatorButtons[i]) {
                num1 = Double.parseDouble(textField.getText()); //getText()で取得したString型の文字列をDouble型に変換"12"→12
                operator = operatorButtons[i].getText().charAt(0); //取得した文字列の最初の文字index:0が演算子になる
                textField.setText(""); //取得後のテキストフィールドは空にする
            }
        }

        //電卓計算処理
        if(e.getSource() == equalButton) {
            num2 = Double.parseDouble(textField.getText());

            //char型比較なのでString.equals()ではなく==で良いがシングルコーテーションで囲む
            switch(operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    result = num1 / num2;
                    break;
            }

            textField.setText(String.valueOf(result));
        }
    }

    public static void main(String[] args) {
        new CalculatorApp();
    }
}