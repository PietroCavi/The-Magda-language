package Magda.mtj;
public abstract class CMagdaMixin{
	String name;
    
    public CMagdaMixin (String aname){ 
        name = aname; 
    }
        
    public abstract void setMethods(CMagdaMixinSequence AllSequence, IMagdaObjectElement[] aObjectBody);
	
    public abstract  int getObjectLayerSize();
	
    public CMagdaIniModule[] IniModules;
};
