import java.util.Random;


public class AsteroidFieldExtreme extends BodyExtreme {
    

    public AsteroidFieldExtreme(double radius) {
        super();
        Random random = new Random();
        int pos = random.nextInt(3 - 0 + 1) + 0;
        this.xxPos = xpos(pos, radius);
        
        this.yyPos = ypos(pos, radius);
        
        this.xxVel = random.nextDouble() * 10000;
       
        this.yyVel = random.nextDouble() * 10000;
       
        this.mass = Math.pow(10,17);
        
        this.imgFileName = "asteroid.gif";
        
    }
    
    public static double xpos(int pos, double radius){
        Random random = new Random();
    
        if(pos == 0){
            return -radius;
           
        }
        
        else if(pos == 1){
            return random.nextDouble() * 2 * radius;
        }
        
        else if(pos == 2){
            return radius;
        }
        
        else if(pos == 3){
            return random.nextDouble() * 2 * radius;
        }

        return 0;
    }

    public static double ypos(int pos, double radius){
        Random random = new Random();

        if(pos == 0){
            return random.nextDouble() * 2 * radius;
        }

        else if(pos == 1){
            return radius;
        }
      
        else if(pos == 2){
            return random.nextDouble() * 2 * radius;
        }
        
        else if(pos == 3){
            return -radius;
        }

        return 0;
    }

    public static AsteroidFieldExtreme[] field(int n, double radius){
        AsteroidFieldExtreme[] arrAst = new AsteroidFieldExtreme[n];
        for(int i = 0; i < n; i++){
            arrAst[i] = new AsteroidFieldExtreme(radius);
        }
        return arrAst;
    }

    public void draw()
    {
        Random random = new Random();
        StdDraw.picture(xxPos,yyPos,"images/" + imgFileName,random.nextInt(360));
    }

    public void update(double dt, double fX, double fY) {

        double accelx = fX/mass;
        double accely = fY/mass;
        this.xxVel = xxVel + dt * accelx;
        this.yyVel = yyVel + dt * accely;
        this.xxPos = xxPos + dt * xxVel;
        this.yyPos = yyPos + dt * yyVel;
    }

    public void remove()
    {
        this.imgFileName = "Gone.png";
    }



}