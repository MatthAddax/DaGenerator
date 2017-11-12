package dagenerator.models;

public class IfEnd extends CodeNode {
    public IfEnd(CodeNode parent) {
        super(parent);
    }

    @Override
    public String toString() {
        return "if_end";
    }
}
