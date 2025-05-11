package Magda.ProgramTree.Expressions;
import Magda.ProgramTree.*;
import Magda.ProgramTree.Declarations.*;
import Magda.Compiler.*;

import java.util.*;

public class CMethodCallExpression implements IExpression{ 
    
    private static final long serialVersionUID = 1L;
    
    public IExpression MethodTarget;
    String MixinName;
    String MethodName;
    CExpressionList Params;

    public CMethodCallExpression (IExpression aMethodTarget, String aMixinName, String aMethodName, CExpressionList aparams){ 
        MethodTarget = aMethodTarget;
        MixinName = aMixinName;
        MethodName = aMethodName;
        Params  = aparams;
    }

    public void print(java.io.PrintStream o){ 
        o.print("(");
        MethodTarget.print(o);
        o.print(")");
        o.print(".call "+MixinName+"."+MethodName+"(");
        Params.print(o);
        o.print(")");
    }

    public CType GetType (CInstrEnvironment env){ 
        CMixinDeclaration targetMix= env.getMixin(MixinName);

        //
        CType targetType = MethodTarget.GetType(env);
        if (!targetType.isSubTypeOf( env, CType.createCType(env, targetMix) ) )
            throw new CTypeError ("Target object type "+targetType+" doesn't contain "+MixinName+" layer ");
        //
        targetMix.getNewMethod(MethodName).getFormalParameters().CheckTypesOfActualParameters(targetType, env, Params);
        //
        return env.getMixin(MixinName).getMethodResultType(env, MethodName).setPolyParamsFrom(targetType.getApplications(env)  ) ;
    }

    public String GetTypeString (){
        // System.out.println("// CMethodCallExpression.GetTypeString()->empty");
        return ("Integer");   // as default arithmetci operations about Integers
    }

    public void GenCode (java.io.PrintStream o, CInstrEnvironment env, CGenCodeHelper h, int target){  
        o.println("   // preparing call to method "+MixinName+"."+MethodName);
        int Target2 = h.getTemp();
        int MethodOffset = env.getMixin(MixinName).getMethodOffset(MethodName);
        MethodTarget.GenCode(o, env, h, Target2);

        Params.GenCodeForParams(o, env, h);
        o.println(h.tempAcc(target)+"="+h.tempAcc(Target2)+".executeMethodByName (\""+ MixinName +"\", " + String.valueOf(MethodOffset) + ", ParamsToPass);}  // call to method "+MixinName+"."+MethodName+" finished ");
    }

};
