package Magda.ProgramTree.Expressions;


public class CBinaryExpressionAdd extends CBinaryExpression
{
  public CBinaryExpressionAdd (IExpression aMethodTarget, IExpression param)
  {
    super( aMethodTarget, param.GetTypeString(), "add", param);
    // System.out.println("// CBinaryExpressionAdd: tar=" + aMethodTarget.GetTypeString() + " par=" + param.GetTypeString());
  }

};