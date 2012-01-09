package br.com.reduso.football

import br.com.reduso.football.Shot.{Parry, ParrySafely, Hold, CouldNotStop, Goal, ShotResult, CornerKick}


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

trait Keeper {

  require(dice.faces == Configuration.MAX_DICE_VALUE)
  require(intercept > 1 && intercept < Configuration.MAX_DICE_VALUE)
  require(hold > 1 && hold < Configuration.MAX_DICE_VALUE)
  
  val dice: Dice
  val intercept: Int
  val hold: Int

  def defend: ShotResult = {
    
    AttributeTest(dice).test(intercept) match {
      case AttributeTest.AutoFailure | AttributeTest.Failure => Goal
      case AttributeTest.AutoSuccess => Shot.Hold
      case AttributeTest.Success => tryToHold
    }
  }
  
  private def tryToHold: ShotResult = {
    AttributeTest(dice).test(hold) match {
      case AttributeTest.AutoFailure => CouldNotStop
      case AttributeTest.Failure => parry
      case AttributeTest.AutoSuccess | AttributeTest.Success => Hold
    }
  }

  private def parry: ShotResult = {
    AttributeTest(dice).test(hold) match {
      case AttributeTest.AutoFailure => CouldNotStop
      case AttributeTest.Failure => Parry
      case AttributeTest.Success => CornerKick
      case AttributeTest.AutoSuccess => ParrySafely 
    }
  }
  
}

object Shot {

  abstract class ShotResult
  case object Hold extends ShotResult
  case object CornerKick extends ShotResult
  case object Parry extends ShotResult
  case object ParrySafely extends ShotResult
  case object CouldNotStop extends ShotResult
  case object Goal extends ShotResult
  case object GoalKick extends ShotResult

}