// ************************************************************************** //
//                                                                            //
//                                                        :::      ::::::::   //
//   predict.scala                                      :+:      :+:    :+:   //
//                                                    +:+ +:+         +:+     //
//   By: mcanal <zboub@42.fr>                       +#+  +:+       +#+        //
//                                                +#+#+#+#+#+   +#+           //
//   Created: 2015/09/27 19:54:37 by mcanal            #+#    #+#             //
//   Updated: 2015/09/30 03:49:04 by mcanal           ###   ########.fr       //
//                                                                            //
// ************************************************************************** //

import scala.io.Source.fromFile //read file
import scala.io.StdIn.readLine //read... line

object Predict
{
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

	def main(args: Array[String]): Unit =
	{
		val (t0, t1) = readFiles

		if (t0 != 0 && t1 != 0)
			prompt(t0, t1)
	}
}
