package Magda.ProgramTree.Expressions;


public class CBinaryExpressionDivide extends CBinaryExpression
{
  public CBinaryExpressionDivide (IExpression aMethodTarget, IExpression param)
  {
    super( aMethodTarget, param.GetTypeString(), "divide", param);
  }

};