package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
    def pascal(c: Int, r: Int): Int = {
      if (r == 0 || c == 0 || c == r) 1
      else
        pascal(c-1, r-1) + pascal(c, r-1)
    }
  
  /**
   * Exercise 2
   */
    def balance(chars: List[Char]): Boolean = {

      balanceHelper(chars, 0, 0)
    }
    //Open records how many remaining left parenthesis are unpaired.
    def balanceHelper(chars: List[Char], it: Int, open: Int): Boolean = {
      if (open < 0) return false;
      if (it == chars.length) return open == 0
      else {
        if (chars(it) =='(') {
          balanceHelper(chars, it + 1, open + 1)
        }
        else if (chars(it) == ')'){
          balanceHelper(chars, it + 1, open - 1)
        }
        else {
          balanceHelper(chars, it + 1, open)
        }
      }
    }
  /**
   * Exercise 3
   */
    def countChange(money: Int, coins: List[Int]): Int = {
      changeHelper(money, coins, 0)
    }

    def changeHelper(money: Int, coins: List[Int], index: Int): Int = {
      if(money == 0) 1
      else if (money < 0 || coins.length == index) 0
      else {
        //make_change_with_first_coin + make_change_without_first_coin
        return changeHelper(money - coins(index), coins, index) + changeHelper(money, coins, index + 1)
      }
    }
  }
