
public class Rotation
{
    
    public static Point3D[] RotateX(Point3D[] arr, double angleX){
    
        
        double projxrad = Math.toRadians(angleX); //convert degrees to radians       
        
        double cosx = Math.cos(projxrad); // calculate sin and cos for each angle 
        double sinx = Math.sin(projxrad);
        
   
        
        double[][] matrix1 = new double[3][3];
        matrix1[0][0] = 1; matrix1[0][1] = 0;       matrix1[0][2] = 0;
        matrix1[1][0] = 0; matrix1[1][1] = cosx;    matrix1[1][2] = -sinx;
        matrix1[2][0] = 0; matrix1[2][1] = sinx;   matrix1[2][2] = cosx;
      

        
        Point3D[] result = new Point3D[arr.length];
        double[] VectorOutput;
                
        for(int i = 0; i < arr.length; i++){
            VectorOutput = Mat.multiply(matrix1, arr[i].getArray());
            result[i] = new Point3D(VectorOutput[0], VectorOutput[1], VectorOutput[2]); 
                       
        }
        
        return result;
        
    }
    
    
    public static Point3D[] RotateY(Point3D[] arr, double angleY){
        
        double projyrad = Math.toRadians(angleY); //convert degrees to radians       
        
        double cosy = Math.cos(projyrad); // calculate sin and cos for each angle 
        double siny = Math.sin(projyrad);
        
   
        
        double[][] matrix2 = new double[3][3];
        matrix2[0][0] = cosy;   matrix2[0][1] = 0; matrix2[0][2] = siny;
        matrix2[1][0] = 0;      matrix2[1][1] = 1; matrix2[1][2] = 0;
        matrix2[2][0] = -siny;   matrix2[2][1] = 0; matrix2[2][2] = cosy;
              
        Point3D[] result = new Point3D[arr.length];
        double[] VectorOutput;
                
        for(int i = 0; i < arr.length; i++){
            VectorOutput = Mat.multiply(matrix2, arr[i].getArray());
            result[i] = new Point3D(VectorOutput[0], VectorOutput[1], VectorOutput[2]); 
                       
        }
        
        return result;
        
    }
    
    
    public static Point3D[] RotateZ(Point3D[] arr, double angleZ){
        
        double projzrad = Math.toRadians(angleZ); //convert degrees to radians       
        
        double cosz = Math.cos(projzrad); // calculate sin and cos for each angle 
        double sinz = Math.sin(projzrad);
        
   
        
        double[][] matrix3 = new double[3][3];
        matrix3[0][0] = cosz;   matrix3[0][1] = -sinz;   matrix3[0][2] = 0;
        matrix3[1][0] = sinz;  matrix3[1][1] = cosz;   matrix3[1][2] = 0;
        matrix3[2][0] = 0;      matrix3[2][1] = 0;      matrix3[2][2] = 1;
              
        Point3D[] result = new Point3D[arr.length];
        double[] VectorOutput;
                
        for(int i = 0; i < arr.length; i++){
            VectorOutput = Mat.multiply(matrix3, arr[i].getArray());
            result[i] = new Point3D(VectorOutput[0], VectorOutput[1], VectorOutput[2]); 
                       
        }
        
        return result;
        
    }
}
