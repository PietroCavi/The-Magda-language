package Magda.ProgramTree.Instructions;
import Magda.ProgramTree.*;
import Magda.Compiler.*;
import Magda.ProgramTree.Expressions.*;

public class CWhileLoopInstruction extends CInstruction{  
    
    private static final long serialVersionUID = 1L;
    
    IExpression expr;
    CInstructions instrs;
   
    public CWhileLoopInstruction( IExpression aexpr, CInstructions ainstrs){ 
        instrs = ainstrs;
        expr = aexpr;
    }

	public void CheckTypes (CInstrEnvironment env) throws CTypeError{ 
        super.CheckTypes(env);
	    if (! expr.GetType(env).isSubTypeOf(env, CType.createCType(env, env.getMixin("Boolean"))) )
		    throw new CTypeError ("While clause has to have a type of Boolean");
	    instrs.CheckTypes(env);
	}

    public void print(java.io.PrintStream o){ 
        o.print("while (");
	    expr.print(o);
	    o.println(")");
	    instrs.print(o);
	    o.println ("end;");
	}


	public void GenCode (java.io.PrintStream o, CInstrEnvironment env, CGenCodeHelper h){ 
        super.GenCode(o, env, h); 
	    int t = h.getTemp();
	    expr.GenCode(o,env, h, t);
        o.println(" while ( (Boolean) "+h.tempAcc(t)+".internalPointer) {");
        instrs.GenCode(o, env, h);
	    expr.GenCode(o,env, h, t);
        o.println ("}");
	}


};
