package br.com.reduso.football

import org.scalatest.{GivenWhenThen, WordSpec}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import br.com.reduso.football.TestDices.{ArbitraryDice, SeqDice, SuccessDice}

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
class KeeperSpec  extends WordSpec with GivenWhenThen {

  "a goal keeper" should {
    
    "sucessfully hold a shot with a auto-success when the keeper try to intercept" in {
      
      given("a keeper")
      val keeper = new {
        val dice: Dice = new SeqDice(faces=6, results=Seq(6, 1))
        val intercept = 3
        val hold = 3
      } with Keeper

      when("he tries to defend a shot")
      val result = keeper.defend

      then("he should be able to hold the ball")
      assert(result === Shot.Hold)
    }
    
    "sucessfully hold a shot with a success in a attribute test after intercepting the ball" in {
      
      given("a keeper")
      val keeper = new {
        val dice: Dice = new SeqDice(faces=6, results=Seq(2, 2))
        val intercept = 3
        val hold = 3
      } with Keeper

      when("he tries to defend a shot")
      val result = keeper.defend

      then("he should be able to hold the ball")
      assert(result === Shot.Hold)
    }

    "send the ball to a corner kick when he cannot hold the ball, but has success in a attribute check" in {
      given("a keeper")
      val keeper = new {
        val dice = new SeqDice(faces=6, results=Seq(3, 5, 2))
        val intercept = 3
        val hold = 2
      } with Keeper
      when("he tries to defend a shot")
      val result = keeper.defend

      then("he should be able to send the ball to a corner kick")
      assert(result === Shot.CornerKick)
    }
    
    "parry the ball safely when he cannot hold the ball, but has and auto-success in the attribute check" in {
      given("a keeper")
      val keeper = new {
        val dice = new SeqDice(6, Seq(3, 4, 6))
        val intercept = 3
        val hold = 3
      } with Keeper
      
      when("he tries to defend a shot")
      val result = keeper.defend
      
      then("he should be able to parry the ball")
      assert(result === Shot.ParrySafely)
    }

    "just parry the ball when he cannot hold the ball nor send it to a safer place" in {
      given("a keeper")
      val keeper = new {
        val dice = new SeqDice(6, Seq(3, 4, 4))
        val intercept = 3
        val hold = 3
      } with Keeper

      when("he tries to defend a shot")
      val result = keeper.defend

      then("he should be able to parry the ball")
      assert(result === Shot.Parry)
    }

    "parry the ball to the goal when he intercepted, but had an auto-failure trying to hold" in {
      given("a keeper")
      val keeper = new {
        val dice = new SeqDice(6, Seq(3, 1))
        val intercept = 3
        val hold = 3
      } with Keeper

      when("he tries to defend a shot")
      val result = keeper.defend

      then("he should be able to parry the ball")
      assert(result === Shot.CouldNotStop)
    }

    "parry the ball to the goal when he intercepted, could not hold and had an auto-failure trying to parry" in {
      given("a keeper")
      val keeper = new {
        val dice = new SeqDice(6, Seq(3, 4, 1))
        val intercept = 3
        val hold = 3
      } with Keeper

      when("he tries to defend a shot")
      val result = keeper.defend

      then("he should be able to parry the ball")
      assert(result === Shot.CouldNotStop)
    }

  }
  
}