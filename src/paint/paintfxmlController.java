/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint;

import javafx.scene.canvas.Canvas;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Thiellen_Oliveira
 */
public class paintfxmlController implements Initializable {
    
    @FXML
    private ColorPicker colorpicker;    
    
    @FXML
    private TextField bsize;
    
    @FXML
    private Canvas canvas;            
    
    boolean toolSelected = false;
    
    GraphicsContext brushTool;    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        brushTool = canvas.getGraphicsContext2D(); 
        canvas.setOnMouseDragged(e ->{  
            double size = Double.parseDouble(bsize.getText());
            double x = e.getX() - size / 2;
            double y = e.getY() - size / 2;
            
            if(toolSelected &&!bsize.getText().isEmpty());
            brushTool.setFill(colorpicker.getValue());
            brushTool.fillRoundRect(x, y, size, size, size, size);
        });
    
    }
    
    @FXML
    public void newcanvas(ActionEvent e){
        TextField getCanvasWidth = new TextField();
        getCanvasWidth.setPromptText("width");
        getCanvasWidth.setPrefWidth(150);
        getCanvasWidth.setAlignment(Pos.CENTER);
        
        TextField getCanvasheight = new TextField();
        getCanvasheight.setPromptText("height");
        getCanvasheight.setPrefWidth(150);
        getCanvasheight.setAlignment(Pos.CENTER);
        
        Button createButton = new Button();
        createButton.setText("Create Canvas");
        
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(getCanvasWidth, getCanvasheight, createButton);
        
        Stage createStage = new Stage();
        AnchorPane root = new AnchorPane();
        root.setPrefWidth(200);
        root.setPrefHeight(200);
        root.getChildren().add(vbox);
        
        Scene CanvasScene = new Scene(root);
        createStage.setTitle("Create Canvas");
        createStage.setScene(CanvasScene);
        createStage.show();
        
        createButton.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                double canvasWidthReceived = Double.parseDouble(getCanvasWidth.getText());
                double canvasHeightReceived = Double.parseDouble(getCanvasheight.getText());
                canvas = new Canvas();
                canvas.setWidth(canvasWidthReceived);
                canvas.setHeight(canvasHeightReceived);
                vbox.getChildren().add(canvas);
                createStage.close();
            }
        });
            
        }           
        
        
    
    @FXML
    public void toolselected(ActionEvent e){
        toolSelected = true;
    
    }
}
    
