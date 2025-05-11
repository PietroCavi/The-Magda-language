package Magda.ProgramTree.Expressions;
import Magda.ProgramTree.*;
import Magda.Compiler.*;

public class CNullExpression implements IExpression{
    
    private static final long serialVersionUID = 1L;
    
    public void print(java.io.PrintStream o){ 
        o.print("null");
    }

    public CType GetType (Magda.Compiler.CInstrEnvironment env){ 
        return CType.createCType(true);
    }

    public String GetTypeString (){
        // System.out.println("// CNullExpression.GetTypeString()->empty");
        return ("Integer");   // as default arithmetci operations about Integers
    }

    public void GenCode (java.io.PrintStream o, CInstrEnvironment env, CGenCodeHelper h, int target){  
        o.print(h.tempAcc(target)+"=null;");
    }

};
