package sample;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import java.io.IOException;

public class PauseMenuController extends Click
{
    @FXML
    public void MusicSet(MouseEvent mouseEvent) { Main.MusicToggle = !Main.MusicToggle; Main.Music(); }

    @FXML
    public void RestartGame(MouseEvent mouseEvent) throws IOException { Main.setRoot_Game();}

    @FXML
    public void SaveGame(MouseEvent mouseEvent) throws IOException { Main.setRoot_Main_Menu(); }

    @FXML
    public void Back(MouseEvent mouseEvent) throws IOException { Main.setRoot_Game(); }

    @FXML
    public void Exit(javafx.scene.input.MouseEvent mouseEvent) { System.exit(0); }
}
