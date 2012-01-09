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
class TacklerSpec extends WordSpec with GivenWhenThen {

  "A player who tackle" should {

    "should be able to tackle and dispose the ball from another player in case of auto-success" in {
      given("a player A")
      val player1 = new {
        val dice:Dice = new TestDices.SuccessDice
        val tackle = 2
      } with Tackler

      and("a player B")
      val player2 = new {
        val dice:Dice = new TestDices.SuccessDice
        val tackle = 2
      } with Tackler

      when("the player A tackle the player B")
      val result = player1.tackle(player2)

      then("the player A must have successful got the ball")
      assert(result === Tackle.Disposed)
    }

    "should be able to tackle and dispose the ball from another player in case of success in a attribute-check" in {
      given("a player A")
      val player1 = new {
        val dice:Dice = new TestDices.ArbitraryDice(results=2)
        val tackle = 3
      } with Tackler

      and("a player B")
      val player2 = new {
        val dice:Dice = new TestDices.FailureDice
        val tackle = 2
      } with Tackler

      when("the player A tackle the player B")
      val result = player1.tackle(player2)

      then("the player A must have successful got the ball")
      assert(result === Tackle.Disposed)
    }

    "should be able to tackle, but be unable to dispose the ball of another player in case of auto-failure" in {
      given("a player A")
      val player1 = new {
        val dice:Dice = new TestDices.FailureDice
        val tackle = 2
      } with Tackler

      and("a player B")
      val player2 = new {
        val dice:Dice = new TestDices.FailureDice
        val tackle = 2
      } with Tackler

      when("the player A tackle the player B")
      val result = player1.tackle(player2)

      then("the player B must keep the ball")
      assert(result === Tackle.Failure)
    }

    "should be able to tackle, but be unable to dispose the ball of another player in case of failure in attribute check" in {
      given("a player A")
      val player1 = new {
        val dice:Dice = new TestDices.ArbitraryDice(results=3)
        val tackle = 2
      } with Tackler

      and("a player B")
      val player2 = new {
        val dice:Dice = new TestDices.FailureDice
        val tackle = 2
      } with Tackler

      when("the player A tackle the player B")
      val result = player1.tackle(player2)

      then("the player B must keep the ball")
      assert(result === Tackle.Failure)
    }

    "should be able to prevent the ball from being completely disposed by a attribute check success player" in {
      given("a player A")
      val player1 = new {
        val dice:Dice = new TestDices.ArbitraryDice(results=2)
        val tackle = 2
      } with Tackler

      and("a player B")
      val player2 = new {
        val dice:Dice = new TestDices.SuccessDice
        val tackle = 2
      } with Tackler

      when("the player A tackle the player B")
      val result = player1.tackle(player2)

      then("the ball must get loose")
      assert(result === Tackle.Loose)
    }

  }

}