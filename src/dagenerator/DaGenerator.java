/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dagenerator;

import dagenerator.exceptions.ConditionCleanCodeException;
import dagenerator.exceptions.NullRootException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dagenerator.models.*;

public class DaGenerator{
        /**
                Parcourir chaque ligne
                Compter les tabs (ou espaces)
                ouvrir une balise (while ou if pour le moment)
                si dans une balise (et sera d'office dans une balise)
                        afficher les barres sur le côté jusqu'à la fin de la balise
                        quand à la fin, afficher fin de balise

                pendant balise possibilité de texte simple ou de module
                ATTENTION : un module prends 3 espaces de hautµ

                ATTENTION 2 : faire boucle ou récursive????
        */
		
	/*
		Patterns
	*/
	private final Pattern while_start = Pattern.compile("^\\s+do while\\((.*?)\\)\\s*$");
	private final Pattern while_end = Pattern.compile("^\\s+end while\\s*$");
	private final Pattern program_start = Pattern.compile("^\\*\\s*(.+?)\\s*$");
	private final Pattern program_end = Pattern.compile("^end program");
	private final Pattern if_start = Pattern.compile("^\\s+if\\((.*?)\\)\\s*$");
	private final Pattern if_else = Pattern.compile("^\\s+else\\s*$");
	private final Pattern if_end = Pattern.compile("^\\s+end if\\s*$");
	private final Pattern module = Pattern.compile("^\\s+mod:(.*?);(.*?);(.*?)\\s*$");
	/*
		End Patterns
	*/
	
	private CodeNode root;
	
	public CodeNode parse(BufferedReader unparsed_da) throws NullRootException, ConditionCleanCodeException, IOException {
            /**
                    Work with buffers instead?
                    Will allow work from any kind of stream (string, file, network, etc)
            */
            //String[] lines = unparsed_da.split("\r\n");

            StringBuilder sb = new StringBuilder();
            int currentLineNumber = 0;
            String line;

            root = new ActionDiagramNode();

            while((line = unparsed_da.readLine()) != null){
                currentLineNumber++;
                Matcher matcherWhileStart = while_start.matcher(line);
                Matcher matcherWhileEnd = while_end.matcher(line);
                Matcher matcherProgramStart = program_start.matcher(line);
                Matcher matcherProgramEnd = program_end.matcher(line);
                Matcher matcherIfStart = if_start.matcher(line);
                Matcher matcherIfElse = if_else.matcher(line);
                Matcher matcherIfEnd = if_end.matcher(line);
                Matcher matcherModule = module.matcher(line);

                //boolean programStartFound = matcherProgramStart.find();
                //int groupCount = matcherProgramStart.groupCount();
                if(matcherProgramStart.find()){
                    /*
                        Ajouter noeud dans l'arbre ATTENTION : program_start = NOEUD_UNIQUE!!
                        Sinon imbriquer matcher suivant
                    */
                    if(root == null)
                        throw new NullRootException(currentLineNumber);

                    CodeNode programNode = new ProgramNode(root, matcherProgramStart.group(1));
                    root.addChild(programNode);
                    root = programNode;

                }
                else
                {
                    /*
                        if or loop or module => new code node and change current root
                        end if end loop end of module => go to previous root

                        if anything else => add new element to list
                    **/
                    if(matcherIfStart.find()){
                        CodeNode ifStart = new IfNode(root, matcherIfStart.group(1));
                        if(root!= null)
                            root.addChild(ifStart);
                        root = ifStart;
                    }
                    else{
                        if(matcherIfElse.find()){
                            if(root instanceof IfNode){
                                CodeNode ifElse = new IfElseNode(root);
                                root.addChild(ifElse);
                            }
                        }
                        else{
                            if(matcherWhileStart.find()){
                                CodeNode whileStart = new WhileNode(root, matcherWhileStart.group(1));
                                if(root!= null)
                                    root.addChild(whileStart);
                                root = whileStart;
                            }
                            else{
                                if(matcherModule.find()){
                                    String name = matcherModule.group(1);
                                    String stringInput = matcherModule.group(2);
                                    String stringOutput = matcherModule.group(3);
                                    CodeNode module = new ModuleNode(root, name, stringInput, stringOutput);
                                    if(root!= null)
                                        root.addChild(module);
                                }
                                else{
                                    if(matcherIfEnd.find()){
                                        if(root!= null && root.getParent() != null && root instanceof IfNode){
                                            CodeNode ifEnd = new IfEnd(root.getParent());
                                            root.getParent().addChild(ifEnd);
                                        }

                                        root = root.getParent();
                                    }
                                    else{
                                        if(matcherWhileEnd.find()){
                                            if(root!= null && root.getParent() != null && root instanceof WhileNode){
                                                CodeNode whileEnd = new WhileEnd(root.getParent());
                                                root.getParent().addChild(whileEnd);
                                            }

                                            root = root.getParent();
                                        }
                                        else{
                                            if(matcherProgramEnd.find()){
                                                if(root!= null && root.getParent() != null && root instanceof ProgramNode){
                                                    CodeNode programEnd = new ProgramEndNode(root.getParent());
                                                    root.getParent().addChild(programEnd);
                                                }

                                                root = root.getParent();
                                            }
                                            else{
                                                if(root != null && line!=null){
                                                    if(!line.matches("^\\s+$")){
                                                        CodeNode node = new LineNode(root, line.trim());
                                                        root.addChild(node);
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            while(root != null && root.getParent() != null)
                root = root.getParent();

            return root;
	}
	
}
