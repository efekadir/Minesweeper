package UI;

import GameManagement.Config;
import GameManagement.GameSettings;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.control.Button;

public class SettingsScreen extends StackPane {

    private static final double WIDTH = 500;
    private static final double HEIGHT = 400;

    private static final String NORMAL_STYLE =
            "-fx-background-color: #d0d0d0;" +
            "-fx-border-color: #ffffff #808080 #808080 #ffffff;" +
            "-fx-border-width: 3px;" +
            "-fx-font-size: 16px;";

    private static final String HOVER_STYLE =
            "-fx-background-color: #dddddd;" +
            "-fx-border-color: #ffffff #909090 #909090 #ffffff;" +
            "-fx-border-width: 3px;" +
            "-fx-font-size: 16px;";

    private static final String SELECTED_STYLE =
            "-fx-background-color: #a8a8a8;" +
            "-fx-border-color: #707070;" +
            "-fx-border-width: 2px;" +
            "-fx-font-size: 16px;";

    private static final String CLOSE_STYLE =
            "-fx-background-color: #c8c8c8;" +
            "-fx-border-color: #ffffff #808080 #808080 #ffffff;" +
            "-fx-border-width: 3px;" +
            "-fx-font-size: 16px;";

    private Button selectedButton;
    private GameSettings selectedSettings;

    public SettingsScreen() {

        Rectangle background = new Rectangle(WIDTH, HEIGHT);
        background.setArcWidth(25);
        background.setArcHeight(25);

        background.setFill(
                Color.rgb(236, 233, 227, 0.98)
        );

        background.setStroke(
                Color.rgb(190, 190, 190)
        );

        background.setStrokeWidth(2);

        // Başlık
        Label title = new Label("SETTINGS");
        title.setFont(Font.font("Arial", 32));
        title.setTextFill(Color.rgb(60, 60, 60));

        // Stage butonları
        VBox stages = new VBox(12);
        stages.setAlignment(Pos.CENTER);

        Button stage1Button = createStageButton(
                "STAGE 1",
                Config.STAGE1_ROWS,
                Config.STAGE1_COLUMNS,
                Config.STAGE1_BOMB_COUNT,
                Config.STAGE1_CELL_SIZE
        );

        Button stage2Button = createStageButton(
                "STAGE 2",
                Config.STAGE2_ROWS,
                Config.STAGE2_COLUMNS,
                Config.STAGE2_BOMB_COUNT,
                Config.STAGE2_CELL_SIZE
        );

        Button stage3Button = createStageButton(
                "STAGE 3",
                Config.STAGE3_ROWS,
                Config.STAGE3_COLUMNS,
                Config.STAGE3_BOMB_COUNT,
                Config.STAGE3_CELL_SIZE
        );

        stages.getChildren().addAll(
                stage1Button,
                stage2Button,
                stage3Button
        );

        // Başlangıçta Stage 1 seçili
        selectStage(stage1Button);

        selectedSettings = new GameSettings(
                Config.STAGE1_ROWS,
                Config.STAGE1_COLUMNS,
                Config.STAGE1_BOMB_COUNT,
                Config.STAGE1_CELL_SIZE
        );

        // CLOSE butonu
        Button closeButton = new Button("CLOSE");

        closeButton.setPrefSize(120, 40);
        closeButton.setStyle(CLOSE_STYLE);

        closeButton.setOnMouseEntered(e ->
                closeButton.setStyle(HOVER_STYLE)
        );

        closeButton.setOnMouseExited(e ->
                closeButton.setStyle(CLOSE_STYLE)
        );

        closeButton.setOnAction(e ->
                setVisible(false)
        );

        // İçerik
        VBox content = new VBox(20);

        content.getChildren().addAll(
                title,
                stages,
                closeButton
        );

        content.setAlignment(Pos.CENTER);

        // Arka plan + içerik
        StackPane settingsBox = new StackPane();

        settingsBox.getChildren().addAll(
                background,
                content
        );

        settingsBox.setPrefSize(
                WIDTH,
                HEIGHT
        );

        getChildren().add(settingsBox);

        setAlignment(
                settingsBox,
                Pos.CENTER
        );

        // Başlangıçta görünmez
        setVisible(false);
    }

    private Button createStageButton(
            String stageName,
            int rows,
            int columns,
            int bombCount,
            int cellSize) {

        // Stage adı
        Label stageLabel = new Label(stageName);
        stageLabel.setFont(Font.font("Arial", 18));
        stageLabel.setTextFill(Color.rgb(60, 60, 60));

        // Boyut
        Label sizeLabel = new Label(
                rows + " × " + columns
        );

        sizeLabel.setFont(Font.font("Arial", 18));
        sizeLabel.setTextFill(Color.rgb(60, 60, 60));

        // Mayın
        Mine mine = new Mine();
        mine.setVisible(true);
        mine.setMouseTransparent(true);

        mine.setScaleX(0.6);
        mine.setScaleY(0.6);

        // Mayın sayısı
        Label bombLabel = new Label(
                "x" + bombCount
        );

        bombLabel.setFont(Font.font("Arial", 18));
        bombLabel.setTextFill(Color.rgb(60, 60, 60));

        // Mayın + sayı
        HBox bombBox = new HBox(5);

        bombBox.getChildren().addAll(
                mine,
                bombLabel
        );

        bombBox.setAlignment(Pos.CENTER);

        // Butonun içeriği
        HBox content = new HBox(45);

        content.getChildren().addAll(
                stageLabel,
                sizeLabel,
                bombBox
        );

        content.setAlignment(Pos.CENTER);

        // Stage butonu
        Button stageButton = new Button();

        stageButton.setGraphic(content);

        stageButton.setPrefSize(380, 55);
        stageButton.setMinSize(380, 55);
        stageButton.setMaxSize(380, 55);

        stageButton.setStyle(NORMAL_STYLE);

        // Hover
        stageButton.setOnMouseEntered(e -> {

            if (stageButton != selectedButton) {
                stageButton.setStyle(HOVER_STYLE);
            }
        });

        stageButton.setOnMouseExited(e -> {

            if (stageButton != selectedButton) {
                stageButton.setStyle(NORMAL_STYLE);
            }
        });

        // Stage seçimi
        stageButton.setOnAction(e -> {

            selectStage(stageButton);

            selectedSettings = new GameSettings(
                    rows,
                    columns,
                    bombCount,
                    cellSize
            );

            System.out.println(
                    stageName + " selected"
            );
        });

        return stageButton;
    }

    private void selectStage(Button button) {

        // Önceki seçimi normale döndür
        if (selectedButton != null) {
            selectedButton.setStyle(NORMAL_STYLE);
        }

        // Yeni seçimi işaretle
        selectedButton = button;

        selectedButton.setStyle(SELECTED_STYLE);
    }

    public GameSettings getSelectedSettings() {
        return selectedSettings;
    }
}