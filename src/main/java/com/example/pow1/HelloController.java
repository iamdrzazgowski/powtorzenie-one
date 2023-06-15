package com.example.pow1;

import com.example.pow1.client.ServerThread;
import com.example.pow1.server.ClientThread;
import com.example.pow1.server.Server;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.Socket;
import java.util.function.Consumer;

public class HelloController {

    @FXML
    public Canvas canvas;
    @FXML
    public TextField addressField;
    @FXML
    public TextField portField;
    @FXML
    public ColorPicker colorPicker;
    @FXML
    public Slider radiusSlider;

    public Server server;
    public ServerThread serverThread;
    private Consumer<Dot> dotConsumer;
    @FXML
    public void onMouseClicked(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        double radius = radiusSlider.getValue();
        Color color = colorPicker.getValue();

//        drawCircle(x,y,radius,color);
        Dot dot = new Dot(x,y,color,radius);

    }

    public HelloController(){
        dotConsumer=this::drawCircle;
        server = new Server(5000);
        serverThread = new ServerThread("localhost",5000);
    }

    private void drawCircle(Dot dot) {
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setFill(dot.color());
        graphicsContext.fillOval(dot.x() - dot.radius(),dot.y()- dot.radius(),2* dot.radius(),2*dot.radius());
    }

    private void setDotConsumer(Consumer<Dot> dotConsumer){
        this.dotConsumer=dotConsumer;
    }

    @FXML
    public void onStartServerClicked(ActionEvent actionEvent) {
        // Implementacja logiki dla przycisku "Start Server & Connect"
        // Ta metoda zostanie wywołana po kliknięciu na ten przycisk

    }

    public ServerThread connect(String name, int port){
        return new ServerThread(name, port);
    }

    @FXML
    public void onConnectClicked(ActionEvent actionEvent) {
        // Implementacja logiki dla przycisku "Connect"
        // Ta metoda zostanie wywołana po kliknięciu na ten przycisk
    }
}