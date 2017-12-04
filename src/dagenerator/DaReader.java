package dagenerator;

import dagenerator.models.*;
import dagenerator.utils.ColorConstants;
import dagenerator.utils.HtmlConstants;
import dagenerator.utils.TextConstants;
import j2html.TagCreator;
import j2html.tags.ContainerTag;

import java.util.function.BiFunction;

import static j2html.TagCreator.*;


public class DaReader {
    private StringBuilder txtBuilder;
    private TagCreator tagCreator;
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
                                    line = line.substring(0, line.length() - 2);
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
                                        else{
                                            if(node instanceof ProgramEndNode){
                                                txtBuilder.append(line).append(TextConstants.program_end).append("\n");
                                            }
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

    public void toHTML(CodeNode da){
        String spanLayout = "<span style=\"color:%s\">%s</span>";
        txtBuilder = new StringBuilder();

        txtBuilder.append("<!DOCTYPE html>\n" +
                "<html>\n" +
                "\t<head>\n" +
                "\t\t<meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\" />\n" +
                "\t\t<title>DA s3zqos</title>\n" +
                "\t</head>\n" +
                "\t<body>\n" +
                "\t\t<pre>\n");
        String htmlBottom = "</pre>\n" +
                "\t</body>\n" +
                "</html>";


        BiFunction<CodeNode, Integer, Void> toHtmlFunction = (node, step) -> {
            createTxtLineFromParents(node);
            if(node instanceof ProgramNode){
                txtBuilder.append(TextConstants.program_start).append(String.format(spanLayout, ColorConstants.TITLE_ORANGE, node)).append("\n");
                line += TextConstants.vertical_bar;
            }
            else{
                if(node instanceof WhileNode){
                    txtBuilder.append(line).append(String.format(HtmlConstants.while_start_text, node)).append("\n");
                }
                else{
                    if(node instanceof IfNode){
                        txtBuilder.append(line).append(String.format(HtmlConstants.if_start_text, node)).append("\n");
                    }
                    else{
                        if(node instanceof ModuleNode){
                            txtBuilder.append(line).append(((ModuleNode)node).getHtmlUpLayer()).append("\n");
                            txtBuilder.append(line).append(((ModuleNode)node).getHtmlMiddleLayer()).append("\n");
                            txtBuilder.append(line).append(((ModuleNode)node).getHtmlBottomLayer()).append("\n");
                        }
                        else{
                            if(node instanceof LineNode){
                                /*
                                * Check if comment
                                * Check if keyword
                                */
                                txtBuilder.append(line).append(node).append("\n");
                            }
                            else{
                                if(node instanceof IfElseNode){
                                    line = line.substring(0, line.length() - 2);
                                    txtBuilder.append(line).append(HtmlConstants.if_else_text).append("\n");
                                }
                                else{
                                    if(node instanceof IfEnd){
                                        txtBuilder.append(line).append(HtmlConstants.if_end_text).append("\n");
                                    }
                                    else{
                                        if(node instanceof WhileEnd){
                                            txtBuilder.append(line).append(HtmlConstants.while_end).append("\n");
                                        }
                                        else{
                                            if(node instanceof ProgramEndNode){
                                                txtBuilder.append(line).append(HtmlConstants.program_end).append("\n");
                                            }
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

        recursiveRead(da, 0, toHtmlFunction);

        txtBuilder.append(htmlBottom);

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
