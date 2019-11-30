import Memory.Memory;

public class CPU {
    Memory memory;

    public CPU() {
        this.memory = new Memory();
    }

    public void mov(Integer number, String register) {
        memory.getRegister(register).setValue(number);
    }

    public void xor(Integer number, String register) {
        int xored = number ^ memory.getRegister(register).getValue();
        memory.getRegister(register).setValue(xored);
    }

    /**
     * xor register with register
     **/
    public void xor(boolean same, String register) {
        memory.getRegister(register).setValue(0);
        memory.getRegister(register).setKnownValue(true);
    }

    public void int_0x80() {
        if (memory.getStack().size() > 0) {
            Integer topElement = memory.getStack().pop();
            if (topElement != null)
                System.out.println(topElement);
            else
                System.out.println("???");
        } else System.out.println("???");
    }

    public void push(Integer number) {
        memory.getStack().push(number);
    }


    public Memory getMemory() {
        return memory;
    }
}
