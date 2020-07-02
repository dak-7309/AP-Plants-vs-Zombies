package sample;

import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class GameController extends Click
{
    @FXML
    public AnchorPane BG;
    public GameHelper G;
    public ImageView SelectShovel;

    public GameController() { }

    @FXML
    public void Pause(MouseEvent mouseEvent) throws IOException { Main.setRoot_PauseMenu(); }

    @FXML
    public void Place(MouseEvent mouseEvent)
    { ImageView Tile = (ImageView) mouseEvent.getSource();G.I_Plant(Tile); }

    public void StartGame(int x,int y,int c, int h)
    { G = new GameHelper(x,y,c,h,this); G.Process(); }

    public void Dig(MouseEvent mouseEvent) { G.ShovelSelected=true; Place(mouseEvent);}
}