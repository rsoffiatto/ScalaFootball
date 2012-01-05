package br.com.reduso.football

import util.Random

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

class Dice protected (val faces: Int){

  private val random = new Random

  def roll = {
    random.nextInt(faces) + 1
  }

}

object Dice {

  def apply(faces: Int = 6) = {
    new Dice(faces)
  }

}

object TwelveDice {

  def apply() = {
    Dice(12)
  }

}

object SixDice {

  def apply() = {
    Dice(6)
  }

}