public class Planet{
	public double xxPos,yyPos,xxVel,yyVel,mass;
	public String imgFileName;
	private static final double G=6.67e-11;
	public Planet(double xP, double yP, double xV,
              double yV, double m, String img){
		/** innitial a planet */
		xxPos=xP;
		yyPos=yP;
        xxVel=xV;
        yyVel=yV;
        mass=m;
        imgFileName=img;
	}
	public Planet(Planet p){
		/** Copy a planet*/
		xxPos=p.xxPos;
		yyPos=p.yyPos;
        xxVel=p.xxVel;
        yyVel=p.yyVel;
        mass=p.mass;
        imgFileName=p.imgFileName;
	}
    public double calcDistance(Planet p){
    	/** calculate the distance between planets*/
        return  Math.sqrt((xxPos-p.xxPos)*(xxPos-p.xxPos)+(yyPos-p.yyPos)*(yyPos-p.yyPos));
    }
    public double calcForceExertedBy(Planet p){
    	/** calculate the force between two planets*/
    	return G*p.mass*mass/calcDistance(p)/calcDistance(p);
    }
    public double calcForceExertedByX(Planet p){
    	/** calculate the force between two planets in x*/
    	double ans= calcForceExertedBy(p)*(p.xxPos-xxPos)/calcDistance(p);
        return ans;
    }
    public double calcForceExertedByY(Planet p){
    	/** calculate the force between two planets in y*/
    	double ans= calcForceExertedBy(p)*(p.yyPos-yyPos)/calcDistance(p);
        return ans;
    }
    public double calcNetForceExertedByX(Planet[] Allplanets){
    	/** calculate the total force between this planet and other planets in x*/
    	double sum=0;
    	for(Planet i : Allplanets){
    		if(this.equals(i)){
    			continue;
    		}
    		sum=sum+calcForceExertedByX(i);
    	}
    	return sum;
    }
    public double calcNetForceExertedByY(Planet[] Allplanets){
    	/** calculate the total force between this planet and other planets in y*/
    	double sum=0;
    	for(Planet i : Allplanets){
    		if(this.equals(i)==false){
    			sum=sum+calcForceExertedByY(i);
    		}
    	}
    	return sum;
    }
    public void update(double dt,double fx,double fy){
    	xxVel=fx/mass*dt+xxVel;
    	yyVel=fy/mass*dt+yyVel;
        xxPos=xxPos+xxVel*dt;
        yyPos=yyPos+yyVel*dt;
    }
    public void draw(){
    	StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
    }
}