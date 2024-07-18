import edu.princeton.cs.algs4.StdDraw;
public class BouncingBall {
    public static void main(String[] args) {


        StdDraw.setXscale(-1.0, 1.0);
        StdDraw.setYscale(-1.0, 1.0);
        StdDraw.enableDoubleBuffering();

        double rx = 0.480, ry = 0.860;     
        double vx = 0.015, vy = 0.023;    
        double radius = 0.05;    
        
        double rx2 = 0.380, ry2 = 0.680;
        double vx2 = 0.02, vy2 = 0.023;
        double radius2 = 0.05;
        while (true)  {

            
            if (Math.abs(rx + vx) > 1.0 - radius) vx = -vx;
            if (Math.abs(ry + vy) > 1.0 - radius) vy = -vy;
            if(Math.abs(rx2+vx2) > 1.0 - radius2) vx2 = -vx2;
            if(Math.abs(ry2+vy2) > 1.0 - radius2) vy2 = -vy2;

            rx = rx + vx;
            ry = ry + vy;
            rx2 = rx2 + vx2;
            ry2 = ry2 + vy2;
            double distance = Math.sqrt(Math.pow(rx - rx2, 2) + Math.pow(ry - ry2, 2));
            if (distance <= radius + radius2) {
                vx = -vx;
                vy = -vy;
                vx2 = -vx2;
                vy2 = -vy2;
            }
            StdDraw.setPenColor(StdDraw.GRAY);
            StdDraw.filledSquare(0, 0, 1.0);
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.filledCircle(rx, ry, radius);
            StdDraw.filledCircle(rx2, ry2, radius2);
            StdDraw.show();
            StdDraw.pause(20);
        }
    }
}
