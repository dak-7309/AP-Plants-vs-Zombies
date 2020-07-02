package sample;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class GameOverController extends Click
{
    @FXML
    public void Back(MouseEvent mouseEvent) throws IOException { Main.setRoot_Main_Menu(); }
}
