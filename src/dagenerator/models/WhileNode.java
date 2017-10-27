/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dagenerator.models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dagenerator.exceptions.ConditionCleanCodeException;

public class WhileNode extends CodeNode{
    private String condition;

    public WhileNode(CodeNode parent, String condition) throws ConditionCleanCodeException{
            super(parent);
            setCondition(condition);
    }


    public String getCondition(){
            return condition;
    }

    public void setCondition(String condition) throws ConditionCleanCodeException{
        /*Totalement faux*/
        /*Pattern beginWithISPattern = Pattern.compile("^is[a-ZA-Z0-9_-]");
        Pattern hasComparisonOperatorPattern = Pattern.compile("*(<)(<=)(==)(>=)(>)*");

        Matcher beginWithISMatcher = beginWithISPattern.matcher(condition);
        Matcher hasComparisonOperatorMatcher = hasComparisonOperatorPattern.matcher(condition);*/

        if(/*beginWithISMatcher.find() || hasComparisonOperatorMatcher.find()*/true)
            this.condition = condition;
        else
            throw new ConditionCleanCodeException();
    }

    @Override
    public String toString(){
        return condition;
    }
}
