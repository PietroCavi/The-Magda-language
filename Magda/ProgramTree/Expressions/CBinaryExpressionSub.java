package Magda.ProgramTree.Expressions;


public class CBinaryExpressionSub extends CBinaryExpression
{
  public CBinaryExpressionSub (IExpression aMethodTarget, IExpression param)
  {
    super( aMethodTarget, param.GetTypeString(), "sub", param);
  }

};