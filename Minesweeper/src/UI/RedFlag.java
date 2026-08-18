package UI;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class RedFlag extends Pane {

    public RedFlag() {

        // Direk - soldan, kısa
        Line pole = new Line(17, 12, 17, 32);
        pole.setStroke(Color.DARKGRAY);
        pole.setStrokeWidth(3);

        // Büyük kırmızı bayrak
        Polygon flag = new Polygon();
        flag.getPoints().addAll(
            18.0, 11.0,
            36.0, 16.0,
            18.0, 23.0
        );
        flag.setFill(Color.RED);

        // Küçük taban
        Rectangle base = new Rectangle(11, 32, 12, 3);
        base.setFill(Color.DARKGRAY);

        getChildren().addAll(pole, flag, base);

        setPrefSize(45, 45);
        setMinSize(45, 45);
        setMaxSize(45, 45);

        setMouseTransparent(true);
        setVisible(false);
    }
}