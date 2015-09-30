// ************************************************************************** //
//                                                                            //
//                                                        :::      ::::::::   //
//   Predict.scala                                      :+:      :+:    :+:   //
//                                                    +:+ +:+         +:+     //
//   By: mcanal <zboub@42.fr>                       +#+  +:+       +#+        //
//                                                +#+#+#+#+#+   +#+           //
//   Created: 2015/09/27 19:54:37 by mcanal            #+#    #+#             //
//   Updated: 2015/09/30 14:50:31 by mcanal           ###   ########.fr       //
//                                                                            //
// ************************************************************************** //

/*
** Predict the price of a car for a given mileage.
** It will prompt you for a mileage,
** and then give you back the estimated price for that mileage.
*/

import scala.io.Source.fromFile //read file
import scala.io.StdIn.readLine //read... line (stdin)

object Predict
{
	//parse t0/t1 from files made by Train.scala
	def readFiles: (Float, Float) =
	{
		try
		{
			val t0: Float = fromFile("data/t0.train").getLines.mkString.toFloat
			val t1: Float = fromFile("data/t1.train").getLines.mkString.toFloat
			(t0, t1)
		}
		catch
		{
			case ex: Exception => println("Oops... Train me maybe? :/")
				(0, 0)
		}
	}

	//prompt user for a mileage, then print the estimated price
	def prompt(t0: Float, t1: Float): Unit =
	{
		print("Mileage? ")
		try
		{
			val mileage = readLine().toFloat
			val price = if (t0 + t1 * mileage > 0) t0 + t1 * mileage else 0

			if (mileage < 0) throw new IllegalArgumentException

			println("Estimated price: " + "%.2f".format(price) + "€")
		}
		catch
		{
			case ex: Exception => println("Nah...")
				prompt(t0, t1)
		}
	}

	//main... starting point!
	def main(args: Array[String]): Unit =
	{
		val (t0, t1) = readFiles

		if (t0 != 0 && t1 != 0)
			prompt(t0, t1)
	}
}
