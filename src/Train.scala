// ************************************************************************** //
//                                                                            //
//                                                        :::      ::::::::   //
//   train.jvm                                          :+:      :+:    :+:   //
//                                                    +:+ +:+         +:+     //
//   By: mcanal <zboub@42.fr>                       +#+  +:+       +#+        //
//                                                +#+#+#+#+#+   +#+           //
//   Created: 2015/09/27 19:55:54 by mcanal            #+#    #+#             //
//   Updated: 2015/09/28 17:52:54 by mcanal           ###   ########.fr       //
//                                                                            //
// ************************************************************************** //

import scala.io.Source //read file
import math.abs //do the math

object Train
{
	val lRate: Float = 0.0001f //wtf?
	val limit: Float = 0.000001f

	def readFile =
	{
		var ret: List[Array[Float]] = Nil

		try
		{
			for (s <- Source.fromFile("data/data.csv").getLines.toList.map(_.split(",")).filter(_(0) != "km"))
				ret = ret ::: List(Array(s(0).toFloat / 1000, s(1).toFloat / 1000))
		}
		catch
		{
			case ex: Exception => println("Wut? Data not found...")
				System.exit(1)
		}
		ret
	}

	def sumT0(data: List[Array[Float]], t0: Float, t1: Float, sum: Float):
			Float =
	{
		if (data.tail == Nil)
			return (sum + t1 * data.head(0) - data.head(1) + t0)

		sumT0(data.tail, t0, t1, sum + t1 * data.head(0) - data.head(1) + t0)
	}

	def sumT1(data: List[Array[Float]], t0: Float, t1: Float, sum: Float):
			Float =
	{
		if (data.tail == Nil)
			return (sum + (t1 * data.head(0)- data.head(1) + t0) * data.head(0))

		sumT1(data.tail, t0, t1, sum + (t1 * data.head(0) - data.head(1) + t0) * data.head(0))
	}

	def doTheMath(data: List[Array[Float]], t0: Float, t1: Float):
			(Float, Float) =
	{
		val tmpT0: Float = lRate / data.length * sumT0(data, t0, t1, 0)
		val tmpT1: Float = lRate / data.length * sumT1(data, t0, t1, 0)

		if (abs(tmpT0) < limit && abs(tmpT1) < limit)
			return (t0 * 1000, t1)

		doTheMath(data, t0 - tmpT0, t1 - tmpT1)
	}

	def main(args: Array[String]): Unit =
	{
		val (t0, t1) = doTheMath(readFile, 0, 0)

		try
		{
			scala.tools.nsc.io.File("data/t0.learned").writeAll(t0.toString)
			scala.tools.nsc.io.File("data/t1.learned").writeAll(t1.toString)
		}
		catch
		{
			case ex: Exception => println("Wut? Could not store data...")
				System.exit(1)
		}
		println("t0: " + t0) //debug
		println("t1: " + t1) //debug
	}
}
