package funsets

import org.scalatest.FunSuite


import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  // test("string take") {
  //   val message = "hello, world"
  //   assert(message.take(5) == "hello")
  // }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  // test("adding ints") {
  //   assert(1 + 2 === 3)
  // }


  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)

    val s4 = Set(1,2,3,4)
    val s5 = Set(3,4,5,6,7)
    val s6 = Set(5,6,7)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements of each set") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
      println("Union:")
      printSet(s)
    }
  }

  test ("intersects contains elements existing in both sets") {
    new TestSets {
      val t1 = intersect(s4, s5)
      val t2 = intersect(s5, s6)
      assert(contains(t1, 3), "Intersects 3")
      assert(contains(t1, 4), "Intersects 4")
      assert(contains(t2, 5), "Intersects 5")
      assert(contains(t2, 6), "Intersects 6")
      assert(contains(t2, 7), "Intersects 7")

      println("Intersect: ")
      printSet(t1)
      printSet(t2)
    }
  }

  test ("diff contains elements in s not in t") {
    new TestSets {
      val t = diff(s4, s5)
      assert(contains(t, 1), "Diff 1")
      assert(contains(t, 2), "Diff 2")
      println("Diff: ")
      printSet(t)
    }
  }

  test ("filter contains elements satisfy p") {
    new TestSets {
      val t = filter(s5, {elem: Int => elem >= 3 && elem < 7})
      assert(contains(t, 6), "Filter 6")
      println("Filter: ")
      printSet(t)
    }
  }

  test ("forall finds all elements satisfy p") {
    new TestSets {
      val t = forall(s5, {elem: Int => elem >= 3 && elem <= 7})
      println("for all: ")
      printSet(s5)
      assert(t)
    }
  }

  test ("exist finds ") {
    new TestSets {
      println("Exists")
      println(s5)
      assert(exists(s5, {elem: Int => elem % 5 == 0}))
    }
  }

  test ("map translates") {
    new TestSets {
      val t = map(s6, {elem: Int=> elem * 2})
      assert(contains(t, 10))
      assert(contains(t, 12))
      assert(contains(t, 14))
      println("map: ")
      printSet(t)
    }
  }

}
