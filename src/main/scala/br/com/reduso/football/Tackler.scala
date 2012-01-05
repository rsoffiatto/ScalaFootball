package br.com.reduso.football

import br.com.reduso.football.Tackle.{TackleResult,Disposed,Loose,Failure}

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

trait Tackler {

  require(dice.faces == 6)
  require(tackle > 1 && tackle  < 6 )

  val dice: Dice
  val tackle: Int

  def tackle(opponent: Tackler): TackleResult = {
    AttributeTest(dice).test(tackle) match {
      case AttributeTest.AutoFailure | AttributeTest.Failure => Failure
      case AttributeTest.AutoSuccess => Disposed
      case AttributeTest.Success => opponent.beingTackled
    }
  }
  
  def beingTackled : TackleResult = {
    AttributeTest(dice).test(tackle) match {
      case AttributeTest.AutoFailure | AttributeTest.Failure => Disposed
      case AttributeTest.AutoSuccess | AttributeTest.Success => Loose
    }
  }

}

object Tackle {
  abstract class TackleResult
  case object Disposed extends TackleResult
  case object Loose extends TackleResult
  case object Failure extends TackleResult
}