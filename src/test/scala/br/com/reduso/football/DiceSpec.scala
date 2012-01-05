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
class DiceSpec extends WordSpec with GivenWhenThen {

  "A dice" should {

    "roll a random number between 1 and 6 when the dice has 6 faces" in {

      given("a six faced dice")
      val dice = SixDice()

      when("the dice is rolled 100 times")
      val results = for (i <- (1 to 100).toList) yield dice.roll

      then("no number returned should be greater than 6 nor lesser that 1")
      assert (!results.exists(result => result < 1 || result > 6))
    }

    "roll a number between 1 and 12 when the dice has 12 faces" in {

      given("a twelve faced dice")
      val dice = TwelveDice()

      when("the dice is rolled 100 times")
      val results = for (i <- (1 to 100).toList) yield dice.roll

      then("no number returned should be greater than 12 nor lesses than 1")
      assert(!results.exists(result => result < 1 || result > 12))
    }

    "roll a number between 1 and 25 when the dice has 25 faces" in {

      given("a 25 faced dice")
      val dice = Dice(faces = 25)

      when("the dice is rolled 100 times")
      val results = for (i <- (1 to 100).toList) yield dice.roll

      then("no number returned should be greater than 25 nor lesses than 1")
      assert(!results.exists(result => result < 1 || result > 25))

    }

    "not roll always the same number" in {

      given("a dice")
      val dice = Dice()

      when("the dice is rolled 100 times")
      val results = for (i <- (1 to 100).toList) yield dice.roll

      then("the numbers returned should not be all equal")
      assert(results.distinct.size > 1)

    }

  }
}