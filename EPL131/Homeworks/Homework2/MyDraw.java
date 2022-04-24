/**
* Author: Iason Georgiou 
* ID: 1058044
* Written: 02/11/2021
* Last updated: 15/11/2021
*
* Windows commands:
* Compilation command: javac -cp .;./stdlib.jar MyDraw.java
* Execution command: java -cp .;./stdlib.jar MyDraw -cmdArg arg1 arg2 arg3 arg4 arg5 (not necessary to fill all arguments)
* 
* Linux commands:
* Compilation command: javac -cp .:./stdlib.jar MyDraw.java
* Execution command: java -cp .:./stdlib.jar MyDraw -cmdArg arg1 arg2 arg3 arg4 arg5 (not necessary to fill all arguments)
* This program generates the shapes that are specified in the task
* The methods included are:
* -kaleidoscope(double r) -k r
* -kaleidscope(double r, String args) -k r Arg
* -hexagon(double r, double x, double y, Color c) -r x y colour
* -hexagon (double r, double x, double y,
Color c1, Color c2) -r x y colour1 colour 2
*-hexagon (double r, double x, double y,
Color c1, Color c2, Color c3) -r x y color1 color2 color3
* -hGrid(double r) -g N
* -hive (double r, double x, double y) -H R X Y
* -hive (double r) -H R
* -star (double x, double y, double r, int sides) -s x y r sides
 * sun(double x, double y, double r, int sides) -S x y r sides
* -filledCircle(double x, double y, double r, Color c,
char type)
*
* In each of the methods that are listed above there are
* detailed comments that are explaining the simple trigonometrical
* relations that are used for each shape.
* 
* Furthermore there is a detailed description in each function
* for how these are applied using StdDraww and Java 
*/
import java.awt.Color;

public class MyDraw {

    public static final Color black = StdDraw.BLACK;
    public static final Color blue = StdDraw.BLUE;
    public static final Color darkBlue = blue.darker().darker().darker();
    public static final Color mustard = StdDraw.ORANGE.darker();
    public static final Color white = StdDraw.WHITE;



  public static Color get_col(){

   int r = (int) (Math.random() * 256.0);
   int g = (int) (Math.random() * 256.0);
   int b = (int) (Math.random() * 256.0);
   return new Color(r,g,b);

  }

  private static Color parseColor(String c){
    if (c.equals("black")) return black;
    else if (c.equals("blue")) return blue;
    else if (c.equals("darkBlue")) return darkBlue;
    else if (c.equals("mustard")) return mustard;
    else if (c.equals("white")) return white;
    return StdDraw.GRAY;
  }

  private static double Random(double r){
    return Math.round(Math.random()*r*1000.0)/1000.0;
  }

  private static double rotateX (double x, double r, double theta){
   return x + r * Math.cos(Math.toRadians(theta));
  }

  private static double rotateY (double y, double r, double theta){
   return y + r * Math.sin(Math.toRadians(theta));
  }

  public static void filledCircle(double x, double y, double r, Color c, char type){
    StdDraw.setPenColor(c);
    switch(type){
     case 'P':
           for (int i = 1; i <= (int)(20000 * r); i++){
              double theta = Random(360.0);
              double rs = Random(r);
              double xs = rotateX(x,rs,theta);
              double ys = rotateY(y,rs,theta);
              StdDraw.point(xs,ys);
           }
           break;

     case 'A':
           double theta = 0.0;
           while (theta <= 360.0){
             double xs = rotateX(x,r,theta);
             double ys = rotateY(y,r,theta);
             StdDraw.line(x,y,xs,ys);
             theta += 0.01;
           }
           break;

    case 'C':
           double rs = 0.0;
           while (rs <= r){
             theta = 0.0;
             while (theta <= 360.0){
                 double xs = rotateX(x,rs,theta);
                 double ys = rotateY(y,rs,theta);
                 StdDraw.point(xs,ys);
                 theta += 0.1;
             }
             rs += 0.25;
          }
          break;
     case 'S':
           double xs = -r;
           while (xs <= r){
             double ys = -r;
             while (ys <= r){
                if (xs * xs + ys * ys <= r * r) StdDraw.point(xs+x,ys+y);
                ys = ys + 0.1;
             }
             xs = xs + 0.1;
           }
           break;
      /*this method uses some equilateral triangles to form a circle 
      by rotating each time the angles of the triangle, with the specified center (x,y)
      thetaAdd is equal to 60 degrees, the angle of an equilateral triangle
      It calculates each time the Xs and Ys of the points of the triangle
      and then fills a polygon using the two arrays, baseXs and baseYs
      The Xs and Ys are calculated each time using trigonometrical relations which can
      easily be observed in the Cartesian field
      */
      case 'F':
            double thetaAdd = 6*Math.PI/19;  //defines how many radiants are required in the trigonometrical relations

            //the for loop runs 100 times, which are enough to form a circle
            //(it rotates the points of the triangle 2*Math.PI/200 radiants each time)
            for (double initheta = Math.PI/2; initheta<=2*Math.PI/2; initheta+=2*Math.PI/400){
              double currTheta = initheta; //store the current angle of the triangle
            while (currTheta<=2*Math.PI+thetaAdd){ //calculate the needed rotation
              //creates the shapes
            double[] baseXs =  {x, x+r*Math.cos(currTheta), x+r*Math.cos(currTheta+thetaAdd)};
            double[] baseYs = {y, y + r*Math.sin(currTheta), y+r*Math.sin(currTheta+thetaAdd)};
            StdDraw.filledPolygon(baseXs, baseYs);
            currTheta += thetaAdd; //increase the counter 
            }
          }

            break;
    }
  }
  //first kaleidoscope using points
 public static void Kaleidoscope (int k){
  StdDraw.clear(StdDraw.WHITE);  //set colour to white
  double R = k;
  StdDraw.setPenRadius(R*0.01); //it creates a pen radius based on R and the proportion to the scale 
  for (double i=0; i<100; i=i+R){
    for (double j=0; j<100; j=j+R){
      StdDraw.setPenColor(get_col()); //sets a random color using the given method get_col()
      StdDraw.point(i+R/2, j+R/2); //creates a point in each center as per calculated in the double fors
    }
  }
}

//second kaleidoscope using filledCircle
public static void Kaleidoscope(int k, String methods){
  StdDraw.clear(StdDraw.WHITE); //set colour to white
  double R = (double)(k)/2;  //this is the correct radius for a circle of size k
  int cnt = 1; //this counter(cnt) will be used to switch through the different methods of filledCircle
  for (double i=0; i<100; i=i+k){
    for (double j=0; j<100; j=j+k){
      //draws a circle at (i+R, j+R) using a random color, with the cnt%methods.length method each time 
      filledCircle(i+R, j+R, R, get_col(), methods.charAt(cnt%methods.length())); 
      cnt++;
    }
  }
}
//first hexagon
  public static void hexagon(double r, double x, double y, Color c1){
    double[] xh = new double [6]; //X points of the hexagon
    double[] yh = new double [6]; //Y points of the hexagon

   int cnt = 0;
   //Xs and Ys are calculated circuraly, by going though Math.PI/3 radiants each time
   //in other words the difference of each angle of each point from the center
    for (double i=0; i<=2*Math.PI-(Math.PI/3); i=i+(Math.PI/3)){
      xh[cnt] = x + r*Math.sin(i);
      yh[cnt] = y + r*Math.cos(i);
      cnt++; //counter to go to the next point 
    }
    StdDraw.setPenColor(c1); //sets color to c1
    StdDraw.filledPolygon(xh, yh); //fills a Polygon with six points (hexagon)
  }

//second hexagon

public static void hexagon(double r, double x, double y, Color c1, Color c2){
  double[] xh1 = new double [4];
  double[] yh1 = new double [4];

  int cnt = 0;
  for (double i=Math.PI*3/2; i<=Math.PI*5/2; i=i+(Math.PI/3)){ //these are the angles from the left side (first colour)
    xh1[cnt] = x + r*Math.cos(i);
    yh1[cnt] = y + r*Math.sin(i);
    cnt++;
  }
  StdDraw.setPenColor(c1);
  StdDraw.filledPolygon(xh1, yh1);


  double[] xh2 = new double[4];
  double[] yh2 = new double[4];

  cnt = 0;
  for (double i=Math.PI/2; i<=Math.PI*3/2; i=i+(Math.PI/3)){ //these are the angles from the right side (second colour)
    xh2[cnt] = x + r * Math.cos(i); //both are calculated using polar equations
    yh2[cnt] = y + r * Math.sin(i);
    cnt++;
  }
  //draw the polygon with color c2
  StdDraw.setPenColor(c2); 
  StdDraw.filledPolygon(xh2, yh2);
}
//third hexagon method
public static void hexagon(double r, double x, double y, Color c1, Color c2, Color c3){
  //just call the other two hexagons, with the two-coloured hexagon inside the other, i.e. with a smaller radius
  hexagon(r, x, y, c1);
  hexagon(r*0.5, x, y, c2, c3);
}

//hGrid function
public static void hGrid(double r){
  StdDraw.setXscale(0, 110); //setting a different scale (to 110) because my Windows system borders the canvas differently
  StdDraw.setYscale(0, 110); 
  Color allColors[] = new Color [3];
  //these are the colours that we will cycle through in order to 
  //colour our hexagons with the correct pattern
  allColors[0] = parseColor ("mustard");
  allColors[1] = parseColor ("blue");
  allColors[2] = parseColor ("white");
  int cntSiz = 0; //this will count the line we are at
  int cntColor = 0; //this counter will be used to cycle through colours

  //in the first for we iterate through all the possible lines
  //using trigonometry we can find the right relations for the borders of the hGrid
  //the Y distance from one center to the one above is Math.cos(Math.PI/3)*4*r
 for (double line = 0; line <= 110 - Math.cos(Math.PI/3) * 4 * r; line+=(r + (Math.cos(Math.PI/3) * r))){
  //if the line we are at is even, then we have to print more hexagons
  //both of these borders can also be calculated using trigonometrical relations
  //the X distance between two centers should be 2*r*Math.sin(Math.PI/3)
  if (cntSiz%2==0){
  for (double xPos = 110-r/2*Math.sin(Math.PI/3); xPos-2*r*Math.sin(Math.PI/3)>=0; xPos-=2*r*Math.sin(Math.PI/3)){
    hexagon(r, xPos-r, line+r, allColors[cntColor%3], darkBlue, black);
    cntColor++;
  }
  cntSiz++;
  cntColor--;
}
//if the line we are at is odd then we have to print two fewer hexagons
//here we substract another X distance from each side so that we will have two fewer hexagons symmetrically
  else{
    for (double xPos = 110-3*r/2*Math.sin(Math.PI/3); xPos-3*r*Math.sin(Math.PI/3)>=0; xPos-=2*r*Math.sin(Math.PI/3)){
      hexagon(r, xPos-r, line+r, allColors[cntColor%3], darkBlue, black);
      cntColor++; //
    }
    cntSiz++;
    cntColor--; //decrease the color counter at the end of the line because the beginning of the next line should be with the same colour
  }
}
 } 

 //hive method
 //the hive method generates 7 hexagons with different centers
 //as calculated below
 //since the shapes are only 7, the hard-coding method is not that time-consuming in terms of writing
 //and at the same time is much safer
 //so in this method the X and Y distances are calculated with simple trigonometrical relations 
 //and the colours have been written manually to match the pattern as specified in the task
 public static void hive(double r, double x, double y){
  //setting a different scale due to system border differences much like above
  StdDraw.setXscale(0, 110);
  StdDraw.setYscale(0, 110);
  Color allColors[] = new Color [3];
  //two top
  hexagon(r, x-r*Math.sin(Math.PI/3), y+r*Math.cos(Math.PI/3)+r, blue, black, darkBlue);
  hexagon(r, x+r*Math.sin(Math.PI/3), y+r*Math.cos(Math.PI/3)+r, white, black, darkBlue);
  //three middle
  hexagon(r, x-2*r*Math.sin(Math.PI/3), y, white, black, darkBlue);
  hexagon(r, x, y, mustard, black, darkBlue);
  hexagon(r, x+2*r*Math.sin(Math.PI/3), y, blue, black, darkBlue);
  //two down
  hexagon(r, x-r*Math.sin(Math.PI/3), y-r*Math.cos(Math.PI/3)-r, blue, black, darkBlue);
  hexagon(r, x+r*Math.sin(Math.PI/3), y-r*Math.cos(Math.PI/3)-r, white, black, darkBlue);
 }

 //second hive method
 //this hive method just creates a calculated amount of first hives 
 //using the borders as calculated with the same trigonometrical relations
 //for X and Y distances
 public static void hive(double R){
  for (double x=0; x<110-6*R*Math.sin(Math.PI/3); x+=6*R*Math.sin(Math.PI/3)){
    for (double y=0; y<110-(4*R+2*R*Math.cos(Math.PI/3)); y+=(4*R+2*R*Math.cos(Math.PI/3))){
      hive(R, x+3*R*Math.sin(Math.PI/3), y+2*R+R*Math.cos(Math.PI/3));
    }
  }
 }

 //this method calculates a polar distance and is used in the star and sun functions
 //to make the calculations more clear to avoid errors
 public static double[] polarPoint (double r, double theta){
  //basic trigonometric calcutations of the polar point 
  double[] point = new double[2];
  point[0] = r*Math.cos(theta);
  point[1] = r*Math.sin(theta);
  return point;
 }

 //star method 
 //rotates the two outer points which are common between all triangles 
 //it goes with an increment of the angle of the trianlge (2*Math.PI/sides)
 //the currTheta defines the angle of the second point and the first point is currTheta - 2*Math.PI/sides
 //the thetaInside defines the angle of the inner points.
 //the first black triangle's inner point is constant, however the two other triangles' inner points rotate proportionally
 public static void star (double x, double y, double R, int sides){
  double theta = (2*Math.PI/sides); //constant to add/substract as needed in the relations 
  double thetaInside = theta/2; //counter for the inner points
  double currTheta = (2*Math.PI/sides); //counter for the outer points
  while (currTheta<=2*Math.PI+theta){
    double[] firstPoint = polarPoint (R, currTheta-theta); //calculate the x and y distances of the first point
    double[] secondPoint = polarPoint (R, currTheta); //calculate the x and y distances of the second point 
    double[] baseXs =  {x, x+firstPoint[0], x+secondPoint[0]};  //x points of the basic triangle
    double[] baseYs = {y, y + firstPoint[1], y+ secondPoint[1]}; //y points of the basic triangle
    StdDraw.setPenColor(0, 0, 0);  //set color to black
    StdDraw.filledPolygon(baseXs, baseYs); //fill basic triangle 
    double[] addXYs1 = polarPoint(R/6, thetaInside); //calculate the x and y distances of the inner point 
    double[] secondXs =  {x+addXYs1[0], x+firstPoint[0], x+secondPoint[0]};  //x points of the first inner triangle
    double[] secondYs = {y+addXYs1[1], y + firstPoint[1], y+ secondPoint[1]}; //y points of the first inner triangle
    StdDraw.setPenColor(255, 255, 255); //set color to white
    StdDraw.filledPolygon(secondXs, secondYs); //fill second inner triangle
    double[] addXYs2 = polarPoint(R/3, thetaInside); //calculate the x and y distances of the inner point 
    double[] thirdXs =  {x+addXYs2[0], x+firstPoint[0], x+secondPoint[0]}; //x points of the second inner triangle
    double[] thirdYs = {y+addXYs2[1], y + firstPoint[1], y+ secondPoint[1]}; //y points of the second inner triangle
    StdDraw.setPenColor(0, 0, 255); //set pen color to blue
    StdDraw.filledPolygon(thirdXs, thirdYs); //fill second triangle
     currTheta += theta; //increase the angles of the outer points
     thetaInside+=theta; //increase the angles of the inner points of the two triangles
  }
}
  //the sun method works similarly to the star method 
  //the only difference is that the first triangle is gray, like the canvas
  //and we add another outer triangle with enough distance to complete it 
  public static void sun (double x, double y, double R, int sides){
  double theta = (2*Math.PI/sides);
  double thetaInside = theta/2;
  double currTheta = (2*Math.PI/sides);
  while (currTheta<=2*Math.PI+theta){
    //base triangle
    double[] firstPoint = polarPoint (R, currTheta-theta);
    double[] secondPoint = polarPoint (R, currTheta);
    double[] baseXs =  {x, x+firstPoint[0], x+secondPoint[0]};
    double[] baseYs = {y, y + firstPoint[1], y+ secondPoint[1]};
    StdDraw.setPenColor(StdDraw.GRAY);
    StdDraw.filledPolygon(baseXs, baseYs);
    //inner triangle1 (red)
    double[] addXYs1 = polarPoint(R/6, thetaInside);
    double[] secondXs =  {x+addXYs1[0], x+firstPoint[0], x+secondPoint[0]};
    double[] secondYs = {y+addXYs1[1], y + firstPoint[1], y+ secondPoint[1]};
    StdDraw.setPenColor(255, 0, 0);
    StdDraw.filledPolygon(secondXs, secondYs);
    //inner triangle2 (orange)
    double[] addXYs2 = polarPoint(R/3, thetaInside);
    double[] thirdXs =  {x+addXYs2[0], x+firstPoint[0], x+secondPoint[0]};
    double[] thirdYs = {y+addXYs2[1], y + firstPoint[1], y+ secondPoint[1]};
    StdDraw.setPenColor(255, 213, 71);
    StdDraw.filledPolygon(thirdXs, thirdYs);
    //outer triangle (yellow)
    //the outer point of the yellow triangle should be equidistant to the side of the two outer points with the inner point
    //this is easily calculated through midpoints as shown below
    double xMid = (x+firstPoint[0] + x+secondPoint[0])/2;
    double yMid = (y+firstPoint[1] + y+secondPoint[1])/2;
    double xFinal = 2*xMid - x;
    double yFinal = 2*yMid - y;
    double[] fourthXs = {xFinal, x+firstPoint[0], x+secondPoint[0]};
    double[] fourthYs = {yFinal, y + firstPoint[1], y+ secondPoint[1]};
    StdDraw.setPenColor(255, 255, 0);
    StdDraw.filledPolygon(fourthXs, fourthYs);
     currTheta += theta;
     thetaInside+=theta;
  } 
  }


  public static void main(String[] args){
    StdDraw.setXscale(0,100.0);
    StdDraw.setYscale(0,100.0);
    StdDraw.clear(StdDraw.GRAY);

    if (args[0].equals("-k")){
       if (args.length == 2) Kaleidoscope(Integer.parseInt(args[1]));
        else Kaleidoscope(Integer.parseInt(args[1]), args[2]);
    }
   else if (args[0].equals("-h")){
              double r = Double.parseDouble(args[1]);
              double x = Double.parseDouble(args[2]);
              double y = Double.parseDouble(args[3]);
              Color c1 = parseColor(args[4]);
              if (args.length == 5) hexagon(r,x,y,c1);
              else if (args.length == 6){
                         Color c2 = parseColor(args[5]);
                         hexagon(r,x,y,c1,c2);}
              else if (args.length == 7){
                          Color c2 = parseColor(args[5]);
                          Color c3 = parseColor(args[6]);
                          hexagon(r,x,y,c1,c2,c3); }
     }

     else if (args[0].equals("-c")){
                double x = Double.parseDouble(args[1]);
                double y = Double.parseDouble(args[2]);
                double r = Double.parseDouble(args[3]);
                Color c = parseColor(args[4]);
                long start = System.nanoTime();
                filledCircle(x,y,r,c,args[5].charAt(0));
                System.out.println("computation time: " +
                                   (double)(System.nanoTime()-start)/10E9 + " seconds");
       }
       else if (args[0].equals("-g")){
                 double r = Double.parseDouble(args[1]);
                 hGrid(r);
       }
       else if (args[0].equals("-H")){
                 double r = Double.parseDouble(args[1]);
                  if (args.length == 2) hive(r);
                 else {
                  double x = Double.parseDouble(args[2]);
                  double y = Double.parseDouble(args[3]);
                  hive(r,x,y);}
                }
        

       else if (args[0].equals("-s")){
                   double x = Double.parseDouble(args[1]);
                   double y = Double.parseDouble(args[2]);
                   double r = Double.parseDouble(args[3]);
                   int s = Integer.parseInt(args[4]);
                   star(x,y,r,s);
       }
       else if(args[0].equals("-S")){
        double x= Double.parseDouble(args[1]);
        double y = Double.parseDouble(args[2]);
        double r = Double.parseDouble(args[3]);
        int s = Integer.parseInt(args[4]);
        sun(x, y, r, s);
       }
       else System.out.println("Wrong command arguments");
  }

}
