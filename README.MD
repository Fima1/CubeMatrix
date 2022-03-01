# CubeMatrix
Program that generates, transforms and displays a 3D cube using matrix multiplicaiton. Allows the user to rotate the cube along x, y, z axes. Projects the 3D cube onto a 2D image using projection matrices. Created using Java and JavaFX. 

## Class Diagram
![alt text](https://github.com/Fima1/CubeMatrix/blob/master/class_diagram_CubeMatrix_1.png?raw=true)

## Class Files
- [Main](Main.java) - defines the UI using JavaFX, runs the program
- [Point2D](Point2D.java), [Point3D](Point3D.java) - data type classes for 2D and 3D points respectfully 
- [Cube](Cube.java) - describes a cube, returns an array of points and an array of connections between them
- [Projection](Projection.java) - class performing projection matrix calculations
- [Rotation](Rotation.java) - class performing rotation matrix calculations
- [Mat](Mat.java) - utility class containing matrix operation methods

## Display and rotation example
![alt text](https://github.com/Fima1/CubeMatrix/blob/master/cube.gif?raw=true)
