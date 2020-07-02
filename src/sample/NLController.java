package sample;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class NLController extends Click

{
    @FXML
    public void Back(MouseEvent mouseEvent) throws IOException { Main.setRoot_Game(); }
}
