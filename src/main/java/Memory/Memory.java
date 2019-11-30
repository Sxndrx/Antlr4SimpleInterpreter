package Memory;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Memory {
    private Stack<Integer> stack;
    private Map<RegName, Register> registers;

    public Memory() {
        stack = new Stack<>();
        registers = new HashMap<RegName, Register>();
        registers.put(RegName.ebx, new Register());
        registers.put(RegName.ecx, new Register());
        registers.put(RegName.edx, new Register());

    }

    public Register getRegister(String regAdress) {
        regAdress = regAdress.toLowerCase();
        switch (regAdress) {
            case "ebx":
                return registers.get(RegName.ebx);
            case "ecx":
                return registers.get(RegName.ecx);
            case "edx":
                return registers.get(RegName.edx);
        }
        return null;
    }

    public Stack<Integer> getStack() {
        return stack;
    }

    @Override
    public String toString() {
        return "ebx[" + registers.get(RegName.ebx).getValue() + "]ecx[" + registers.get(RegName.ecx).getValue() + "]edx[" +
                registers.get(RegName.edx).getValue() + "] \n Stack: " + stack.toString();
    }
}
