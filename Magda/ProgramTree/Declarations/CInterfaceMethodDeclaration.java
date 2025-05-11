package Magda.ProgramTree.Declarations;
import Magda.ProgramTree.*;
import Magda.ProgramTree.MixinExpressions.*;
import Magda.Compiler.*;
import org.omg.IOP.ENCODING_CDR_ENCAPS;


public abstract class CInterfaceMethodDeclaration implements INewMethodDeclaration
{

	String MethodName;
	IMixinExpression ResType;
	CParameterDeclarations Pars;
	int PosInCode;
	String ProgramFile;
	CMixinDeclaration ContainerMixin;

       public CInterfaceMethodDeclaration (IMixinExpression aResType, String aMethodName, CParameterDeclarations aPars, int aPosInCode, String aProgramFile)
       { ResType = aResType;
	 MethodName= aMethodName;
	 Pars = aPars;
	 PosInCode = aPosInCode;
	 ProgramFile = aProgramFile;
	 aPars.setMethod(this);
       }

       public CParameterDeclarations getFormalParameters()
       { return Pars;
       }

       public void CheckTypes(CMethodEnvironment env) throws CTypeError
       { CTypeError.LineNo = PosInCode;
         CTypeError.ProgramFile = ProgramFile;
         ResType.GetType(env);
         Pars.CheckTypes(env);
         for (int i=0; i<env.CurrentMixin.NewMethods.size(); i++)
            if ( (env.CurrentMixin.NewMethods.get(i) != this) &&
                 (env.CurrentMixin.NewMethods.get(i).getMethodName().equals(MethodName))
               )
            throw new CTypeError("Method named "+env.CurrentMixin.MixinName+"."+MethodName+" redeclared!");
       }


       public String getMethodName()
       { return MethodName;
       }

       public CType getResultType(CMethodEnvironment env)
       { return ResType.GetType(env);
       }

	public void setMixin (CMixinDeclaration container)
	{ ContainerMixin = container;
	}

	public CMixinDeclaration getMixin()
	{ return ContainerMixin;
	}

    public String getMixinName()
	{ return ContainerMixin.MixinName;
	}


}
