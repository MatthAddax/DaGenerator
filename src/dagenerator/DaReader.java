package dagenerator;

import dagenerator.models.CodeNode;
import org.jetbrains.annotations.Contract;

import java.util.concurrent.Callable;
import java.util.function.Function;

public class DaReader {

    public static void toTXT(CodeNode da){
        recursiveRead(da, null);
    }

    @Contract("_ -> fail")
    public static void toPDF(CodeNode da){
        throw new UnsupportedOperationException();
    }

    private static void recursiveRead(CodeNode da, Function<CodeNode, Void> func){
        System.out.println(da.toString() + "\n");
        if(da.getChilds()!= null){
            for(CodeNode child : da.getChilds()){
                recursiveRead(child, func);
            }
        }
    }
}
