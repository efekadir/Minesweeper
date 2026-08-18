package UI;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class Mine extends Pane {

    public Mine() {

        // Mayının ana gövdesi
        Circle body = new Circle(22.5, 22.5, 9);
        body.setFill(Color.BLACK);

        // Dikenler
        Line vertical = new Line(22.5, 7, 22.5, 38);
        Line horizontal = new Line(7, 22.5, 38, 22.5);

        Line diagonal1 = new Line(11, 11, 34, 34);
        Line diagonal2 = new Line(34, 11, 11, 34);

        vertical.setStroke(Color.BLACK);
        horizontal.setStroke(Color.BLACK);
        diagonal1.setStroke(Color.BLACK);
        diagonal2.setStroke(Color.BLACK);

        vertical.setStrokeWidth(4);
        horizontal.setStrokeWidth(4);
        diagonal1.setStrokeWidth(4);
        diagonal2.setStrokeWidth(4);

        // Küçük parlaklık
        Circle shine = new Circle(19, 19, 3);
        shine.setFill(Color.WHITE);

        getChildren().addAll(
            vertical,
            horizontal,
            diagonal1,
            diagonal2,
            body,
            shine
        );

        setPrefSize(45, 45);
        setMinSize(45, 45);
        setMaxSize(45, 45);

        setMouseTransparent(true);
        setVisible(false);
    }
}