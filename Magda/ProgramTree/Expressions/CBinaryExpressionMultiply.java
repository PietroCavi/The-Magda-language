package Magda.ProgramTree.Expressions;


public class CBinaryExpressionMultiply extends CBinaryExpression
{
  public CBinaryExpressionMultiply (IExpression aMethodTarget, IExpression param)
  {
    super( aMethodTarget, param.GetTypeString(), "multiply", param);
  }

};