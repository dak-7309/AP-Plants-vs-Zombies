package sample;

public class Zombie extends Character
{
    private double Damage;
    private String Type;
    private boolean Blocked;
    public Zombie(double H, double x, double y, String type,double d) { super(H,x,y); Type=type; Damage = d; Blocked = false;}
    public String getType() { return Type; }
    public void setType(String type) { Type = type; }
    public double getDamage() { return Damage; }
    public boolean isBlocked() { return Blocked; }
    public void setBlocked(boolean blocked) { Blocked = blocked; }
    public void Eat(){}
}
