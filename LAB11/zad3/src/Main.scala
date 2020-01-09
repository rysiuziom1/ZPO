object Main {
  def checkBalancedBrackets(expression: String): Boolean = {
    val asd = expression.foldLeft(""){(n1, n2) => n1 + (n2 match {
      case '(' => '('
      case ')' => ')'
      case _ => ""
    })}

    val tmp = expression.foldLeft(0){(n1, n2) => n1 + (n2 match {
      case '(' => 1
      case ')' => -1
      case _ => 0
    })}
    if(tmp != 0)
      return false
    if(asd.startsWith("(") && asd.endsWith(")") && tmp == 0)
      return true
    false
  }

  def main(args:Array[String]): Unit = {
    val expression = "(aa(b)()c)d"
    val xd = checkBalancedBrackets(expression)
    print(xd)
  }
}
