package Magda.ProgramTree.Expressions;
import Magda.ProgramTree.*;
import Magda.Compiler.*;
import Magda.ProgramTree.MixinExpressions.*;

public class CFloatLiteral implements IExpression
{
  float  Value;

  public CFloatLiteral (float aValue)
  {
    Value = aValue;
  }

  public CType GetType (CInstrEnvironment env)
  {
    return (new CMixinExpressionIdentifier("FullFloat")).GetType(env) ;
  }

  public String GetTypeString ()
  {
    return ("Float");
  }

  public void print(java.io.PrintStream o)
  {
    o.print(" float:<"+String.valueOf(Value)+">");
  }

   public void GenCode (java.io.PrintStream o, CInstrEnvironment env, CGenCodeHelper h, int target)
   {   new CObjectCreation(new CMixinExpressionIdentifier("FullFloat"), new CInitializationOfParams()).GenCode(o, env, h, target);
     o.println ( h.tempAcc(target)+ ".internalPointer = new Float("+Value+");");
   }

};