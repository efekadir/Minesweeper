package UI;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class MainMenuIcon extends Pane {

    public MainMenuIcon() {

        setPrefSize(40, 40);

        Rectangle house = new Rectangle(
                3, 14,
                24, 18
        );
        house.setFill(Color.BLACK);

        Polygon roof = new Polygon(
                -1.0, 14.0,
                15.0, 3.0,
                31.0, 14.0
        );
        roof.setFill(Color.BLACK);

        Rectangle door = new Rectangle(
                12, 22,
                6, 10
        );
        door.setFill(Color.WHITE);

        getChildren().addAll(
                house,
                roof,
                door
        );

        setMouseTransparent(true);
    }
}