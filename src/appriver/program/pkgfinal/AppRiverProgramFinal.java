/*
 * This will be the main screen for the program.
 */
package appriver.program.pkgfinal;

//import git clone:https://github.com/Gaara23/AppRiver-Data.git;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
//import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author Squidward5
 */
public class AppRiverProgramFinal extends Application
{
    
    
    //@PersistenceContext
    EntityManagerFactory emf;
    @Override
    public void start(Stage primaryStage)
    {
        final String JDBC_Driver= "apache_derby_net";
        final String JDBC_URL= "jdbc://localhost:1527/DataRiver";
        
        Information info = new Information("374b0570-aba5-4eee-8141-19af2d4a8a49");
        EntityManager em = emf.createEntityManager();
        InformationJpaController test= new InformationJpaController(emf);
        try
        {
            test.create(info);
        } catch (Exception ex)
        {
            Logger.getLogger(AppRiverProgramFinal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Information OriginCountry= test.findInformation();
        System.out.println(OriginCountry.getOriginalcountry());
        
        Button pieBtn= new Button();
        Button lineBtn= new Button();
        Button barBtn= new Button();
        pieBtn.setText("Pie Chart");
        pieBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            
            @Override
            public void handle(ActionEvent event)
            {
                Scene scene = new Scene(new Group());
                Stage stage = new Stage();
                
                
                ObservableList<PieChart.Data> pieChartData = 
                        FXCollections.observableArrayList(
                        new PieChart.Data("MSGCLASS", 13),
                        new PieChart.Data("TIMES", 10),
                        new PieChart.Data("DATES", 15));
                final PieChart chart = new PieChart(pieChartData);
                chart.setTitle("Spam Catalogs");
                
                ((Group) scene.getRoot()).getChildren().add(chart);
                stage.setScene(scene);
                stage.show();
            }
        });
            
        lineBtn.setText("Line Chart");
        lineBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            
            @Override
            public void handle(ActionEvent event)
            {
                Stage stage = new Stage();
                
                stage.setTitle("Spam Catalog");
                //defining the axes
                final NumberAxis xAxis = new NumberAxis();
                final NumberAxis yAxis = new NumberAxis();
                xAxis.setLabel("Countries");
                final LineChart<Number,Number> lineChart = 
                new LineChart<Number,Number>(xAxis,yAxis);
                
                lineChart.setTitle("Spam Catalog");
                //defining a series
                XYChart.Series series = new XYChart.Series();
                series.setName("AppRiver");
                //populating the series with data
                series.getData().add(new XYChart.Data(1, 23));
                series.getData().add(new XYChart.Data(2, 14));
                series.getData().add(new XYChart.Data(3, 15));
                series.getData().add(new XYChart.Data(4, 24));
                series.getData().add(new XYChart.Data(5, 34));
                series.getData().add(new XYChart.Data(6, 36));
                series.getData().add(new XYChart.Data(7, 22));
                series.getData().add(new XYChart.Data(8, 45));
                series.getData().add(new XYChart.Data(9, 43));
                series.getData().add(new XYChart.Data(10, 17));
                series.getData().add(new XYChart.Data(11, 29));
                series.getData().add(new XYChart.Data(12, 25));
        
                Scene scene1  = new Scene(lineChart,800,600);
                lineChart.getData().add(series);
                       
                stage.setScene(scene1);
                stage.show();
            }
        });
            
        /*barBtn.setText("Bar Chart");
        barBtn.setOnAction(new EventHandler<ActionEvent>()
        {
            
            @Override
            public void handle(ActionEvent event)
            {
                Scene scene = new Scene(new Group());
                Stage stage = new Stage();
                
                
                ObservableList<PieChart.Data> pieChartData = 
                        FXCollections.observableArrayList(
                        new PieChart.Data("MSGCLASS", 13),
                        new PieChart.Data("TIMES", 10),
                        new PieChart.Data("DATES", 15));
                final PieChart chart = new PieChart(pieChartData);
                chart.setTitle("Spam Catalogs");
                
                ((Group) scene.getRoot()).getChildren().add(chart);
                stage.setScene(scene);
                stage.show();
            }
        });*/
        
        VBox root = new VBox();
        //StackPane root1= new StackPane();
        root.getChildren().add(pieBtn);
        root.getChildren().add(lineBtn);
        
        Scene scene = new Scene(root, 300, 250);
        //Scene scene1= new Scene(root1, 300, 250);
        
        primaryStage.setTitle("AppRiver Data");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        
        launch(args);
    }
    
}
