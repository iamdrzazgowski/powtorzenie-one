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
    public void onMouseClicked(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        double radius = radiusSlider.getValue();
        Color color = colorPicker.getValue();

        drawCircle(x,y,radius,color);

    }

    private void drawCircle(double x, double y, double radius, Color color) {
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setFill(color);
        graphicsContext.fillOval(x - radius,y - radius,radius * 2 ,radius * 2);
    }

    public void onStartServerClicked(ActionEvent actionEvent) {
        server = new Server(Integer.valueOf(portField.getText()));
        try {
            System.out.println("Connected");
            server.listen();

            serverThread = new ServerThread(addressField.getText(), Integer.valueOf(portField.getText()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onConnectClicked(ActionEvent actionEvent) {
    }
}