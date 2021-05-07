


public class Cube
{
    private Point3D center;
    private double length;

    
    public Cube()
    {
        center = new Point3D(0, 0, 0);
        length = 1;   
    }

    public Cube(Point3D cent, double l)
    {
        center = cent;
        length = l;   
    }
    
    public Point3D[] getPoints(){
        double h = length / 2;
        double cx = center.x;
        double cy = center.y;
        double cz = center.z;
        
        Point3D[] arr = new Point3D[8];
        arr[0] = new Point3D(cx - h, cy - h, cz + h);
        arr[1] = new Point3D(cx + h, cy - h, cz + h);
        arr[2] = new Point3D(cx + h, cy - h, cz - h);
        arr[3] = new Point3D(cx - h, cy - h, cz - h);
        arr[4] = new Point3D(cx - h, cy + h, cz + h);
        arr[5] = new Point3D(cx + h, cy + h, cz + h);
        arr[6] = new Point3D(cx + h, cy + h, cz - h);
        arr[7] = new Point3D(cx - h, cy + h, cz - h);
        
        return arr;    
    }
    
    
    public int[][] getLines(){
    
        int[][] arr = new int[12][2];
        
        arr[0][0] = 0; arr[0][1] = 1;
        arr[1][0] = 0; arr[1][1] = 3;
        arr[2][0] = 2; arr[2][1] = 1;
        arr[3][0] = 2; arr[3][1] = 3;
        arr[4][0] = 4; arr[4][1] = 5;
        arr[5][0] = 4; arr[5][1] = 7;
        arr[6][0] = 6; arr[6][1] = 5;
        arr[7][0] = 6; arr[7][1] = 7;
        arr[8][0] = 0; arr[8][1] = 4;
        arr[9][0] = 1; arr[9][1] = 5;
        arr[10][0] = 2; arr[10][1] = 6;
        arr[11][0] = 3; arr[11][1] = 7;
    
        return arr;
    
    }
}
