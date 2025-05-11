package Magda.mtj;

import java.util.*;

public abstract  class CMagdaIniModule implements IMagdaObjectElement
{
	public abstract void Execute(CMagdaObject aSelf, CMagdaIniModules ModulesToExecute, CMagdaIniParams IniParams);
};
