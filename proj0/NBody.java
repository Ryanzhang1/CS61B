public class NBody{
	public static double readRadius(String filename){
	  /** find the ridus of the universe*/
      In in =new In(filename);
      int n=in.readInt();
      double r=in.readDouble();
      return r;
	}
	public static Planet[] readPlanets(String filename){
		/** read the planets from a file*/
		In in = new In(filename);
		int n=in.readInt();
		double r=in.readDouble();
		Planet[] planets=new Planet[n];
        int i=0;
        while(i<n){
        	double xxPos,yyPos,xxVel,yyVel,mass;
        	String name;
        	xxPos=in.readDouble();
        	yyPos=in.readDouble();
        	xxVel=in.readDouble();
        	yyVel=in.readDouble();
        	mass=in.readDouble();
        	name=in.readString();
        	planets[i]=new Planet(xxPos,yyPos,xxVel,yyVel,mass,name);
        	i+=1;
        }
        return planets;
	}
	public static void main(String[] args) {
		double T=new Double(args[0]);
		double dt=new Double(args[1]);
		String filename=args[2];
		double radius=readRadius(filename);
        Planet[] planets=readPlanets(filename);
        StdDraw.setXscale(-radius,radius);
        StdDraw.setYscale(-radius,radius);
        StdDraw.enableDoubleBuffering();
        double i=0;
        int num=planets.length;
        while(i<=T){
        	double[] xForces=new double[num];
        	double[] yForces=new double[num];
            for(int j=0;j<num;j++){
            	xForces[j]=planets[j].calcNetForceExertedByX(planets);
            	yForces[j]=planets[j].calcNetForceExertedByY(planets);
            }
            for(int j=0;j<num;j++){
            	planets[j].update(dt,xForces[j],yForces[j]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for(Planet planet: planets){
        	    planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            i+=dt;
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int j = 0; j < planets.length; j++) {
        StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[j].xxPos, planets[j].yyPos, planets[j].xxVel,
                  planets[j].yyVel, planets[j].mass, planets[j].imgFileName);   
        }

	}
}