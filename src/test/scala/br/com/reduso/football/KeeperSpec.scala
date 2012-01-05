package br.com.reduso.football

import org.scalatest.{GivenWhenThen, WordSpec}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import br.com.reduso.football.TestDices.{SeqDice, SuccessDice}

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
    
    "defend sucessfully a shot" in {
      
      given("a keeper")
      val keeper = new {
        val dice: Dice = new SuccessDice(6)
      } with Keeper

      when("he tries to defend a shot")
      val result = keeper.defend

      then("he should be able to defend this shot")
      assert(result === Shot.Hold)
      
    }

    "send the ball to a corner kick" in {
      given("a keeper")
      val keeper = new {
        val dice = new SeqDice(6, Seq(3, 6))
      } with Keeper
      when("he tries to defend a shot")
      val result = keeper.defend

      then("he should be able to send the ball to a corner kick")
      assert(result === Shot.CornerKick)

    }
    
    "parry the ball" in {
      given("a keeper")
      val keeper = new {
        val dice = new SeqDice(6, Seq(3, 1))
      } with Keeper
      
      when("he tries to defend a shot")
      val result = keeper.defend
      
      then("he should be able to parry the ball")
      assert(result === Shot.Parry)
      
    }
    
  }
  
}