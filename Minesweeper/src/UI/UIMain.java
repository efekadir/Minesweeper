package UI;

import javafx.application.Application;
import javafx.stage.Stage;

public class UIMain extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            primaryStage.setTitle("Minesweeper");
            
            MainMenuScreen mainMenu = new MainMenuScreen(primaryStage);
            primaryStage.setScene(mainMenu.getScene());
            primaryStage.show();
            
        } catch (Exception e) {
            System.out.println("Oyun başlatılırken bir hata oluştu!");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}