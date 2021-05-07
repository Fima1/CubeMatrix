import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

// main class
 
public class Main extends Application {

    static double XSize = 800;
    static double YSize = 800;
    
    private double[] Angles     = new double[3];
    private double[] ProjAngles = new double[3];
    
      
    final Slider AngleX = new Slider();
    final Slider AngleY = new Slider();
    final Slider AngleZ = new Slider();
    
    final Label TransformationTitle    = new Label("Rotation");
    final Label AngleXCaption  = new Label("X:");
    final Label AngleYCaption  = new Label("Y:");
    final Label AngleZCaption  = new Label("Z:");
    
    final Label AngleXValue    = new Label("0");
    final Label AngleYValue    = new Label("0");
    final Label AngleZValue    = new Label("0");
    //-------------------------------------------
    final Slider ProjAngleX     = new Slider();
    final Slider ProjAngleY     = new Slider();
    final Slider ProjAngleZ     = new Slider();
    
    final Label ProjectionTitle    = new Label("Perspective projection viewing angles");
    final Label ProjAngleXCaption  = new Label("X:");
    final Label ProjAngleYCaption  = new Label("Y:");
    final Label ProjAngleZCaption  = new Label("Z:");
    
    final Label ProjAngleXValue    = new Label("0");
    final Label ProjAngleYValue    = new Label("0");
    final Label ProjAngleZValue    = new Label("0");
    
    final Canvas canvas = new Canvas();
    
    final static Color textColor = Color.BLACK;
  
    private Cube    MyCube      = new Cube(new Point3D(0,0,0), 60); // a
    private Point3D ViewPoint   = new Point3D(-100, -75,250); // c
    //private Point3D display     = new Point3D(-100, -75, - 100 * (1 /  Math.sqrt(3))); // the display surface's position relative to the camera pinhole C, e
    private Point3D display     = new Point3D(-75, -50, - 250 * (1 /  Math.sqrt(3)));
    
    @Override
    public void start(Stage stage) {
        
        Angles[0] = 0;
        Angles[1] = 0;
        Angles[2] = 0;
        
        Group root  = new Group();
        
        Scene scene = new Scene(root, XSize + 400, YSize + 30);
        stage.setScene(scene);
        stage.setTitle("Cube animation");
         
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(10);
        grid.setHgap(70);
        //-------------------------------------------------------------
        
        
        canvas.setHeight (YSize);
        canvas.setWidth(XSize);
        
        paintPicture();
        
        GridPane.setConstraints(canvas, 0, 0, 1, 9);
        grid.getChildren().add(canvas);
        
        //=============================================================
        TransformationTitle.setTextFill(textColor);
        GridPane.setConstraints(TransformationTitle, 1, 0,3,1);
        grid.getChildren().add(TransformationTitle);
        //-------------------------------------------------------------
        customaseSliderGroup(AngleX);
        AngleXCaption.setTextFill(textColor);
        GridPane.setConstraints(AngleXCaption, 1, 1);
        grid.getChildren().add(AngleXCaption);
 
        AngleX.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
                  Angles[0] = new_val.doubleValue();
                  AngleXValue.setText(String.format("%.0f", new_val));
                  paintPicture();
            }
        });
 
        GridPane.setConstraints(AngleX, 2, 1);
        grid.getChildren().add(AngleX);
 
        AngleXValue.setTextFill(textColor);
        GridPane.setConstraints(AngleXValue, 3, 1);
        grid.getChildren().add(AngleXValue);
        //-------------------------------------------------------------
        customaseSliderGroup(AngleY);
        AngleYCaption.setTextFill(textColor);
        GridPane.setConstraints(AngleYCaption, 1, 2);
        grid.getChildren().add(AngleYCaption);
 
        AngleY.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
                  Angles[1] = new_val.doubleValue();
                  AngleYValue.setText(String.format("%.0f", new_val));
                  paintPicture();
            }
        });
 
        GridPane.setConstraints(AngleY, 2, 2);
        grid.getChildren().add(AngleY);
 
        AngleYValue.setTextFill(textColor);
        GridPane.setConstraints(AngleYValue, 3, 2);
        grid.getChildren().add(AngleYValue);
        //-------------------------------------------------------------
        customaseSliderGroup(AngleZ);
        AngleZCaption.setTextFill(textColor);
        GridPane.setConstraints(AngleZCaption, 1, 3);
        grid.getChildren().add(AngleZCaption);
 
        AngleZ.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                  Angles[2] = new_val.doubleValue();
                  AngleZValue.setText(String.format("%.0f", new_val));
                  paintPicture();
            }
        });
 
        GridPane.setConstraints(AngleZ, 2, 3);
        grid.getChildren().add(AngleZ);
 
        AngleZValue.setTextFill(textColor);
        GridPane.setConstraints(AngleZValue, 3, 3);
        grid.getChildren().add(AngleZValue);
        //=============================================================================
        
        ProjectionTitle.setTextFill(textColor);
        GridPane.setConstraints(ProjectionTitle, 1, 4,3,1);
        grid.getChildren().add(ProjectionTitle);
        //-----------------------------------------------------------------------------      
        customasePerSliderGroup(ProjAngleX);
        ProjAngleXCaption.setTextFill(textColor);
        GridPane.setConstraints(ProjAngleXCaption, 1, 5);
        grid.getChildren().add(ProjAngleXCaption);
 
        ProjAngleX.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
                  ProjAngles[0] = new_val.doubleValue();
                  ProjAngleXValue.setText(String.format("%.0f", new_val));
                  paintPicture();
            }
        });
 
        GridPane.setConstraints(ProjAngleX, 2, 5);
        grid.getChildren().add(ProjAngleX);
 
        ProjAngleXValue.setTextFill(textColor);
        GridPane.setConstraints(ProjAngleXValue, 3, 5);
        grid.getChildren().add(ProjAngleXValue);
        //-------------------------------------------------------------
        customasePerSliderGroup(ProjAngleY);
        ProjAngleYCaption.setTextFill(textColor);
        GridPane.setConstraints(ProjAngleYCaption, 1, 6);
        grid.getChildren().add(ProjAngleYCaption);
 
        ProjAngleY.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
                  ProjAngles[1] = new_val.doubleValue();
                  ProjAngleYValue.setText(String.format("%.0f", new_val));
                  paintPicture();
            }
        });
 
        GridPane.setConstraints(ProjAngleY, 2, 6);
        grid.getChildren().add(ProjAngleY);
 
        ProjAngleYValue.setTextFill(textColor);
        GridPane.setConstraints(ProjAngleYValue, 3, 6);
        grid.getChildren().add(ProjAngleYValue);
        //-------------------------------------------------------------
        customasePerSliderGroup(ProjAngleZ);
        ProjAngleZCaption.setTextFill(textColor);
        GridPane.setConstraints(ProjAngleZCaption, 1, 7);
        grid.getChildren().add(ProjAngleZCaption);
 
        ProjAngleZ.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                Number old_val, Number new_val) {
                  ProjAngles[2] = new_val.doubleValue();
                  ProjAngleZValue.setText(String.format("%.0f", new_val));
                  paintPicture();
            }
        });
 
        GridPane.setConstraints(ProjAngleZ, 2, 7);
        grid.getChildren().add(ProjAngleZ);
 
        ProjAngleZValue.setTextFill(textColor);
        GridPane.setConstraints(ProjAngleZValue, 3, 7);
        grid.getChildren().add(ProjAngleZValue);
        
        //-------------------------------------------------------------
        scene.setRoot(grid);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void customaseSliderGroup(Slider slider) {
        slider.setMin(-180);
        slider.setMax(180);
        slider.setValue(0);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(15);
        slider.setMinorTickCount(5);
        slider.setBlockIncrement(10);    
    }
    
    private void customasePerSliderGroup(Slider slider) {
        slider.setMin(-45);
        slider.setMax(45);
        slider.setValue(0);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(15);
        slider.setMinorTickCount(5);
        slider.setBlockIncrement(10);    
    }

    private void paintPicture(){
        
        Point2D[] points = Projection.getPoints(MyCube, ProjAngles,  Angles, ViewPoint, display);
        int[][]   lines = Projection.getLines(MyCube);
        
        GraphicsContext graphicsContext2D = canvas.getGraphicsContext2D();
        graphicsContext2D.setFill(Color.valueOf("#ffffff"));
        graphicsContext2D.fillRect(0, 0, YSize, XSize);
        
        graphicsContext2D.setStroke(Color.BLACK);
        graphicsContext2D.setLineWidth(1);
        graphicsContext2D.setLineDashes(2);
        
        graphicsContext2D.strokeLine(1, 1, XSize -1, 1);            
        graphicsContext2D.strokeLine(XSize -1, 1, XSize -1, YSize -1);            
        graphicsContext2D.strokeLine(XSize -1, YSize -1, 1, YSize -1);            
        graphicsContext2D.strokeLine(1, YSize -1, 1, 1);            
        
        double dx = XSize/2;
        double dy = YSize/2;
        
        graphicsContext2D.setStroke(Color.BLUE);
        graphicsContext2D.setLineWidth(1);
        graphicsContext2D.setLineDashes(2);
        double scalefactor = 4;
        for (int i = 0; i < lines.length; i++) {
            graphicsContext2D.strokeLine(scalefactor * points[lines[i][0]].x + dx, scalefactor * points[lines[i][0]].y + dy, scalefactor * points[lines[i][1]].x + dx, scalefactor * points[lines[i][1]].y + dy);            
        }
        
        graphicsContext2D.setFill(Color.valueOf("#ff0000"));
        for (int i = 0; i < points.length; i++) {
           graphicsContext2D.fillOval(scalefactor * (points[i].x-1) + dx, scalefactor * (points[i].y-1) + dy,scalefactor * 2, scalefactor * 2);
        }
        
    
    } 
}