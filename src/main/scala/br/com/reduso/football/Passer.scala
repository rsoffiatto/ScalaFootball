package br.com.reduso.football

import br.com.reduso.football.Pass.{PassResult, Missed, CouldNotHold, Completed}

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

/**
 * TODO: give passer and receiver different tests
 */
trait Passer {

  require(dice.faces == 6)
  require(pass > 1 && pass  < 6)

  val dice: Dice
  val pass: Int

  def pass(destination: Passer): PassResult = {
    AttributeTest(dice).test(pass) match {
      case AttributeTest.AutoFailure | AttributeTest.Failure => Missed
      case AttributeTest.AutoSuccess | AttributeTest.Success => destination.receive
    }
  }

  def receive : PassResult = {
    AttributeTest(dice).test(pass) match {
      case AttributeTest.AutoFailure | AttributeTest.Failure => CouldNotHold
      case AttributeTest.AutoSuccess | AttributeTest.Success => Completed
    }
  }

}

object Pass {
  abstract class PassResult
  case object Missed extends PassResult
  case object CouldNotHold extends PassResult
  case object Completed extends PassResult
}