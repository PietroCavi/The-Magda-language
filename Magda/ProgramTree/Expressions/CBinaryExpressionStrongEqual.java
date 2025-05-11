package Magda.ProgramTree.Expressions;
import Magda.Compiler.*;
import Magda.ProgramTree.MixinExpressions.*;


public class CBinaryExpressionStrongEqual implements IExpression
{
  IExpression Left, Right;

  public CBinaryExpressionStrongEqual (IExpression aLeft, IExpression aRight)
  {
    Left = aLeft;
    Right = aRight;
  }

  public void print(java.io.PrintStream o)
  {
    o.print("(");
    Left.print(o);
    o.print(" == ");
    Right.print(o);
    o.print(")");
  }

  public CType GetType (CInstrEnvironment env)
  {
    Left.GetType(env);
    Right.GetType(env);
    return new CType (env, env.getMixin("Boolean") );
  }

  public String GetTypeString ()
  {
    // System.out.println("// CBinaryExpressionStrongEqual.GetTypeString()->empty");
    return ("Integer");   // as default arithmetci operations about Integers
  }

  public void GenCode (java.io.PrintStream o, CInstrEnvironment env, CGenCodeHelper h, int target)
  {
    int LeftT = h.getTemp();
    int RightT = h.getTemp();
    Left.GenCode(o, env, h, LeftT);
    Right.GenCode(o, env, h, RightT);
    new CObjectCreation(new CMixinExpressionIdentifier("Boolean"), new CInitializationOfParams()).GenCode(o, env, h, target);
    o.println ( h.tempAcc(target)+ ".internalPointer = ("+h.tempAcc(LeftT)+"="+ h.tempAcc(RightT)+");");
  }

};