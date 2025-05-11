package Magda.ProgramTree.Instructions;
import Magda.ProgramTree.*;
import Magda.Compiler.*;
import Magda.ProgramTree.LValues.*;
import Magda.ProgramTree.Expressions.*;

public class CAssignment extends CInstruction{
    
    private static final long serialVersionUID = 1L;

	ILValue LVal;
	IExpression Expr;

	public CAssignment(ILValue aLVal, IExpression aExpr){ 
        LVal = aLVal;
	    Expr = aExpr;
	}

	public void CheckTypes (CInstrEnvironment env){ 
        super.CheckTypes(env);
	    CType type1= Expr.GetType(env);
	    CType type2= LVal.GetType(env);
	    if (!type1.isSubTypeOf( env, type2))
 		    throw new CTypeError ("Cannot assign "+ type1+ " to "+type2 );
	}


    public void print(java.io.PrintStream o){ 
        LVal.print(o);
        o.print(":=");
	    Expr.print(o);
	    o.println(";");
	}

	public void GenCode (java.io.PrintStream o, CInstrEnvironment env, CGenCodeHelper h){ 
        super.GenCode(o, env, h); 
	    int t = h.getTemp();
	    Expr.GenCode(o, env, h, t);
        LVal.GenCode(o, env, h);
	    o.println(" = " +h.tempAcc(t)+";" );
	}
};
