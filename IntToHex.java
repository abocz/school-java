import java.util.Stack;

/**
 * conversion using stack
 */
public class IntToHex extends Stack {
    String digits = "0123456789ABCDEF";

    //This is where the the conversion from decimal to hex takes place and the valued is pushed to stack
    public void decToStack(int decimal) {
        if (decimal == 0) {
            push(0);
        }
        while (decimal > 0) {
            int digit = decimal % 16;
            push(digits.charAt(digit));
            decimal = decimal / 16;
        }
    }
    //pop a value from stack
    public String getHexa() {
        String h = "";
        while (!isEmpty()) {
            h = h + pop();
        }
        return h;
    }
}
