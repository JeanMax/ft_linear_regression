// ************************************************************************** //
//                                                                            //
//                                                        :::      ::::::::   //
//   test.scala                                         :+:      :+:    :+:   //
//                                                    +:+ +:+         +:+     //
//   By: mcanal <zboub@42.fr>                       +#+  +:+       +#+        //
//                                                +#+#+#+#+#+   +#+           //
//   Created: 2015/09/28 14:22:03 by mcanal            #+#    #+#             //
//   Updated: 2015/09/28 14:24:09 by mcanal           ###   ########.fr       //
//                                                                            //
// ************************************************************************** //

import scala.swing.__

object FirstSwingApp extends SimpleGUIApplication
{
	def top = new MainFrame
	{
		title = "First Swing App"
		contents = new Button
		{
			text = "Click me"
		}
	}
}

object Draw
{
	def main(args: Array[String]) : Unit =
	{

	}
}
