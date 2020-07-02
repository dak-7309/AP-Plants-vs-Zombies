package sample;

public class SunToken
{
    private pair Position;
    private boolean Active;
    public SunToken(pair position) { Position = position; Active = true; }
    public pair getPosition() { return Position; }
    public void setPosition(pair position) { Position = position; }
    public boolean isActive() { return Active; }
    public void setActive(boolean active) { Active = active; }
}
