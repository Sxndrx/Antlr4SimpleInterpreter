package Memory;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Memory {
    private Stack<Integer> stack;
    private Map<RegisterEnum, Register> registers;

    public Memory() {
        stack = new Stack<>();
        registers = new HashMap<RegisterEnum, Register>();
        registers.put(RegisterEnum.eax, new Register());
        registers.put(RegisterEnum.ebx, new Register());
        registers.put(RegisterEnum.ecx, new Register());
        registers.put(RegisterEnum.edx, new Register());

    }

    public Register getRegister(String regAdress) {
        regAdress = regAdress.toLowerCase();
        switch (regAdress) {
            case "eax":
                return registers.get(RegisterEnum.eax);
            case "ebx":
                return registers.get(RegisterEnum.ebx);
            case "ecx":
                return registers.get(RegisterEnum.ecx);
            case "edx":
                return registers.get(RegisterEnum.edx);
        }
        return null;
    }

    public Stack<Integer> getStack() {
        return stack;
    }

    @Override
    public String toString() {
        return "eax[" + registers.get(RegisterEnum.eax).getValue() + "[ebx]" + registers.get(RegisterEnum.ebx).getValue() + "]ecx[" + registers.get(RegisterEnum.ecx).getValue() + "]edx[" +
                registers.get(RegisterEnum.edx).getValue() + "] \n Stack: " + stack.toString();
    }
}
