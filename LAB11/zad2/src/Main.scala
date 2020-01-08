import scala.util.matching.Regex

object Main {
  def main(args: Array[String]): Unit = {
    print("Hello World!")
    val numberPattern: Regex = "-*[0-9]( [+\\Q-\\E] [0-9])*".r
  }
}
