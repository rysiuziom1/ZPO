import org.scalatest.funsuite.AnyFunSuite

class PasswordTest extends AnyFunSuite {
  test("Min 2, max 8, 1 digit, upper case") {
    val tmp = Array(Password.min_len(2) _, Password.max_len(8) _,
      Password.at_least_one_digit _, Password.at_least_one_capital _)
    assert(Password.validate("Pass2")(tmp))
  }

  test("Min 2, max 8, 1 digit, upper case - wrong") {
    val tmp = Array(Password.min_len(2) _, Password.max_len(8) _,
      Password.at_least_one_digit _, Password.at_least_one_capital _)
    assert(!Password.validate("pass2")(tmp))
  }

  test("Min 5, max 8, 2 digits, upper case, lower case") {
    val tmp = Array(Password.min_len(5) _, Password.max_len(8) _,
      Password.at_least_two_digits _, Password.at_least_one_capital _, Password.at_least_one_lower_case _)
    assert(Password.validate("P4ssw0rd")(tmp))
  }
}
