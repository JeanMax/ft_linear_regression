// ************************************************************************** //
//                                                                            //
//                                                        :::      ::::::::   //
//   predict.jvm                                        :+:      :+:    :+:   //
//                                                    +:+ +:+         +:+     //
//   By: mcanal <zboub@42.fr>                       +#+  +:+       +#+        //
//                                                +#+#+#+#+#+   +#+           //
//   Created: 2015/09/27 19:54:37 by mcanal            #+#    #+#             //
//   Updated: 2015/09/28 18:54:26 by mcanal           ###   ########.fr       //
//                                                                            //
// ************************************************************************** //

import scala.io.Source.fromFile //read file
import scala.io.StdIn.readLine //read... line

object Predict
{
	def main(args: Array[String]) : Unit =
	{
		var t0 : Float = 0f
		var t1 : Float = 0f

		try
		{
			t0 = fromFile("data/t0.learned").getLines.mkString.toFloat
			t1 = fromFile("data/t1.learned").getLines.mkString.toFloat
		}
		catch
		{
			case ex: Exception => println("Oops... I know nothing about cars :/")
				System.exit(0)
		}

		print("Mileage? ")
		try
		{
			var price = readLine().toFloat
			if (price < 0) throw new IllegalArgumentException

			price = t0 + t1 * price.toFloat
			if (price < 0) price = 0

			println("Estimated price: " + "%.2f".format(price) + "€")
		}
		catch
		{
			case ex: Exception => println("Nah...")
				main(args)
		}
	}
}
