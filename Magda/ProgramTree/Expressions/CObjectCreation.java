package Magda.ProgramTree.Expressions;
import Magda.ProgramTree.*;
import Magda.ProgramTree.Declarations.*;
import Magda.ProgramTree.MixinExpressions.*;
import Magda.Compiler.*;

public class CObjectCreation implements IExpression
{

    CInitializationOfParams Init;
    IMixinExpression MixinExpr;

    public CObjectCreation( IMixinExpression aMixinExpr, CInitializationOfParams aInit)
    { MixinExpr = aMixinExpr;
      Init = aInit;
    }

    public CType GetType(CInstrEnvironment env)
    { Init.CheckTypes(env);
      //
      CType nativeType = MixinExpr.GetNativeType(env);
      CNewMethodDeclarations decls =  nativeType.calcAbstractMethods(env);
      if (decls.size() >0)
      throw new CTypeError("Cannot create object of type "+ nativeType +" with abstract methods, ie: "+decls.get(0).getMixinName() +"."+decls.get(0).getMethodName() );
      //
      nativeType.CheckIfBaseMixinsExist(env);
      //
      return MixinExpr.GetType(env);
    }

    public String GetTypeString ()
    {
      // System.out.println("// CObjectCreation.GetTypeString()->empty");
      return ("Integer");   // as default arithmetci operations about Integers
    }

    public void print(java.io.PrintStream o)
    { o.print("(new  ");
      MixinExpr.print(o);
      o.print("[");
      Init.print(o);
      o.print("])");
    }

   public void GenCode (java.io.PrintStream o, CInstrEnvironment env, CGenCodeHelper h, int target)
   {  o.print("{ //object creation for: "); MixinExpr.print(o); o.println("");
      o.println("CMagdaMixinSequence tempList= new CMagdaMixinSequence();");
      o.println(" tempList.add( CMagdaMixinSequence.globalList.getMixin(\"Object\"));");
      MixinExpr.GenCodeForMixinExpression (o, env,  h);
      o.println(h.tempAcc(target)+"= tempList.CreateObject();");
      o.println("}");
      String IniParamsSetter="";
      for (int i=0; i<Init.size(); i++)
      { int temp = h.getTemp();
        Init.get(i).Expr.GenCode(o, env, h, temp);
        IniParamsSetter += "IniParams.putParamValue(\""+Init.get(i).MixinName+"\",\""+Init.get(i).ParamName+"\","+h.tempAcc(temp)+");\n";
      }
      o.println("{CMagdaIniParams IniParams = new CMagdaIniParams();");
      o.print(IniParamsSetter);

      o.println("CMagdaIniModules modules = new CMagdaIniModules();");
      int modulecount=MixinExpr.GetType(env).GenCodeForActivatedModules(o, env, h, Init);
      if (modulecount>0)
      { o.println("CMagdaIniModule firstModule = modules.remove(0);");
        o.println("firstModule.Execute("+h.tempAcc(target)+",modules, IniParams);");
      }
      o.println("}");

      /*Init.GenCode(o,  env, h, target);*/

      o.println(h.tempAcc(target)+".executeMethodByName(\"Object\", "+String.valueOf (env.getMixin("Object").getMethodOffset("Init"))+", new CMagdaObject[0]);");

   }

};