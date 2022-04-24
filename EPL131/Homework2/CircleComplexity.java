/**
* Author: Iason Georgiou 
* ID: 1058044
* Written: 02/11/2021
* Last updated: 11/11/2021
*
* Windows commands:
* Compilation command: javac CircleComplexity.java
* Execution command: java CircleComplexity
* 
* This program is responsible for timing each method of MyDraw.filledCircle
* and then presenting the points in a formatted text 
* as well as presenting a graph.
* The way we do this is exaplained in detail in the comments of each section.
* 
*/

import java.util.*;
import java.awt.Color;

public class CircleComplexity {

	public static void plotLine(double[] xs, double[] ys, int r, int g, int b){ //graph to plot a line with a given rgb color code
		StdDraw.setPenColor(r, g, b);
		for (int i=0; i<xs.length-1; i++){
			StdDraw.line(xs[i], ys[i], xs[i+1], ys[i+1]);
		}
	}
	public static void main(String[] args){
			StdDraw.setXscale(0, 100);
			StdDraw.setYscale(0, 100);
			//store the time-values for each method (Y points)
			double[] A = new double[8];
			double[] S = new double[8];
			double[] C = new double[8];
			double[] P = new double[8];
			double[] F = new double[8];

			double[] Rs = {5, 10, 15, 20, 25, 30, 35, 40};
			
			//min and max points to set correct scales to the graph
			double maxY=0;
			double minY= Double.MAX_VALUE;
			for (int i=0; i<Rs.length; i++){ //iterate through all radi
				double currR = Rs[i];

				//A circle
				long start = System.nanoTime();
				MyDraw.filledCircle(50, 50, currR, Color.BLACK, 'A');
				StdDraw.clear(Color.WHITE);
				double time = (double)(System.nanoTime()-start)/10E9;
				A[i] = time;
				//these if cases find the minY and maxY each iteration 
				if (time>maxY) {maxY=time;}
				if (time<minY) {minY=time;}

				//S circle
				start = System.nanoTime();
				MyDraw.filledCircle(50, 50, currR, Color.BLACK, 'S');
				StdDraw.clear(Color.WHITE);
				time = (double)(System.nanoTime()-start)/10E9;
				S[i] = time;
				if (time>maxY) {maxY=time;}
				if (time<minY) {minY=time;}

				//C circle
				start = System.nanoTime();
				MyDraw.filledCircle(50, 50, currR, Color.BLACK, 'C');
				StdDraw.clear(Color.WHITE);
				time = (double)(System.nanoTime()-start)/10E9;
				C[i] = time;
				if (time>maxY) {maxY=time;}
				if (time<minY) {minY=time;}

				//P circle
				start = System.nanoTime();
				MyDraw.filledCircle(50, 50, currR, Color.BLACK, 'P');
				StdDraw.clear(Color.WHITE);
				time = (double)(System.nanoTime()-start)/10E9;
				P[i] = time;
				if (time>maxY) {maxY=time;}
				if (time<minY) {minY=time;}

				//F circle
				start = System.nanoTime();
				MyDraw.filledCircle(50, 50, currR, Color.BLACK, 'F');
				StdDraw.clear(Color.WHITE);
				time = (double)(System.nanoTime()-start)/10E9;
				F[i] = time;
				if (time>maxY) {maxY=time;}
				if (time<minY) {minY=time;}

			}

			StdDraw.setXscale(4, 46);
			StdDraw.setYscale(-5, 70);
			StdDraw.setPenRadius(0.005);
			//plot the graphs with the correct colors
			plotLine(Rs, A, 0, 0, 0); //A corresponds to black
			plotLine(Rs, F, 255, 255, 0); //F corresponds to yellow
			plotLine(Rs, S, 255, 0, 0); //S corresponds to red
			plotLine(Rs, C, 0, 0, 255); //C corresponds to blue
			plotLine(Rs, P, 34, 139, 34); //P corresponds to green


			System.out.println("radius	 A 	 S 	 C 	 P 	 F");
			//print the values formatted  
			for (int i=0; i<8; i++){
				System.out.print(Rs[i]);
				System.out.printf("	%.2f	%.2f	%.2f	%.2f	%.2f\n", A[i], S[i], C[i], P[i], F[i]);
			}
	}
}