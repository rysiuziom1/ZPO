object Main {
  def checkBalancedBrackets(expression: String): Boolean = {
    val exp1 = expression.foldLeft(""){(n1, n2) => n1 + (n2 match {
      case '(' => '('
      case ')' => ')'
      case _ => ""
    })}

    val tmp = expression.foldLeft(0){(n1, n2) => {
      if(n1 >= 0) {
        n1 + (n2 match {
          case '(' => 1
          case ')' => -1
          case _ => 0
        })
      }
      else
        return false
    }}
    if(tmp != 0)
      return false
    if(exp1.startsWith("(") && exp1.endsWith(")") && tmp == 0)
      return true
    false
  }

  def main(args:Array[String]): Unit = {
    val expression = "())(()"
    val xd = checkBalancedBrackets(expression)
    print(xd)
  }
}
