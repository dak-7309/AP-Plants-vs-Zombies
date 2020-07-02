package sample;

public class LawnMower extends Character
{
    boolean Used;
    boolean Running;
    public LawnMower(double x, double y) { super(100,x,y);Used = false; Running=false;}
    public void Use() {Used = true;}
    public boolean isUsed() { return Used; }
    public boolean isRunning() { return Running; }
    public void setRunning(boolean running) { Running = running; }
}
