package Magda.PreParser.PreParserStructures;

import java.util.HashMap;
import java.util.ArrayList;

public class MixinContext{
    private HashMap<String,MethodContext> methodsContext = new HashMap<String,MethodContext>();
    private HashMap<String,String> fields = new HashMap<String,String>();

    private ArrayList<String> linkedMixins = new ArrayList<String>();

    public void addMethod(String name, MethodContext methodContext){
        methodsContext.put(name,methodContext);
    } 

    public boolean containsMethod(String methodName){
        if(methodsContext.containsKey(methodName))
            return true;
        else
            return false;
    }
    
    public boolean containsField(String fieldName){
        if(fields.containsKey(fieldName))
            return true;
        else
            return false;
    }

    public void addField(String name, String type){
        fields.put(name,type);
    }
   
    public void addLinkedMixin(String name){
        linkedMixins.add(name);
    }

    public String getFieldType(String fieldName){
        return fields.get(fieldName);
    } 

    public String getMethodReturnType(String methodName){
        return methodsContext.get(methodName).getReturnType();
    }

    public String getMethodVariableType(String methodName, String variableName){
        return methodsContext.get(methodName).getVariableType(variableName);
    }

    public ArrayList<String> getLinkedMixins(){
        return new ArrayList<String>(linkedMixins);
    } 
   
    public String toString(){

        ArrayList<String> mckeys = new ArrayList<String>(methodsContext.keySet());
        ArrayList<String> fkeys = new ArrayList<String>(fields.keySet());

        String s = "";
        s+="  "+linkedMixins.toString()+"\n";

        s+="\tfields:\n";
        for(int i=0;i<fkeys.size();i++){
            s+="\t\t" + fkeys.get(i) + " : " + fields.get(fkeys.get(i)) + "\n";
        }
        
        s+="\tmethods:\n";
        for(int i=0;i<mckeys.size();i++){
            MethodContext tmp = methodsContext.get(mckeys.get(i));
            s+="\t\t" + mckeys.get(i) + " : " + tmp.getReturnType()+"\n";
            s+=tmp.toString();
        }

        return s;
    }
}

