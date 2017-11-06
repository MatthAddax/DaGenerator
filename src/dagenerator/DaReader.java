package dagenerator;

import dagenerator.models.CodeNode;
import org.jetbrains.annotations.Contract;

import java.util.concurrent.Callable;
import java.util.function.Function;

public class DaReader {

    public static void toTXT(CodeNode da){
        recursiveRead(da, null, 0);
    }

    @Contract("_ -> fail")
    public static void toPDF(CodeNode da){
        throw new UnsupportedOperationException();
    }

    private static void recursiveRead(CodeNode da, Function<CodeNode, Void> func, int step){
        //int step = mStep;
        System.out.println(step + " " + da.toString() + "\n");
        if(da.getChilds()!= null){
            for(CodeNode child : da.getChilds()){
                recursiveRead(child, func, step + 1);
            }
        }
    }
}
