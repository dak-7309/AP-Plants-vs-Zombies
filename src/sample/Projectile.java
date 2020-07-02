package sample;


import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;

public class Projectile
{
    private pair Position;
    private boolean Hit;
    private ImageView B;

    public Projectile(pair position)
    { Position = position; Hit = false; B= new ImageView("sample/Resources/pea.png"); B.setX(position.getX()+50); B.setY(position.getY()+25); B.setFitHeight(20); B.setFitWidth(20); B.setEffect(new Glow(1));}

    public pair getPosition() { return Position; }
    public void setPosition(pair position) { Position = position; }
    public boolean isHit() { return Hit; }
    public void setHit(boolean hit) { Hit = hit; }
    public ImageView getB() { return B; }
}
