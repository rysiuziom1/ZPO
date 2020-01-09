import org.scalatest.funsuite.AnyFunSuite

class MainTest extends AnyFunSuite {
  test("Test1") {
    assert(!Main.checkBalancedBrackets("))(("))
    assert(Main.checkBalancedBrackets("(()(ab)c)"))
    assert(!Main.checkBalancedBrackets("((()())"))
    assert(!Main.checkBalancedBrackets("(()))("))
    assert(Main.checkBalancedBrackets("(aa(b)()c)d"))
  }

}
