package br.com.reduso.football

import org.scalatest.{GivenWhenThen, WordSpec}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * Copyright 2011 Renato Duarte Soffiatto
 *
 * This file is part of Scala Football.
 * Scala Football is free software: you can redistribute it and/or modify it under the terms of the 
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the 
 * License, or (at your option) any later version.
 *
 * Scala Football is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with Scala Football. If not, 
 * see http://www.gnu.org/licenses/.
 *  
 */
@RunWith(classOf[JUnitRunner])
class AttributeTestSpec  extends WordSpec with GivenWhenThen {

  "A AttributeTest" should {

    "Be a auto-success when the dice rolls its max value" in {
      given("A attribute test")
      val attributeTest = AttributeTest(new TestDices.SuccessDice(6))

      when("the dice roll results in its max value against a small attribute")
      val result = attributeTest.test(2)

      then("the result should be a success")
      assert(result === AttributeTest.AutoSuccess)
    }

    "Be a auto-failure when the dice rolls its min value" in {
      given("A attribute test")
      val attributeTest = AttributeTest(new TestDices.FailureDice(6))

      when("the dice roll results in its min value against a big attribute")
      val result = attributeTest.test(5)

      then("the result should be a failure")
      assert(result === AttributeTest.AutoFailure)
    }

    "Be successful when the attribute is bigger than the number rolled" in {
      given("A attribute test")
      val attributeTest = AttributeTest(new TestDices.ArbitraryDice(faces=6, results=2))

      when("the dice roll result is smaller than the attribute")
      val result = attributeTest.test(3)

      then("the result should be a success")
      assert(result === AttributeTest.Success)
    }

    "Be successful when the attribute is equal than the number rolled" in {
      given("A attribute test")
      val attributeTest = AttributeTest(new TestDices.ArbitraryDice(faces=6, results=3))

      when("the dice roll result is equal than the attribute")
      val result = attributeTest.test(3)

      then("the result should be a success")
      assert(result === AttributeTest.Success)
    }

    "Be a failure when the attribute is smaller than the number rolled" in {
      given("A attribute test")
      val attributeTest = AttributeTest(new TestDices.ArbitraryDice(faces=6, results=5))

      when("the dice roll result is bigger than the attribute")
      val result = attributeTest.test(2)

      then("the result should be a failure")
      assert(result === AttributeTest.Failure)
    }

  }

}