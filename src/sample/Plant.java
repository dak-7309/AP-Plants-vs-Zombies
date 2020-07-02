package sample;

public class Plant extends Character
{
    private String Type ;
    public Plant(double H,double x, double y, String s,double c) { super(H, x, y); Type = s;}
    public String getType() { return Type; }
    public void setType(String type) { Type = type; }
}
