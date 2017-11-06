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
        line = "";
        BiFunction<CodeNode, Integer, Void> toTextFunction = (node, step) -> {
            if(node instanceof ProgramNode){
                txtBuilder.append(TextConstants.program_start).append(node).append("\n");
                line += TextConstants.vertical_bar;
            }
            else{
                if(node instanceof WhileNode){
                    txtBuilder.append(line).append(String.format(TextConstants.while_start_text, node));
                    line += TextConstants.while_current + "\t";
                }
                else{
                    if(node instanceof IfNode){

                    }
                    else{
                        if(node instanceof ModuleNode){

                        }
                        else{
                            if(node instanceof LineNode){

                            }
                        }
                    }
                }
            }
            return null;
        };

        recursiveRead(da, 0,null);
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
