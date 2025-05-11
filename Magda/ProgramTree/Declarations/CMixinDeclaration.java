package Magda.ProgramTree.Declarations;
import Magda.ProgramTree.*;
import Magda.ProgramTree.MixinExpressions.*;

import Magda.Compiler.*;

public class CMixinDeclaration implements IDeclaration, ITypeElement
{
  public String MixinName;
  public IMixinExpression BaseMixinExpression;
  public CPolymorphismParams polyPars;
  //
  public CMethodDeclarations OverridenMethods;
  public CNewMethodDeclarations NewMethods;
  public CIniModuleDeclarations IniModules;

  public CFieldDeclarations Flds;

  public CMixinDeclaration(String aMixinName,
                        IMixinExpression aBaseMixinExpression,
                        CFieldDeclarations aFlds,
                        CNewMethodDeclarations aNewMethods,
                        CMethodDeclarations aOverridenMethods,
                              CIniModuleDeclarations aIniModules,
                              CPolymorphismParams apolyPars)
  {
    MixinName = aMixinName;
    // System.out.println("// CMixinDeclaration: name=" + MixinName);
    BaseMixinExpression =  aBaseMixinExpression;
    OverridenMethods = aOverridenMethods;
    NewMethods = aNewMethods;
    IniModules = aIniModules;
    Flds = aFlds;
    polyPars = apolyPars;
    //
    polyPars.setMixin(this);
    NewMethods.setMixin(this);
    OverridenMethods.setMixin(this);
}

  public CPolyApplicationValues getApplications(CMethodEnvironment env)
  { return new CPolyApplicationValues();
  }

  public CNewMethodDeclarations calcAbstractMethods(CMethodEnvironment env, CNewMethodDeclarations prevAbstractMethods)
  { for (int i=0; i<OverridenMethods.size(); i++)
      prevAbstractMethods.remove(OverridenMethods.get(i).GetSourceMethod(env));
    //uzupelniamy o nowe abstrakcyjne
    for (int i=0; i<NewMethods.size(); i++)
      if (NewMethods.get(i) instanceof CAbstractMethodDeclaration)
        prevAbstractMethods.add( NewMethods.get(i) );
    //
    return prevAbstractMethods;
  }

  public CType GetNativeType  (CMethodEnvironment env)
  { return new CType(env, this);
  }

  public CType GetType (CMethodEnvironment env)
  { if (env.calculatedTypes.get(this) != null)
      return (CType) env.calculatedTypes.get(this); //sprawdzamy czy nie jest juz w trakcie liczenia -zeby unknac zapetlenia
    //
    CType res = GetNativeType(env);
    env.calculatedTypes.put(this, res);
    res.addNewAtStart(BaseMixinExpression.GetType(env) );
    return res;
  }

  public int getCountOfNewMethods()
  { return NewMethods.size();
  }

  public int getLayerSize()
  { return getCountOfNewMethods()+Flds.size();
  }

  public int getMethodOffset (String name)
  { int res =0;
    for (int i=0;  i<NewMethods.size(); i++)
  { if ( NewMethods.get(i).getMethodName().equals(name) )
      return res;
    res++;
  }
  throw new Error("Method not found! "+name);
  }

  public INewMethodDeclaration getNewMethod(String name)
  { for (int i=0;  i<NewMethods.size(); i++)
      if ( NewMethods.get(i).getMethodName().equals(name) )
      return NewMethods.get(i);
  throw new Error("Method not found! "+name);
  }

  public CType getMethodResultType(CEnvironment env, String name)
  { return getNewMethod(name).getResultType(new CMethodEnvironment(env, this));
  }

  public CType getFieldParamType(CEnvironment env, String name)
  { for (int i=0; i<Flds.size(); i++)
      if (Flds.get(i).VarName.equals(name))
        return Flds.get(i).GetType(new CMethodEnvironment(env, this) );
    //
    throw new Error("Field "+name+" Not Found in mixin "+MixinName);

  }

  public CType getIniParamType(CEnvironment env, String ParamName)
  {  for (int m=0; m<IniModules.size(); m++)
     { CSourceInitializationParameters params= IniModules.get(m).InputParams;
       for (int ip=0; ip< params.size(); ip++ )
         if (params.get(ip).ParName.equals(ParamName))
           return params.get(ip).GetType(new CMethodEnvironment(env, this) );
     }
     throw new Error("InitParam "+ParamName+" not found in mixin "+MixinName);
  }


  public int getFieldParamOffset(String name)
  { int res = getCountOfNewMethods();
    for (int i=0; i<Flds.size(); i++)
    { if (Flds.get(i).VarName.equals(name))
  return res;
      res++;
    }
    //

    throw new Error("Field "+name+" Not Found");
  }

   public void print(java.io.PrintStream o)
  { o.print("Mixin declaration "+MixinName + " of ");
    BaseMixinExpression.print(o);
    o.println();
    Flds.print(o);
    NewMethods.print(o);
    OverridenMethods.print(o);
    IniModules.print(o);
    o.println();
    o.println("end;");
   }

   public boolean ModuleContainsInputParameter (CSourceInitializationParameter par)
   { return ModuleContainsInputParameter(par, IniModules.size());
   }

   public boolean ModuleContainsInputParameter (CSourceInitializationParameter par, int ModuleNumber)
   { for (int i=0; i<ModuleNumber; i++)
       if (IniModules.get(i).InputParams.containsParam(par.MixinName, par.ParName))
         return true;
     return false;

   }


   public void CheckTypes(CEnvironment env)
  { for (int i=0; i<Flds.size(); i++)
      Flds.get(i).CheckTypes(new CMethodEnvironment(env.Decls, this));
    for (int i=0; i<OverridenMethods.size(); i++)
      OverridenMethods.get(i).CheckTypes(new CMethodEnvironment(env.Decls, this));
    for (int i=0; i<NewMethods.size(); i++)
      NewMethods.get(i).CheckTypes(new CMethodEnvironment(env.Decls, this));
    for (int i=0; i<IniModules.size(); i++)
      IniModules.get(i).CheckTypes(new CIniModuleEnvironment(env.Decls, this, i));
    BaseMixinExpression.GetType(new CMethodEnvironment(env, this) );

  }

public void GenCode (java.io.PrintStream o, CEnvironment env, CGenCodeHelper h)
{ o.println();
  o.println(" /* ------- beginning of mixin ["+MixinName+"] --------- */");
  o.println("CMagdaMixinSequence.globalList.add( curMixin=new CMagdaMixin (\""+MixinName+"\") {");
  o.println(" public int getObjectLayerSize() ");
  o.println(" { return "+String.valueOf(getLayerSize())+"; };");
  o.println("public void setMethods(CMagdaMixinSequence AllSequence, IMagdaObjectElement[] aObjectBody) { ");

  CMethodEnvironment env2 =  new CMethodEnvironment(env.Decls, this);
  for (int i=0; i<OverridenMethods.size(); i++)
    OverridenMethods.get(i).GenCode(o, env2, h);
  for (int i=0; i<NewMethods.size(); i++)
    NewMethods.get(i).GenCode(o, env2, h);
  for (int i=0; i<Flds.size(); i++)
    Flds.get(i).GenCode(o, env2, h);
  o.println( " } // end of setMethods");
  o.println("} ); ");
  o.println("curMixin.IniModules = new CMagdaIniModule["+ IniModules.size() +"];");
  for (int i=0; i< IniModules.size(); i++)
  { o.println("curMixin.IniModules["+i+"] = ");
  IniModules.get(i).GenCode(o, env2, h);
  }
  o.println("//end  of new Mixin ["+MixinName+"] ");
}


  public String getCaption()
  { return MixinName;
  }
  public String getName()
  { return MixinName;
  }

  public String CodeForMixin()
  { return "CMagdaMixinSequence.globalList.getMixin(\""+MixinName+"\")";
  }

  public void GenCodeForMixinExpression (java.io.PrintStream o, CEnvironment env, CGenCodeHelper h)
  { o.println("tempList.add ("+CodeForMixin()+");");
  }



}