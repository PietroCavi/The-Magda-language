package Magda.ProgramTree.Expressions;
import Magda.ProgramTree.*;
import Magda.Compiler.*;
import java.util.*;

public class CFieldSelectExpression implements IExpression
{
  public IExpression SelectTarget;
  String MixinName;
  String FieldName;

  public CFieldSelectExpression (IExpression aSelectTarget, String aMixinName, String aFieldName)
  {
    SelectTarget = aSelectTarget;
    MixinName = aMixinName;
    FieldName = aFieldName;
  }

  public CType GetType (CInstrEnvironment env)
  {
    return env.getMixin(MixinName).getFieldParamType(env, FieldName);
  }

  public String GetTypeString ()
  {
    // System.out.println("// CFieldSelectExpression.GetTypeString()->empty");
    return ("Integer");   // as default arithmetci operations about Integers
  }

  public void print(java.io.PrintStream o)
  {
    o.print("(");
    SelectTarget.print(o);
    o.print(")");
    o.print(".sel "+MixinName+"."+FieldName);
  }

  public void GenCode (java.io.PrintStream o, CInstrEnvironment env, CGenCodeHelper h, int target)
  {
    int Targetplace = h.getTemp();
    SelectTarget.GenCode(o, env, h, Targetplace);
    o.println(  h.tempAcc(target)+"="+h.tempAcc(Targetplace)+".getStateHolderByName(\""+MixinName+"\","+String.valueOf (env.getMixin(MixinName).getFieldParamOffset(FieldName))+").Value;");
  }

};