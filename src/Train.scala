// ************************************************************************** //
//                                                                            //
//                                                        :::      ::::::::   //
//   Train.scala                                        :+:      :+:    :+:   //
//                                                    +:+ +:+         +:+     //
//   By: mcanal <zboub@42.fr>                       +#+  +:+       +#+        //
//                                                +#+#+#+#+#+   +#+           //
//   Created: 2015/09/27 19:55:54 by mcanal            #+#    #+#             //
//   Updated: 2015/09/30 14:52:32 by mcanal           ###   ########.fr       //
//                                                                            //
// ************************************************************************** //

/*
** Train your model to predict cars price. It will read your dataset
** file (data/data.csv) and perform a linear regression on the data.
** Once the linear regression has completed, the variables theta0 and
** theta1 will be saved for use in Predict.scala.
*/

import scala.io.Source.fromFile //read file
import math.abs //do the math

object Train
{
	val scale: Float = 0.001f
	val lRate: Float = 0.15f * scale
	val limit: Float = 0.0000005f

	//calculate the precision on t0 and t1
	def errorRate(data: List[Array[Float]], t0: Float, t1: Float, sum: Float): 
			Float =
	{
		if (data.tail == Nil)
			return ((sum + abs(data.head(1) - (t0 + t1 * data.head(0)))
				/ data.head(1) / data.length * 100))

		errorRate(data.tail, t0, t1, sum + 
			abs(data.head(1) - (t0 + t1 * data.head(0))) / data.head(1))
	}

	//calculate the sum needed in t0 formulae
	def sumT0(data: List[Array[Float]], t0: Float, t1: Float, sum: Float): 
			Float =
	{
		if (data.tail == Nil)
			return (sum + t1 * data.head(0) - data.head(1) + t0)

		sumT0(data.tail, t0, t1, sum + t1 * data.head(0) - data.head(1) + t0)
	}

	//calculate the sum needed in t1 formulae
	def sumT1(data: List[Array[Float]], t0: Float, t1: Float, sum: Float): 
			Float =
	{
		if (data.tail == Nil)
			return (sum + (t1 * data.head(0)- data.head(1) + t0) * data.head(0))

		sumT1(data.tail, t0, t1, sum + 
			(t1 * data.head(0) - data.head(1) + t0) * data.head(0))
	}

	//calculate t0 and t1
	def doTheMath(data: List[Array[Float]], t0: Float, t1: Float):
			(Float, Float) =
	{
		val tmpT0: Float = lRate / data.length * sumT0(data, t0, t1, 0)
		val tmpT1: Float = lRate / data.length * sumT1(data, t0, t1, 0)

		if (abs(tmpT0) < limit && abs(tmpT1) < limit)
			return (t0 / scale, t1)

		doTheMath(data, t0 - tmpT0, t1 - tmpT1)
	}

	//parse data.csv into a pretty list
	def readFile: List[Array[Float]] =
	{
		var ret: List[Array[Float]] = Nil

		try
		{
			for (s <- fromFile("data/data.csv").getLines.toList
					.map(_.split(",")).filter(_(0) != "km"))
				ret = ret ::: List(Array(
					s(0).toFloat * scale, s(1).toFloat * scale))
		}
		catch
		{
			case ex: Exception => println("Wut? Data not found...")
				System.exit(1)
		}
		ret
	}

	//main... starting point!
	def main(args: Array[String]): Unit =
	{
		val data = readFile
		val (t0, t1) = doTheMath(data, 0, 0)

		try
		{
			scala.tools.nsc.io.File("data/t0.train").writeAll(t0.toString)
			scala.tools.nsc.io.File("data/t1.train").writeAll(t1.toString)
		}
		catch
		{
			case ex: Exception => println("Wut? Could not store data...")
				System.exit(1)
		}

		println("       t0: " + t0)
		println("       t1: " + t1)
		println("precision: " + errorRate(data, t0 * scale, t1, 0) + "%")
	}
}
