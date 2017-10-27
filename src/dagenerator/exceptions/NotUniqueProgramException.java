/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dagenerator.exceptions;

/**
 *
 * @author Matthieu
 */
public class NotUniqueProgramException extends Throwable{
    private int lineNumber;
    public NotUniqueProgramException(int currentLineNumber) {
        lineNumber = currentLineNumber;
    }
    
}
