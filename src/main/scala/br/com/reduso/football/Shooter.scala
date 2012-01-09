package br.com.reduso.football

import br.com.reduso.football.Shot.{ShotResult,Goal,Hold,GoalKick}

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

trait Shooter {

  require(dice.faces == 6)
  require(finishing > 1 && finishing < 6)

  val dice: Dice
  val finishing: Int

  def shoot(keeper: Option[Keeper]): ShotResult = {
    AttributeTest(dice).test(finishing) match {
      case AttributeTest.AutoFailure | AttributeTest.Failure => Shot.GoalKick
      case AttributeTest.Success => defend(keeper)
      case AttributeTest.AutoSuccess => Shot.Goal
    }
  }
   
  def defend(keeper: Option[Keeper]): ShotResult = {
    keeper match {
      case Some(keeper) => keeper.defend
      case None => Shot.Goal
    }
  }
}
