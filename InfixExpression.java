import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
public class InfixExpression {
    public static void main(String[] args) {
        Stack<String> ops = new Stack<String>();
        Stack<String> vals = new Stack<String>();

        while (!StdIn.isEmpty()){
            String s = StdIn.readString();
            if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")){
                ops.push(s);
            }else if (s.equals(")")){
                String val2 = vals.pop();
                String val1 = vals.pop();
                String op = ops.pop();
                String comb = "( " + val1 + op + val2 + " )";
                vals.push(comb); 
            }else{
                vals.push(s);
            }
        }
        StdOut.println(vals.pop());
    }
}
