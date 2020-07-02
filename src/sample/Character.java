package sample;

public abstract class Character
{
    private double Health;
    private pair Position;
    private boolean Active;
    public Character(double H,double x, double y) { Health = H ; Position = new pair(x,y); Active = true;}
    public double getHealth() { return Health; }
    public void setHealth(double health) { Health = health; }
    public pair getPosition() { return Position; }
    public boolean isActive() { return Active; }
    public void setActive(boolean active) { Active = active; }
}
