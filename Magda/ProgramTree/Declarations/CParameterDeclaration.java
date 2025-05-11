package Magda.ProgramTree.Declarations;
import Magda.ProgramTree.*;
import Magda.Compiler.*;
import Magda.ProgramTree.MixinExpressions.*;


public class CParameterDeclaration implements IProgramElem
{   String ParName;
    IMixinExpression Type;

    public CParameterDeclaration(String aParName, IMixinExpression aType)
    { ParName = aParName;
      Type = aType;
    }

    public void print(java.io.PrintStream o)
    { o.print(" param "+ParName+":");
      Type.print(o);
      o.println(";");
    }

    public CType GetType(CMethodEnvironment env)
    { return Type.GetType(env);
    }

 	public void GenCode (java.io.PrintStream o, CMethodEnvironment env, CGenCodeHelper h)
	{ o.println("{ int offset = AllSequence.getMixinOffsetByName(\"" + env.CurrentMixin.MixinName + "\")+"+String.valueOf(env.CurrentMixin.getFieldParamOffset(ParName)) +";");
      o.println(" aObjectBody[offset] = new CMagdaProperty ();}");
	}


	public String ExpandVariableInNative(String input, int i)
	{ 
		return input.replaceAll("\\$"+ParName+"\\$", "params["+String.valueOf(i)+"]" );
	}

};