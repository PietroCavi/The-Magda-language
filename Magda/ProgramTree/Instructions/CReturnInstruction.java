package Magda.ProgramTree.Instructions;
import Magda.ProgramTree.*;
import Magda.Compiler.*;
import Magda.ProgramTree.Expressions.*;


public class CReturnInstruction extends CInstruction
{
	IExpression Expr;

	public CReturnInstruction(IExpression aExpr)
	{ Expr = aExpr;
	}

	public void CheckTypes (CInstrEnvironment env) throws CTypeError
	{  super.CheckTypes(env);
	   if (env.currentMethod == null)
 		 throw new CTypeError ("return not allowed in this context");
	   CType RetType = env.currentMethod.getResultType(env);
		 
	   if (!Expr.GetType(env).isSubTypeOf( env, RetType ))
 		 throw new CTypeError ("Wrong types in assignment");
	}

	public void print(java.io.PrintStream o)
	{ o.print ("return ");
	  Expr.print(o);
	  o.println(";");
	}

	public void GenCode (java.io.PrintStream o, CInstrEnvironment env, CGenCodeHelper h)
	{ super.GenCode(o, env, h); 
	  int temp= h.getTemp();
	  Expr.GenCode(o,env,h, temp);
	  o.println("if (1==1) return "+ h.tempAcc(temp)+";");
	}

}
