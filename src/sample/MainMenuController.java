package sample;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class MainMenuController extends Click
{
    @FXML
    public void NewGame(MouseEvent mouseEvent) throws IOException { Main.setRoot_Game(); }

    @FXML
    public void LoadGame(MouseEvent mouseEvent) throws IOException { Main.setRoot_Load(); }

    @FXML
    public void Help(MouseEvent mouseEvent) throws IOException { Main.setRoot_Help(); }

    @FXML
    public void LeaderBoard(MouseEvent mouseEvent) throws IOException { Main.setRoot_LeaderBoard(); }

    @FXML
    public void Exit(javafx.scene.input.MouseEvent mouseEvent) { System.exit(0); }

    @FXML
    public void LevelMenu(MouseEvent mouseEvent) throws IOException { Main.setRoot_Level(); }
}
