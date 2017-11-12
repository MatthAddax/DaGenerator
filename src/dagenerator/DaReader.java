package dagenerator;

import dagenerator.models.*;
import dagenerator.utils.TextConstants;
import org.jetbrains.annotations.Contract;

import java.text.Format;
import java.util.function.BiFunction;

public class DaReader {
    private StringBuilder txtBuilder;
    private String line;
    public void toTXT(CodeNode da){
        txtBuilder = new StringBuilder();

        BiFunction<CodeNode, Integer, Void> toTextFunction = (node, step) -> {
            createTxtLineFromParents(node);
            if(node instanceof ProgramNode){
                txtBuilder.append(TextConstants.program_start).append(node).append("\n");
                line += TextConstants.vertical_bar;
            }
            else{
                if(node instanceof WhileNode){
                    txtBuilder.append(line).append(String.format(TextConstants.while_start_text, node)).append("\n");
                }
                else{
                    if(node instanceof IfNode){
                        txtBuilder.append(line).append(String.format(TextConstants.if_start_text, node)).append("\n");
                    }
                    else{
                        if(node instanceof ModuleNode){
                            txtBuilder.append(line).append(((ModuleNode)node).getUpLayer()).append("\n");
                            txtBuilder.append(line).append(((ModuleNode)node).getMiddleLayer()).append("\n");
                            txtBuilder.append(line).append(((ModuleNode)node).getBottomLayer()).append("\n");
                        }
                        else{
                            if(node instanceof LineNode){
                                txtBuilder.append(line).append(node).append("\n");
                            }
                            else{
                                if(node instanceof IfElseNode){
                                    txtBuilder.append(line).append(TextConstants.if_else_text).append("\n");
                                }
                                else{
                                    if(node instanceof IfEnd){
                                        txtBuilder.append(line).append(TextConstants.if_end_text).append("\n");
                                    }
                                    else{
                                        if(node instanceof WhileEnd){
                                            txtBuilder.append(line).append(TextConstants.while_end).append("\n");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return null;
        };

        recursiveRead(da, 0,toTextFunction);

        System.out.println(txtBuilder.toString());
    }

    private void createTxtLineFromParents(CodeNode node) {
        line = "";
        while(node.getParent()!=null){
            node = node.getParent();
            if(node instanceof WhileNode){
                line = TextConstants.while_current + "\t" + line;
            }
            else{
                if(node instanceof IfNode || node instanceof ProgramNode){
                    line = TextConstants.vertical_bar + "\t" + line;
                }
            }
        }
    }

    @Contract("_ -> fail")
    public void toPDF(CodeNode da){
        throw new UnsupportedOperationException();
    }

    private void recursiveRead(CodeNode da, int step, BiFunction<CodeNode, Integer, Void> func){
        //int step = mStep;
        if(func != null)
            func.apply(da, step);
        else
            System.out.println(step + " : " + da);
        if(da.getChilds()!= null){
            for(CodeNode child : da.getChilds()){
                recursiveRead(child, step + 1, func);
            }
        }
    }
}
