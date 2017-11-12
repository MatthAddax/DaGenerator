package dagenerator.models;

public class WhileEnd extends CodeNode {
    public WhileEnd(CodeNode parent) {
        super(parent);
    }

    @Override
    public String toString() {
        return "while_end";
    }
}
