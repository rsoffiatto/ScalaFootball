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

    "should be able to finalize the ball to the goal and score" in {
      given("a player")
      val player = new {
        val dice:Dice = new TestDices.SuccessDice(6)
      } with Shooter

      when("the player rightly shoot the ball to a empty goal")
      val result = player.shoot(None)

      then("the result of the shoot must be a Goal")
      assert(result === Shot.Goal)
    }

    "should be able to finalize the ball to the goal and miss" in {
      given("a player")
      val player = new {
        val dice:Dice = new TestDices.FailureDice(6)
      } with Shooter

      when("the player shoot the ball to a empty goal and misses")
      val result = player.shoot(None)

      then("the result must be a Goal Kick")
      assert(result === Shot.GoalKick)
    }

  }

}