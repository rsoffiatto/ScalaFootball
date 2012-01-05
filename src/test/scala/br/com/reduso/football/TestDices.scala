package br.com.reduso.football

import java.util.NoSuchElementException
import org.scalatest.FunSuite
import br.com.reduso.football.TestDices.SeqDice
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

object TestDices {

  class SuccessDice(faces: Int) extends Dice(faces) {
    override def roll = {
      this.faces
    }
  }

  class FailureDice(faces: Int) extends Dice(faces) {
    override def roll = {
      1
    }
  }

  class ArbitraryDice(faces: Int, results: Int) extends Dice(faces) {
    require (results <= faces)
    require (results > 0)

    override def roll = {
      results
    }
  }
  
  class SeqDice(faces: Int,  results: Seq[Int]) extends Dice(faces) {

    private val resultsIterator = results.toIterator

    override def roll = {
      resultsIterator.next()
    }

  }

}

@RunWith(classOf[JUnitRunner])
class TestDicesTest extends FunSuite {

  test("seq dice") {
    val dice = new SeqDice(6, Seq(1, 2, 3, 4, 5, 6))
    assert(dice.roll === 1)
    assert(dice.roll === 2)
    assert(dice.roll === 3)
    assert(dice.roll === 4)
    assert(dice.roll === 5)
    assert(dice.roll === 6)
  }

    test("seq dice desc") {
      val dice = new SeqDice(6, Seq(6, 5, 4, 3, 2, 1))
      assert(dice.roll === 6)
      assert(dice.roll === 5)
      assert(dice.roll === 4)
      assert(dice.roll === 3)
      assert(dice.roll === 2)
      assert(dice.roll === 1)
    }

    test("seq dice same") {
      val dice = new SeqDice(6, Seq(1, 1, 1, 1, 1, 1))
      assert(dice.roll === 1)
      assert(dice.roll === 1)
      assert(dice.roll === 1)
      assert(dice.roll === 1)
      assert(dice.roll === 1)
      assert(dice.roll === 1)
    }

  test("seq dice rand") {
      val dice = new SeqDice(6, Seq(1, 1, 6, 3, 1, 2, 6, 6, 2, 3, 2, 2, 1))
      assert(dice.roll === 1)
      assert(dice.roll === 1)
      assert(dice.roll === 6)
      assert(dice.roll === 3)
      assert(dice.roll === 1)
      assert(dice.roll === 2)
      assert(dice.roll === 6)
      assert(dice.roll === 6)
      assert(dice.roll === 2)
      assert(dice.roll === 3)
      assert(dice.roll === 2)
      assert(dice.roll === 2)
      assert(dice.roll === 1)
    }
  
  test("seq dice too many rools") {
    val dice = new SeqDice(6, Seq(1, 1, 1))
    assert(dice.roll === 1)
    assert(dice.roll === 1)
    assert(dice.roll === 1)
    intercept[NoSuchElementException] {
      dice.roll
    }
  }
  
  
}