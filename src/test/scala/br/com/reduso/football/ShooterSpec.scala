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
class ShooterSpec extends WordSpec with GivenWhenThen {

  "A player who shoot" should {

    "should be able to finalize perfectly the ball to the goal and score" in {
      given("a player")
      val player = new {
        val dice:Dice = new TestDices.SuccessDice(6)
        val finishing = 3
      } with Shooter

      and("a keeper doing perfecly")
      val keeper = new {
        val dice = new TestDices.SuccessDice(6)
        val intercept = 5
        val hold = 5
      } with Keeper
      
      when("the player perfectly shoot the ball to the goal, even with the goalkeeper being perfectly")
      val result = player.shoot(Some(keeper))

      then("the result of the shoot must be a goal")
      assert(result === Shot.Goal)
    }

    "should be able to finalize the ball to the goal and, if the keeper cannot defend, score" in {
      given("a player")
      val player = new {
        val dice:Dice = new TestDices.ArbitraryDice(faces=6, results=2)
        val finishing = 3
      } with Shooter

      and("a the keeper unable to defend")
      val keeper = new {
        val dice = new TestDices.ArbitraryDice(faces=6, results=5)
        val intercept = 3
        val hold = 3
      } with Keeper

      when("the player shoot the ball to the goal")
      val result = player.shoot(Some(keeper))

      then("the result of the shoot must be a goal")
      assert(result === Shot.Goal)
    }

    "should be able to finalize the ball to the goal and, if the keeper can defend, do not score" in {
      given("a player")
      val player = new {
        val dice:Dice = new TestDices.ArbitraryDice(faces=6, results=2)
        val finishing = 3
      } with Shooter

      and("a the keeper making the defense")
      val keeper = new {
        val dice = new TestDices.ArbitraryDice(faces=6, results=2)
        val intercept = 3
        val hold = 3
      } with Keeper

      when("the player shoot the ball to the goal")
      val result = player.shoot(Some(keeper))

      then("the result of the shoot must not be a goal")
      assert(result != Shot.Goal)
    }
    
    "should be able to miss the target, giving a goal kick" in {
      given("a player")
      val player = new {
        val dice:Dice = new TestDices.ArbitraryDice(faces=6, results=5)
        val finishing = 3
      } with Shooter

      when("the player shoot badly the ball to the goal, even without a keeper")
      val result = player.shoot(None)

      then("the result of the shoot must be a goal kick")
      assert(result === Shot.GoalKick)
    }
  }
}