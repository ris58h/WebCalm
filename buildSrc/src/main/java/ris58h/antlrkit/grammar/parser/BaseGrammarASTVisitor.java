package ris58h.antlrkit.grammar.parser;

import org.antlr.v4.tool.ast.*;

interface BaseGrammarASTVisitor extends GrammarASTVisitor {
    @Override
    default Object visit(GrammarAST node) {
        return null;
    }

    @Override
    default Object visit(GrammarRootAST node) {
        return visit((GrammarAST) node);
    }

    @Override
    default Object visit(RuleAST node) {
        return visit((GrammarAST) node);
    }

    @Override
    default Object visit(BlockAST node) {
        return visit((GrammarAST) node);
    }

    @Override
    default Object visit(OptionalBlockAST node) {
        return visit((GrammarAST) node);
    }

    @Override
    default Object visit(PlusBlockAST node) {
        return visit((GrammarAST) node);
    }

    @Override
    default Object visit(StarBlockAST node) {
        return visit((GrammarAST) node);
    }

    @Override
    default Object visit(AltAST node) {
        return visit((GrammarAST) node);
    }

    @Override
    default Object visit(NotAST node) {
        return visit((GrammarAST) node);
    }

    @Override
    default Object visit(PredAST node) {
        return visit((GrammarAST) node);
    }

    @Override
    default Object visit(RangeAST node) {
        return visit((GrammarAST) node);
    }

    @Override
    default Object visit(SetAST node) {
        return visit((GrammarAST) node);
    }

    @Override
    default Object visit(RuleRefAST node) {
        return visit((GrammarAST) node);
    }

    @Override
    default Object visit(TerminalAST node) {
        return visit((GrammarAST) node);
    }
}
