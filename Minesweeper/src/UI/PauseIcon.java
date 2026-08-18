package UI;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PauseIcon extends Pane {

    public PauseIcon() {

        setPrefSize(40, 40);

        Rectangle leftBar = new Rectangle(7, 24);
        leftBar.setFill(Color.BLACK);
        leftBar.setLayoutX(5);
        leftBar.setLayoutY(8);

        Rectangle rightBar = new Rectangle(7, 24);
        rightBar.setFill(Color.BLACK);
        rightBar.setLayoutX(20);
        rightBar.setLayoutY(8);

        getChildren().addAll(leftBar, rightBar);

        setMouseTransparent(true);
    }
}