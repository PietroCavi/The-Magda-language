package Magda.ProgramTree.Expressions;
import Magda.ProgramTree.*;
import Magda.Compiler.*;

public class CIdentifierExpression implements IExpression{
    
    private static final long serialVersionUID = 1L;
  
    public String Name;

    public CIdentifierExpression(String aName){
        Name = aName;
    }

    public void print(java.io.PrintStream o){
        o.print("ID:"+Name);
    }

    public CType GetType (CInstrEnvironment env){
        CType res = env.findParamOrVariableType(Name);
        if (res == null) {
            throw new Error ("Identifier "+Name+" not declared ");
        } 
        else {
            // System.out.println("// CIdentifierExpression.GetType()-> " + res.toString());
        }
    
        return res;
    }

    public String GetTypeString (){
        // System.out.println("// CIdentifierExpression.Name=" + Name);
        // System.out.println("// CIdentifierExpression.GetTypeString()->empty");
        return ("Integer");   // as default arithmetci operations about Integers
    }

    public void GenCode (java.io.PrintStream o, CInstrEnvironment env, CGenCodeHelper h, int target){
        o.print(h.tempAcc(target)+"=");
        int i = env.getVariableOffset(Name);
        if (i>=0) {
            o.println("localVars["+ String.valueOf(i)+"];");
        }
        else {
            i = env.getParameterOffset(Name);
            if (i<0)
                throw new Error ("Identifier "+Name+" not declared ");
            o.println("params["+ String.valueOf(i)+"];");
        }
    }

};
