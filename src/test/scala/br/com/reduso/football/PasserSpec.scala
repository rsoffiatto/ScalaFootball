package br.com.reduso.football

import org.scalatest.{GivenWhenThen, WordSpec}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import br.com.reduso.football.TestDices.{ArbitraryDice, FailureDice, SuccessDice}

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
class PasserSpec extends WordSpec with GivenWhenThen {

  "A player who pass" should {

    "should be able to a pass the ball to another player in case of a auto success" in {
      given("a player A")
      val player1 = new {
        val dice: Dice = new SuccessDice(faces=6)
        val pass = 2
      } with Passer

      and("a player B")
      val player2 = new {
        val dice: Dice = new SuccessDice(faces=6)
        val pass = 2
      } with Passer

      when("the player A passes the ball to player B")
      val result = player1.pass(player2)

      then("the player A must have successfully passed the ball to the player B")
      assert(result === Pass.Completed)
    }

    "should be able to miss a pass to another player in case of a auto failure" in {
      given("a player A")
      val player1 = new {
        val dice: Dice = new FailureDice(faces=6)
        val pass = 5
      } with Passer

      and("a player B")
      val player2 = new {
        val dice: Dice = new SuccessDice(faces=6)
        val pass = 5
      } with Passer

      when("the player A passes the ball to player B")
      val result = player1.pass(player2)

      then("the player A must have successfully passed the ball to the player B")
      assert(result === Pass.Missed)
    }

    "should be able to pass a ball to a player who cannot get hold of it in case of auto failure from the receiver" in {
      given("a player A")
      val player1 = new {
        val dice: Dice = new SuccessDice(faces=6)
        val pass = 5
      } with Passer

      and("a player B")
      val player2 = new {
        val dice: Dice = new FailureDice(faces=6)
        val pass = 5
      } with Passer

      when("the player A passes the ball to player B")
      val result = player1.pass(player2)

      then("the player A must have successfully passed the ball to the player B, but the Player B must not have hold the ball")
      assert(result === Pass.CouldNotHold)
    }

    "should be able to pass a ball to another player in case of success in the attribute check by a low roll" in {
      given("a player A with pass attribute of 3 and next roll of dice of 2")
      val player1 = new {
        val dice = new ArbitraryDice(faces=6, results=2)
        val pass = 3        
      } with Passer
      
      and("a player B with auto-success receive")
      val player2 = new {
        val dice = new SuccessDice(faces=6)
        val pass = 2
      } with Passer
      
      when("the player A passes the ball to player B")
      val result = player1.pass(player2)
      
      then("the player A must have successfully passed the ball to player B")
      assert(result === Pass.Completed)
    }
    
    "should be able to pass a ball to another player in case of success in the attribute check by a equal row" in {
      given("a player A with a pass attribute of 3 and next roll of dice of 3")
      val player1 = new {
        val dice = new ArbitraryDice(faces=6, results=3)
        val pass = 3
      } with Passer

      and("a player B with auto-success receive")
      val player2 = new {
        val dice = new SuccessDice(faces=6)
        val pass = 2
      } with Passer

      when("the player A passes the ball to player B")
      val result = player1.pass(player2)

      then("the player A must have successfully passed the ball to player B")
      assert(result === Pass.Completed)
    }
    
    "should be able to miss a pass a ball to another player in case of failure in the attribute check" in {
      given("a player A with a pass attribute of 3 and next roll of dice of 4")
      val player1 = new {
        val dice = new ArbitraryDice(faces=6, results=4)
        val pass = 3
      } with Passer

      and("a player B with auto-success receive")
      val player2 = new {
        val dice = new SuccessDice(faces=6)
        val pass = 2
      } with Passer

      when("the player A passes the ball to player B")
      val result = player1.pass(player2)

      then("the player A must have missed the pass to player B")
      assert(result === Pass.Missed)
    }

    "should be able to fail to hold the ball in case of failure in the attribute check of the receiver" in {
      given("a player A with a auto-success on the pass")
      val player1 = new {
        val dice = new SuccessDice(faces=6)
        val pass = 5
      } with Passer

      and("a player B with auto-failure receive")
      val player2 = new {
        val dice = new FailureDice(faces=6)
        val pass = 2
      } with Passer

      when("the player A passes the ball to player B")
      val result = player1.pass(player2)

      then("the player A must have missed the pass to player B")
      assert(result === Pass.CouldNotHold)
    }
    
  }

}