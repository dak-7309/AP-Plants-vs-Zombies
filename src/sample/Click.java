package sample;

import javafx.scene.effect.Glow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.io.File;
import java.net.MalformedURLException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class Click
{
    private  boolean SoundCheck;
    private  MediaPlayer SoundPlayer;

    public Click()
    {
        SoundCheck = false;
        try
        {
            File audioFile = new File("ClickSound.mp3");
            Media Sound = new Media(audioFile.toURI().toURL().toString());
            SoundPlayer = new MediaPlayer(Sound);
            SetupSoundPlayer();
         }
        catch (MalformedURLException e)
        { System.out.println("No Audio File Found"); }
    }

    private void SetupSoundPlayer()
    {
        SoundPlayer.setStartTime(new Duration(1000));
        SoundPlayer.setStopTime(new Duration((1100)));
        SoundPlayer.setOnEndOfMedia(() -> SoundPlayer.stop());
    }

    private void PlaySound() { SoundPlayer.play(); }

    public void Glow(MouseEvent mouseEvent)
    {
        if (!SoundCheck){ PlaySound(); SoundCheck = true;}
        Glow G = new Glow(0.75);
        ((ImageView) mouseEvent.getSource()).setEffect(G);
    }

    public void RemoveGlow(MouseEvent mouseEvent)
    {
        InnerShadow IS = new InnerShadow();
        ((ImageView) mouseEvent.getSource()).setEffect(IS);
        SoundCheck = false;
    }
}
