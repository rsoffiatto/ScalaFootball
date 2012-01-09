package br.com.reduso.football

import br.com.reduso.football.AttributeTest.Result

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

class AttributeTest private (private val dice: Dice) {

  def test(attribute: Int): Result = {
    dice.roll match {
      case 1 => AttributeTest.AutoFailure
      case it if 2 to Configuration.MAX_ATTRIBUTE_VALUE contains it => if (it > attribute) AttributeTest.Failure else AttributeTest.Success
      case Configuration.MAX_DICE_VALUE => AttributeTest.AutoSuccess
    }
  }

}

object AttributeTest {

  def apply (dice: Dice = SixDice()) = {
    new AttributeTest(dice)
  }

  abstract class Result
  case object Success extends Result
  case object AutoSuccess extends Result
  case object Failure extends Result
  case object AutoFailure extends Result

}