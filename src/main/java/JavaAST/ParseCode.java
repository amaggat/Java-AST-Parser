package JavaAST;

import Model.*;
import ReadFile.ReadMultipleFile;
import org.eclipse.jdt.core.dom.*;
import org.eclipse.jdt.internal.compiler.ast.ConstructorDeclaration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.*;

public class ParseCode {

    ReadMultipleFile returnFile;

    public ClassProperty visit(String filePath) throws FileNotFoundException, IOException
    {
        final ClassProperty buffer = new ClassProperty();

        ASTParser parser = ASTParser.newParser(AST.JLS13);
        char[] fileContent = returnFile.getFileContent(filePath).toCharArray();
        parser.setSource(fileContent);

        CompilationUnit cu = (CompilationUnit) parser.createAST(null);

        cu.accept(new ASTVisitor() {

            @Override
            public boolean visit(FieldDeclaration node) {
                ClassVariable var = new ClassVariable(node.getType().toString(), node.fragments().toString() ,node.modifiers());
                buffer.addVar(var);
                return false;
            }

            @Override
            public boolean visit(MethodDeclaration node)
            {
                if(!node.isConstructor())
                {
                    ClassMethod method = new ClassMethod(node.getReturnType2().toString(), node.getName().toString(), node.modifiers(),node.parameters());
                    buffer.addMethod(method);
                }
                else
                {
                    ClassConstructor cons = new ClassConstructor(node.getName().toString(), node.parameters(), node.modifiers());
                    buffer.addCons(cons);
                }
                return false;
            }

        });


        return(buffer);
    }

}
