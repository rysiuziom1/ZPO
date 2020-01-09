object Main {
  def main(args: Array[String]): Unit = {
    val expression = "3 - 2 + 3 + 1"

    def evaluate(expression: List[String]): Int = expression match {
      case left :: "+" :: right :: rest => evaluate((left.toInt + right.toInt).toString :: rest)
      case left :: "-" :: right :: rest => evaluate((left.toInt - right.toInt).toString :: rest)
      case value :: Nil => value.toInt
    }

    val expressionElements = expression.split("\\s").toList

    try {
      val result = evaluate(expressionElements)

      println(s"$expression = $result")
    } catch {
      case ex: NumberFormatException => {
        println("Invalid expression")
      }
    }
  }
}
