

public class Point3D
{

    public double x;
    public double y;
    public double z;

    
    public Point3D()
    {
        x = 0;
        y = 0;
        z = 0;
    }

    
    public Point3D(double NewX, double NewY, double NewZ)
    {
        x = NewX;
        y = NewY;
        z = NewZ;
    }
    
    public double[] getArray(){
    double[] arr = new double[3];
    arr[0] = x;
    arr[1] = y;
    arr[2] = z;
    
    return arr;
    }
}
