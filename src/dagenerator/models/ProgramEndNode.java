package dagenerator.models;

public class ProgramEndNode extends CodeNode {
    public ProgramEndNode(CodeNode parent) {
        super(parent);
    }

    @Override
    public String toString() {
        return "program_end";
    }
}
