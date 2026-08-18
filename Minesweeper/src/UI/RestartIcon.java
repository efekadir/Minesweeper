package UI;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Polygon;

public class RestartIcon extends Pane {

    public RestartIcon() {

        setPrefSize(40, 40);

        Arc arrow = new Arc(
                16, 20,
                12, 12,
                45, 280
        );

        arrow.setFill(null);
        arrow.setStroke(Color.BLACK);
        arrow.setStrokeWidth(3);

        Polygon arrowHead = new Polygon(
                25.0, 10.0,
                25.0, 18.0,
                17.0, 14.0
        );

        arrowHead.setFill(Color.BLACK);

        getChildren().addAll(arrow, arrowHead);

        setMouseTransparent(true);
    }
}