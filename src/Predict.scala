// ************************************************************************** //
//                                                                            //
//                                                        :::      ::::::::   //
//   predict.jvm                                        :+:      :+:    :+:   //
//                                                    +:+ +:+         +:+     //
//   By: mcanal <zboub@42.fr>                       +#+  +:+       +#+        //
//                                                +#+#+#+#+#+   +#+           //
//   Created: 2015/09/27 19:54:37 by mcanal            #+#    #+#             //
//   Updated: 2015/09/28 17:52:33 by mcanal           ###   ########.fr       //
//                                                                            //
// ************************************************************************** //

import scala.io.Source //readLine

object Predict
{
	def main(args: Array[String]) : Unit =
	{
		var t0 : Float = 0f
		var t1 : Float = 0f

		try
		{
			t0 = Source.fromFile("data/t0.learned").getLines.mkString.toFloat
			t1 = Source.fromFile("data/t1.learned").getLines.mkString.toFloat
		}
		catch
		{
			case ex: Exception => println("Oops... I know nothing about cars :/")
				System.exit(0)
		}

		print("Mileage? ")
		try
		{
			val price = t0 + (t1 * readLine().toFloat)
			if (price > 0)
				println("Estimated price: " + "%.2f".format(price) + "€")
			else
				println("Estimated price: 0€")
		}
		catch
		{
			case ex: Exception => println("Nah...")
				main(args)
		}
	}
}
