object Main {

  @scala.annotation.tailrec
  def evaluate(expression: List[String]): Int = expression match {
    case left :: "+" :: right :: rest => evaluate((left.toInt + right.toInt).toString :: rest)
    case left :: "-" :: right :: rest => evaluate((left.toInt - right.toInt).toString :: rest)
    case value :: Nil => value.toInt
  }

  def main(args: Array[String]): Unit = {

    val expression = "-3 - 2 + 3 + 1"

    val expressionElements = expression.split("\\s").toList

    try {
      val result = evaluate(expressionElements)

      println(s"$expression = $result")
    } catch {
      case ex: NumberFormatException => println("Invalid expression")
    }
  }
}
