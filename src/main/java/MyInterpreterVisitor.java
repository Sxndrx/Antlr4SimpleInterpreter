public class MyInterpreterVisitor extends InterpreterBaseVisitor<Integer> {
    private CPU CPU;


    public MyInterpreterVisitor(CPU CPU) {
        this.CPU = CPU;
    }


    public Integer visitMov(InterpreterParser.MovContext ctx) {
        Integer number;
        String regAdress = getRegAdress(ctx.register());
        try {
            number = new Integer(this.visit(ctx.expression()));
            CPU.mov(number, regAdress);
        } catch (NullPointerException e) {
            CPU.getMemory().getRegister(regAdress).setKnownValue(false);
        }
        return 0;
    }

    public Integer visitXor(InterpreterParser.XorContext ctx) {
        Integer number;
        String regAdress = getRegAdress(ctx.register());
        try {
            number = new Integer(this.visit(ctx.expression()));
            CPU.xor(number, regAdress);
        } catch (NullPointerException e) {
            if (getLeftXorReg(ctx.expression()).equals(regAdress)) {
                CPU.xor(true, regAdress);
            } else
                CPU.getMemory().getRegister(regAdress).setKnownValue(false);
        }
        return 0;
    }

    public Integer visitInt(InterpreterParser.IntContext ctx) {
        return this.visitChildren(ctx);
    }

    public Integer visitPush(InterpreterParser.PushContext ctx) {
        Integer number;
        try {
            number = this.visit(ctx.expression());
            CPU.push(number);
        } catch (NullPointerException e) {
            CPU.push(null);
        }
        return 0;
    }

    public Integer visitInt_function(InterpreterParser.Int_functionContext ctx) {
        CPU.int_0x80();
        return 0;
    }

    public Integer visitReg(InterpreterParser.RegContext ctx) {
        return this.visitChildren(ctx);
    }

    public Integer visitParExp(InterpreterParser.ParExpContext ctx) {
        return this.visit(ctx.expression());
    }

    public Integer visitMultExp(InterpreterParser.MultExpContext ctx) {
        Integer left = this.visit(ctx.left);
        Integer right = this.visit(ctx.right);
        return left * right;
    }

    public Integer visitAddSubExp(InterpreterParser.AddSubExpContext ctx) {
        Integer left = this.visit(ctx.left);
        Integer right = this.visit(ctx.right);
        if (ctx.MINUS() != null) {
            return left - right;
        } else
            return left + right;
    }

    public Integer visitNumb(InterpreterParser.NumbContext ctx) {
        return this.visitChildren(ctx);
    }

    public Integer visitNumber(InterpreterParser.NumberContext ctx) {
        Integer number = new Integer(ctx.NUMBER().getText());
        return number;
    }

    public Integer visitRegister(InterpreterParser.RegisterContext ctx) {
        String regAdress = getRegAdress(ctx);
        Integer regValue = CPU.getMemory().getRegister(regAdress).getValue();
        if (CPU.getMemory().getRegister(regAdress).isKnownValue()) {
            return regValue;
        } else return null;
    }

    public String getRegAdress(InterpreterParser.RegisterContext ctx) {
        return ctx.getText().substring(1);
    }

    public String getLeftXorReg(InterpreterParser.ExpressionContext ctx) {
        return ctx.getText().substring(1).toLowerCase();
    }
}