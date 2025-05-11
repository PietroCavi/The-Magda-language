package Magda.ProgramTree.Expressions;
import Magda.Compiler.*;
import Magda.ProgramTree.*;

public class CThisExpression implements IExpression
{
    public void print(java.io.PrintStream o)
  { o.print("this");
  }

   public CType GetType (CInstrEnvironment env)
   { if (env.CurrentMixin == null)
     throw new Error("this not allowed in this context");
     return env.CurrentMixin.GetType(env);
   }

   public String GetTypeString ()
   {
     // System.out.println("// CThisExpression.GetTypeString()->empty");
     return ("Integer");   // as default arithmetci operations about Integers
   }

   public void GenCode (java.io.PrintStream o, CInstrEnvironment env, CGenCodeHelper h, int target)
   {  o.println(h.tempAcc(target)+"= aSelf;");
   }

};