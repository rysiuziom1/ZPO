object Password {
  def validate(password: String)(func: Array[String => Boolean]): Boolean = {
    for( f <- func) {
      if(!f(password))
        return false
    }
    true
  }

  def min_len(len: Int)(password: String): Boolean = {
    if(password.length < len)
      return false
    true
  }

  def max_len(len: Int)(password: String): Boolean = {
    if(password.length > len)
      return false
    true
  }

  def at_least_one_capital(password: String): Boolean = {
    if(password.count(_.isUpper) > 0)
      return true
    false
  }

  def at_least_one_lower_case(password: String): Boolean = {
    if(password.count(_.isLower) > 0)
      return true
    false
  }

  def at_least_one_digit(password: String): Boolean = {
    if(password.count(_.isDigit) > 0)
      return true
    false
  }

  def at_least_two_digits(password: String): Boolean = {
    if(password.count(_.isDigit) > 1)
      return true
    false
  }
}
