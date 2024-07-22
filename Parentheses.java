import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
public class Parentheses{
    public static void main(String[] args) {
        Stack<Character> s = new Stack<Character>();
        boolean check = true;
        while (!StdIn.isEmpty()){
            char c = StdIn.readChar();
            if (c == '(' || c == '{' || c == '['){
                s.push(c);
            }else{
                if (c == ')' && s.pop() == '('){
                    continue;
                }else if (c == '}' && s.pop() == '{'){
                    continue;
                }else if (c == ']' && s.pop() == '['){
                    continue;
                }else{
                    check = false;
                    break;
                }
            }
        }
        StdOut.print(check);
    }
}