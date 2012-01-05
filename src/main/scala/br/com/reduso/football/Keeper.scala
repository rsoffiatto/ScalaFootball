package br.com.reduso.football

import br.com.reduso.football.Shot.ShotResult


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

  require(dice.faces == 6)

  val dice: Dice

  def defend: ShotResult = {
    
    dice.roll match {
      case 1 => Shot.Goal
      case it if 2 until 5 contains it => parry
      case 6 => Shot.Hold
    }
  }
  
  private def parry: ShotResult = {
    dice.roll match {
      case 1 => Shot.Parry
      case it if 2 until 5 contains it => Shot.Parry
      case 6 => Shot.CornerKick
    }
  }

}

object Shot {

  abstract class ShotResult
  case object Hold extends ShotResult
  case object CornerKick extends ShotResult
  case object Parry extends ShotResult
  case object Goal extends ShotResult
  case object GoalKick extends ShotResult

}