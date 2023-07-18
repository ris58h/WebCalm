package ris58h.antlrkit.grammar.parser;

import org.antlr.v4.tool.ast.GrammarAST;

import java.util.List;

class RecursiveGrammarASTVisitor implements BaseGrammarASTVisitor {
    @Override
    public Object visit(GrammarAST node) {
        Object result = BaseGrammarASTVisitor.super.visit(node);
        List<?> children = node.getChildren();
        if (children != null) {
            for (Object child : children) {
                ((GrammarAST) child).visit(this);
            }
        }
        return result;
    }
}
