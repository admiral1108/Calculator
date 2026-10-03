package Calculator;
import javax.swing.*;
public class calc {
    static class process{
        String input;
        int pos;
        float answer;
        int a;
        JLabel textLabel;
        float temp;
        int decimal;
        int digits;
        process(String input,JLabel textField){
            this.input = input;
            this.pos = 0;
            this.a = input.length();
            this.textLabel = textField;
            this.decimal = 0;
            this.digits = 0;
        }
        void recursion_opt(){
            if (pos >= a) {
                final_calc();
                return;
            }
            char operator = input.charAt(pos);
            if(operator=='.'){

            }
            if(operator == '+'){
                pos++;
                answer +=this.recursion_num();
            }
            if(operator == '-'){
                pos++;
                answer -=this.recursion_num();
            }
            if (pos >= a) {
                final_calc();
                return;
            }
            this.recursion_opt();
        }
        void start(){
            answer = recursion_num();

            recursion_opt();
        }
        float recursion_mult(){
            float c = 1;
            while(pos<a&&(input.charAt(pos) == '*'||input.charAt(pos) == '/')){
                if(input.charAt(pos) == '*'){
                    pos++;
                    c=c*recursion_num();
                }
                if(pos<a&&input.charAt(pos) == '/'){
                    pos++;
                    c=c/recursion_num();
                }
            }
            return c;
        }
        float recursion_decimal(){
            float test;
                pos++;
            int decimal = 0;
            while(pos<a&&Character.isDigit(input.charAt(pos))){
            decimal = decimal*10+Character.getNumericValue(input.charAt(pos));
            pos++;
            }
                test = (float)decimal;
                while(decimal!=0){
                    decimal = decimal/10;
                    digits++;
                }
                while(digits!=0){
                    test=test/10;
                    digits--;
                }
                return test;
        }
        float recursion_num(){
            float c = 0;
            while(pos<a&&Character.isDigit(input.charAt(pos))){
            c = c*10+Character.getNumericValue(input.charAt(pos));
            pos++;
            }
            if(pos<a&&(input.charAt(pos) == '*'||input.charAt(pos) == '/')){
                c=c*recursion_mult();
            }
            if(pos<a&&input.charAt(pos) == '.'){
                c=c+recursion_decimal();
            }
            return c;
        }
        void final_calc(){
            textLabel.setText(String.valueOf(answer));
        }
    }
    public static void main(String[] args) {
        JFrame MyWindow = new JFrame("CALCULATOR");
        JLabel TextField = new JLabel("Numbers");
        TextField.setText("0");

        MyWindow.setSize(400,700);
        MyWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MyWindow.setVisible(true);
        MyWindow.setLayout(null);

        TextField.setBounds(200,100,200,50);
        MyWindow.add(TextField);

        JButton[] MyButtons = new JButton[9];
        for(int j = 0;j<3;j++){
            int c = 1;
        for(int i = 3*j;i<3*j+3;i++){
            MyButtons[i] = new JButton(String.valueOf(i+1));
            MyButtons[i].setBounds(100+50*c,200+50*j,40,40);
            MyWindow.add(MyButtons[i]);
            c++;
            MyButtons[i].addActionListener(e ->{
                TextField.setText(TextField.getText()+e.getActionCommand());
            });
        }
        }
        JButton[] SPButtons = new JButton[7];
        SPButtons[0] = new JButton("<-");
        SPButtons[1] = new JButton("+");
        SPButtons[2] = new JButton("-");
        SPButtons[3] = new JButton("*");
        SPButtons[4] = new JButton("/");
        SPButtons[5] = new JButton("0");
        SPButtons[6] = new JButton(".");
        for(int i = 0;i<7;i++){
            SPButtons[i].setBounds(100+50*i,350,60,40);
            if(i==0){
                continue;
            }
            SPButtons[i].addActionListener(e ->{
                TextField.setText(TextField.getText()+e.getActionCommand());
            });
        }
        SPButtons[0].addActionListener(e->{
            TextField.setText(TextField.getText().substring(0,TextField.getText().length()-1));
        });
        for(int i = 0;i<7;i++){
            MyWindow.add(SPButtons[i]);
        }
        JButton calcit = new JButton("=");
        calcit.setBounds(300,450,60,50);
        calcit.addActionListener(press->{
            process proc = new process(TextField.getText(),TextField);
            proc.start();
        });
        MyWindow.add(calcit);

}
}
