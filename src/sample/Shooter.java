package sample;

public class Shooter extends Plant
{
    private Projectile P ;
    public Shooter(double H, double x, double y, String type,double c) { super(H,x,y,type,c);  P = new Projectile(getPosition()); }
    public Projectile getP() {return P; }
    public void Shoot(){}
}
