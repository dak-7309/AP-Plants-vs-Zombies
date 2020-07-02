package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class Main extends Application
{
    private static Stage stage;
    private static MediaPlayer SoundPlayer;
    public static boolean MusicToggle  = true;
    public static int Level=5;

    private static  void LoadMusic()
    {
        try
        {
            File audioFile = new File("GameSound.mp3");
            Media Sound = new Media(audioFile.toURI().toURL().toString());
            SoundPlayer = new MediaPlayer(Sound);
            SoundPlayer.setOnEndOfMedia(() -> SoundPlayer.seek(Duration.ZERO));
        }
        catch (MalformedURLException e)
        { System.out.println("No Audio File Found"); }
    }

    public static void Music() { if (MusicToggle) {SoundPlayer.play();} else {SoundPlayer.pause();} }

    @Override
    public void start(Stage primaryStage) throws  IOException
    {
        stage = primaryStage;
        stage.setScene(new Scene(FXMLLoader.load(Main.class.getResource("MainMenu.fxml"))));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        LoadMusic();
        stage.show();
        stage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {if (KeyCode.ESCAPE == event.getCode()) {primaryStage.close();}});
    }

    public static void setRoot_Main_Menu() throws IOException { stage.setScene(new Scene(FXMLLoader.load(Main.class.getResource("MainMenu.fxml"))));stage.show();}
    public static void setRoot_Game() throws IOException
    {
        FXMLLoader L = new FXMLLoader(Main.class.getResource("Game.fxml"));
        Parent R = (Parent) L.load();
        GameController GC = L.getController();
        stage.setScene(new Scene(R));stage.show();Main.Music();
        if(Level==1) GC.StartGame(1,5,10,100);
        else if(Level==2) GC.StartGame(2,5,20,100);
        else if(Level==3) GC.StartGame(3,10,30,100);
        else if(Level==4) GC.StartGame(4,15,40,100);
        else if(Level==5) GC.StartGame(5,15,50,100);
    }
    public static void setRoot_PauseMenu() throws IOException {stage.setScene(new Scene(FXMLLoader.load(Main.class.getResource("PauseMenu.fxml"))));stage.show(); }
    public static void  setRoot_LeaderBoard() throws IOException {stage.setScene(new Scene(FXMLLoader.load(Main.class.getResource("LeaderBoard.fxml"))));stage.show(); }
    public static void setRoot_Help() throws IOException {stage.setScene(new Scene(FXMLLoader.load(Main.class.getResource("Help.fxml"))));stage.show(); }
    public static void setRoot_Level() throws IOException {stage.setScene(new Scene(FXMLLoader.load(Main.class.getResource("LevelMenu.fxml"))));stage.show(); }
    public static void setRoot_Load() throws IOException {stage.setScene(new Scene(FXMLLoader.load(Main.class.getResource("LoadGame.fxml"))));stage.show(); }
    public static void setRoot_GO() throws IOException {stage.setScene(new Scene(FXMLLoader.load(Main.class.getResource("GameOver.fxml"))));stage.show(); }
    public static void setRoot_GW() throws IOException {stage.setScene(new Scene(FXMLLoader.load(Main.class.getResource("Won.fxml"))));stage.show(); }
    public static void setRoot_NL() throws IOException {stage.setScene(new Scene(FXMLLoader.load(Main.class.getResource("NextLevel.fxml"))));stage.show(); }
    public static Stage getStage() { return stage; }
    public static void main(String[] args) { launch(args); }

}
