// ************************************************************************** //
//                                                                            //
//                                                        :::      ::::::::   //
//   test.scala                                         :+:      :+:    :+:   //
//                                                    +:+ +:+         +:+     //
//   By: mcanal <zboub@42.fr>                       +#+  +:+       +#+        //
//                                                +#+#+#+#+#+   +#+           //
//   Created: 2015/09/28 14:22:03 by mcanal            #+#    #+#             //
//   Updated: 2015/09/28 23:52:09 by mcanal           ###   ########.fr       //
//                                                                            //
// ************************************************************************** //

/*
 import scala.swing._
 import java.awt.{ Graphics2D, Color }

 object Draw extends SimpleSwingApplication
 {
 def top = new MainFrame
 {
 title = "Linear Regression"
 preferredSize = new Dimension(512, 512)
 }
 }
 */

import org.jfree.chart.{ChartFactory, ChartPanel}
import org.jfree.data.category.DefaultCategoryDataset
import org.jfree.chart.plot.PlotOrientation
import scala.swing._
import java.awt.BorderLayout

object LineChartExample extends SimpleSwingApplication {

	val ATTENTION = "Attention"
	val MEDITATION = "Meditation"

	val data = new DefaultCategoryDataset
	data.addValue(100.0, ATTENTION, 1)
	data.addValue(200.0, ATTENTION, 2)
	data.addValue(300.0, ATTENTION, 3)
	data.addValue(400.0, ATTENTION, 4)
	data.addValue(500.0, ATTENTION, 5)

	data.addValue(500.0, MEDITATION, 1)
	data.addValue(400.0, MEDITATION, 2)
	data.addValue(300.0, MEDITATION, 3)
	data.addValue(200.0, MEDITATION, 4)
	data.addValue(100.0, MEDITATION, 5)

	val chart = ChartFactory.createLineChart(
		"Brainwaves", "Time", "Value",
		data, PlotOrientation.VERTICAL,
		true, true, true)

	def top = new MainFrame {
		title = "Brainwave Plotter"
		peer.setContentPane(new ChartPanel(chart))
		peer.setLocationRelativeTo(null)
	}

}
