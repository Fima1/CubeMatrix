

public class Point2D
{

    public double x;
    public double y;

    
    public Point2D()
    {
        x = 0;
        y = 0;
    }

    
    public Point2D(double NewX, double NewY)
    {
        x = NewX;
        y = NewY;
    }
    
    public double[] getArray(){
    double[] arr = new double[2];
    arr[0] = x;
    arr[1] = y;
    
    return arr;
    }
}
