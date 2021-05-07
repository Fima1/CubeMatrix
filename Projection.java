

import java.lang.*;

public class Projection
{
    
    
    public static Point2D[] getPoints(Cube cube, double[] ProjAngles, double[] Angles, Point3D observer, Point3D display){
    
        Point3D[] arr = cube.getPoints();
        
        arr = Rotation.RotateX(arr, Angles[0]);
        arr = Rotation.RotateY(arr, Angles[1]);
        arr = Rotation.RotateZ(arr, Angles[2]);
        
        double projxrad = Math.toRadians(ProjAngles[0]); //convert degrees to radians
        double projyrad = Math.toRadians(ProjAngles[1]);
        double projzrad = Math.toRadians(ProjAngles[2]);
        
        
        double cosx = Math.cos(projxrad); // calculate sin and cos for each angle 
        double sinx = Math.sin(projxrad);
        
        double cosy = Math.cos(projyrad);
        double siny = Math.sin(projyrad);
        
        double cosz = Math.cos(projzrad);
        double sinz = Math.sin(projzrad);
        
        double[][] matrix1 = new double[3][3];
        matrix1[0][0] = 1; matrix1[0][1] = 0;       matrix1[0][2] = 0;
        matrix1[1][0] = 0; matrix1[1][1] = cosx;    matrix1[1][2] = sinx;
        matrix1[2][0] = 0; matrix1[2][1] = -sinx;   matrix1[2][2] = cosx;
        
        double[][] matrix2 = new double[3][3];
        matrix2[0][0] = cosy;   matrix2[0][1] = 0; matrix2[0][2] = -siny;
        matrix2[1][0] = 0;      matrix2[1][1] = 1; matrix2[1][2] = 0;
        matrix2[2][0] = siny;   matrix2[2][1] = 0; matrix2[2][2] = cosy;
        
        double[][] matrix3 = new double[3][3];
        matrix3[0][0] = cosz;   matrix3[0][1] = sinz;   matrix3[0][2] = 0;
        matrix3[1][0] = -sinz;  matrix3[1][1] = cosz;   matrix3[1][2] = 0;
        matrix3[2][0] = 0;      matrix3[2][1] = 0;      matrix3[2][2] = 1;
        
        double[][] matrixFinal = Mat.multiply(Mat.multiply(matrix1, matrix2), matrix3);
        
        double[] VectorFinal;
        double[] VectorOutput;
        
        Point2D[] result = new Point2D[arr.length];
                
        for(int i = 0; i < arr.length; i++){
        
            VectorFinal = Mat.subtract(arr[i].getArray(), observer.getArray());
            VectorOutput = Mat.multiply(matrixFinal, VectorFinal);
            
            result[i] = new Point2D((display.z / VectorOutput[2] * VectorOutput[0] + display.x), (display.z / VectorOutput[2] * VectorOutput[1] + display.y)); 
                                  
        }
        
        return result;
    
    }
    
    
    public static int[][] getLines(Cube cube){
    
        return cube.getLines();
    
    }
    

    
}
