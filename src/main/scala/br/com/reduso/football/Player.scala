package br.com.reduso.football

import exceptions.NoMatchException

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
 *
 * This class represent a Person playing a match
 *
 */
class Player private (val dice: Dice,
                      val pass: Int,
                      val tackle: Int,
                      val finishing: Int, 
                      val intercept: Int,
                      val hold: Int) extends Passer
                                      with Tackler
                                      with Shooter
                                      with Keeper {
}

object Player {

  def apply(dice: Dice = Dice(6), pass:Int, tackle: Int, finishing: Int, intercept: Int, hold: Int) = {
    new Player(dice=dice, pass=pass, tackle=tackle, finishing=finishing, intercept=intercept, hold=hold)
  }

}



