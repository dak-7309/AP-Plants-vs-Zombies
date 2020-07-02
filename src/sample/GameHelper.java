package sample;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.TextAlignment;

import java.io.IOException;
import java.security.PublicKey;
import java.util.*;

public class GameHelper
{
    public AnimationTimer T;
    public GameController A;
    public int AZ;
    public HashMap<LawnMower, ImageView> LawnMowers;
    public HashMap<Plant,ImageView> Plants;
    public HashMap<Zombie,ImageView> Zombies;
    public HashMap<SunToken,ImageView> SunTokens;
    public Label STC; public ImageView ZB;
    public ImageView Select_PS, Select_SF, Select_WN, Select_PM, Select_TN, Select_JP;
    public int C_PS,C_SF,C_WN,C_PM,C_TN,C_JP;
    public Glow On,Off;
    public ImageView Selection;
    public boolean ShovelSelected;
    public int L; public double ZS;public int ZC;public int ZH;public int Time;public long PT;

    public GameHelper(int Level,double speed, int count, int health, GameController a)
    {
        T = new AnimationTimer()
        {@Override public void start() { PT = System.nanoTime();super.start();}
        @Override public void handle(long Now) { long ET = Now - PT;Execute();PT = ET; }};
        LawnMowers = new HashMap<>();Plants = new HashMap<>();Zombies = new HashMap<>();SunTokens = new HashMap<>();
        STC = new Label();ZB = new ImageView("sample/Resources/ZombieHead.png");
        ZS = speed;ZC = count;ZH = health;A = a;Time = 0;L = Level;
        Selection = null; ShovelSelected = false;
        C_PS=0;C_SF=0;C_WN=0;C_PM=0;C_TN=0;C_JP=0; AZ=ZC;
        On = new Glow(0.5); Off = new Glow(0);
        I_STC();I_ZB();I_LawnMower();I_Inventory();
    }

    public void I_STC()
    { STC.setLayoutX(50); STC.setLayoutY(85); STC.setPrefHeight(20); STC.setPrefWidth(40); STC.setText("0"); STC.setTextAlignment(TextAlignment.CENTER); A.BG.getChildren().add(STC); }

    public void I_ZB()
    { ZB.setX(1230); ZB.setY(20); ZB.setFitHeight(50); ZB.setFitWidth(50);A.BG.getChildren().add(ZB); }

    public void I_LawnMower()
    {
        for(int i=0;i<5;i++)
        {
            ImageView I = new ImageView("sample/Resources/lawn_mower.gif");
            I.setX(175);I.setY(125+(i*150));I.setFitHeight(100);I.setFitWidth(100);
            A.BG.getChildren().add(I);
            LawnMower O = new LawnMower(175,125+(i*150));
            LawnMowers.put(O,I);
        }
    }

    public void I_SunToken()
    {
        int R = new Random().nextInt(8);
        ImageView I = new ImageView("sample/Resources/sun.gif");
        I.setX(300+(R*110));I.setY(0);I.setFitHeight(75);I.setFitWidth(75);
        A.BG.getChildren().add(I);
        I.setOnMouseClicked(e -> I.setOpacity(0));
        SunToken O = new SunToken(new pair(300+(R*110),0));
        SunTokens.put(O,I);
    }

    public void I_Zombie()
    {
        if (ZC>0)
        {
            ImageView I;
            Zombie O;
            int R = new Random().nextInt(5);
            int C = new Random().nextInt((int) Math.floorDiv(L,3)+1);
            if(C==0){ I = new ImageView("sample/Resources/zombie_normal.gif");}
            else { I = new ImageView("sample/Resources/zombie_football.gif");}
            I.setX(1440);I.setY(125+(R*150));I.setFitHeight(100);I.setFitWidth(100);I.setPreserveRatio(true);
            A.BG.getChildren().add(I);
            if (C==0){O = new Zombie(ZH,1440,(125+(R*150)),"Normal",10);}
            else {O = new Zombie(ZH,1440,(125+(R*150)),"Football",10);}
            Zombies.put(O,I);
            ZC--;
        }
    }

    public void I_Inventory()
    {
        Select_PS = new ImageView("sample/Resources/active_peashooter.png");Select_PS.setFitHeight(80); Select_PS.setFitWidth(80); Select_PS.setX(270); Select_PS.setY(10);Select_PS.setId("100"); Select_PS.setEffect(Off); A.BG.getChildren().add(Select_PS);
        Select_PS.setOnMouseClicked(this::Move_Inventory);
        Select_SF = new ImageView("sample/Resources/active_sunflower.png");Select_SF.setFitHeight(80); Select_SF.setFitWidth(80); Select_SF.setX(360); Select_SF.setY(10);Select_SF.setId("200"); Select_SF.setEffect(Off);A.BG.getChildren().add(Select_SF);
        Select_SF.setOnMouseClicked(this::Move_Inventory);
        if(L>1)
        {
            Select_WN = new ImageView("sample/Resources/active_wallnut.png");Select_WN.setFitHeight(80); Select_WN.setFitWidth(80); Select_WN.setX(450); Select_WN.setY(10);Select_WN.setId("50"); Select_WN.setEffect(Off);A.BG.getChildren().add(Select_WN);
            Select_WN.setOnMouseClicked(this::Move_Inventory);
        }
        if(L>2)
        {
            Select_PM = new ImageView("sample/Resources/active_potatomine.png");Select_PM.setFitHeight(80); Select_PM.setFitWidth(80); Select_PM.setX(540); Select_PM.setY(10);Select_PM.setId("150"); Select_PM.setEffect(Off);A.BG.getChildren().add(Select_PM);
            Select_PM.setOnMouseClicked(this::Move_Inventory);
        }
        if(L>3)
        {
            Select_TN = new ImageView("sample/Resources/active_tallnut.png");Select_TN.setFitHeight(80); Select_TN.setFitWidth(80); Select_TN.setX(630); Select_TN.setY(10);Select_TN.setId("250"); Select_TN.setEffect(Off);A.BG.getChildren().add(Select_TN);
            Select_TN.setOnMouseClicked(this::Move_Inventory);
        }
        if(L>4)
        {
            Select_JP = new ImageView("sample/Resources/active_jalepeno.png");Select_JP.setFitHeight(80); Select_JP.setFitWidth(80); Select_JP.setX(720); Select_JP.setY(10);Select_JP.setId("300"); Select_JP.setEffect(Off);A.BG.getChildren().add(Select_JP);
            Select_JP.setOnMouseClicked(this::Move_Inventory);
        }
    }

    public void I_Plant(ImageView Tile)
    {
        if (Selection!=null)
        {
            if ((!ShovelSelected)&&(Tile.getImage()==null))
            {
                double x = Tile.getLayoutX(); double y = Tile.getLayoutY();
                Tile.setImage(Selection.getImage());
                Tile.setFitWidth(75); Tile.setFitHeight(75); Tile.setPreserveRatio(true);
                if(Selection.getId().equals("PS"))
                { Shooter O = new Shooter(100,x+275,y+140,"Shooter",100); A.BG.getChildren().add(O.getP().getB());  Plants.put(O,Tile);}
                else  if(Selection.getId().equals("SF"))
                {SunTokenGenerator O = new SunTokenGenerator(100,x+275,y+140,"SunTokenGenerator",100); Plants.put(O,Tile);}
                else  if(Selection.getId().equals("WN"))
                {Barrier O = new Barrier(100,x+275,y+140,"Wallnut",100); Plants.put(O,Tile);}
                else  if(Selection.getId().equals("PM"))
                {Bomb O = new Bomb(100,x+275,y+140,"PotatoMine",100); Plants.put(O,Tile);}
                else  if(Selection.getId().equals("TN"))
                {Barrier O = new Barrier(200,x+275,y+140,"Tallnut",100); Plants.put(O,Tile);}
                else  if(Selection.getId().equals("JP"))
                {Bomb O = new Bomb(100,x+275,y+140,"Jalapeno",100); Plants.put(O,Tile);}
            }
            Selection = null;
        }
        else if((ShovelSelected)&&(!(Tile.getId()).equals("SelectShovel")))
        {
            for(Map.Entry<Plant,ImageView> P : Plants.entrySet())
            {
                Plant PO = P.getKey();
                ImageView PI = P.getValue();
                if(PI.equals(Tile))
                {
                    if(PO.getClass().getName().equals("sample.Shooter")) {A.BG.getChildren().remove(((Shooter)PO).getP().getB());}
                    PO.setActive(false);Tile.setImage(null); Tile.setFitHeight(150); Tile.setFitWidth(110);
                }
            }
        }
    }

    public void Move_ZB()
    { if (ZB.getX()>950) { ZB.setX(ZB.getX()-(ZS*0.005));} else {} } // Write Game Winner Exception

    public void Move_Inventory(MouseEvent e)
    {
        ImageView I = (ImageView) e.getSource();
        if(!I.getId().equals("SelectShovel")){ ShovelSelected = false; }
        if ( (I.getId().equals("100"))&& (I.getEffect().equals(On)) ) { Selection = new ImageView("sample/Resources/pea_shooter.gif"); Selection.setId("PS");  I.setEffect(Off); Use_SunToken(100);C_PS=0;}
        else if((I.getId().equals("200"))&& (I.getEffect().equals(On))) {Selection = new ImageView("sample/Resources/sun_flower.gif"); Selection.setId("SF");  I.setEffect(Off); Use_SunToken(200);C_SF=0;}
        else if((I.getId().equals("50"))&& (I.getEffect().equals(On))) {Selection = new ImageView("sample/Resources/wallnut.gif"); Selection.setId("WN");  I.setEffect(Off); Use_SunToken(50);C_WN=0;}
        else if((I.getId().equals("150"))&& (I.getEffect().equals(On))) {Selection = new ImageView("sample/Resources/PotatoMine_Activated.gif"); Selection.setId("PM");  I.setEffect(Off); Use_SunToken(150);C_PM=0;}
        else if((I.getId().equals("250"))&& (I.getEffect().equals(On))) {Selection = new ImageView("sample/Resources/tallnut.gif"); Selection.setId("TN");  I.setEffect(Off); Use_SunToken(250);C_TN=0;}
        else if((I.getId().equals("300"))&& (I.getEffect().equals(On))) {Selection = new ImageView("sample/Resources/jalapeno.gif"); Selection.setId("JP");  I.setEffect(Off); Use_SunToken(300);C_JP=0;}
    }

    public void Manage_Inventory()
    {
        if ((Select_PS!=null)&&(C_PS > 500) && ( 100 <= Integer.parseInt(STC.getText()) ))
        {Select_PS.setEffect(On);} else if ((Select_PS!=null)) {Select_PS.setEffect(Off);}
        if ((Select_SF!=null)&&(C_SF > 500) && ( 200 <= Integer.parseInt(STC.getText()) ))
        {Select_SF.setEffect(On);} else if (Select_SF!=null) {Select_SF.setEffect(Off);}
        if ((Select_WN!=null)&&(C_WN > 500) && ( 50 <= Integer.parseInt(STC.getText()) ))
        {Select_WN.setEffect(On);} else if (Select_WN!=null) {Select_WN.setEffect(Off);}
        if ((Select_PM!=null)&&(C_PM > 500) && ( 150 <= Integer.parseInt(STC.getText()) ))
        {Select_PM.setEffect(On);} else if (Select_PM!=null){Select_PM.setEffect(Off);}
        if ((Select_TN!=null)&&(C_TN > 500) && ( 250 <= Integer.parseInt(STC.getText()) ))
        {Select_TN.setEffect(On);} else if (Select_TN!=null){Select_TN.setEffect(Off);}
        if ((Select_JP!=null)&&(C_JP > 500) && ( 300 <= Integer.parseInt(STC.getText()) ))
        {Select_JP.setEffect(On);} else if (Select_JP!=null){Select_JP.setEffect(Off);}
    }

    public void Move_Pea()
    {
        for(Map.Entry<Plant,ImageView> P : Plants.entrySet())
        {
            Plant PO = P.getKey();
            ImageView PI = P.getValue();
            if ((PO!=null)&&(PI!=null))
            {
                if (String.valueOf(PO.getClass().getName()).equals("sample.Shooter"))
                {
                    ImageView I = ((Shooter) PO).getP().getB();
                    if (I.getX()<1440) {I.setX(I.getX()+ZS);}
                    else {I.setX(((Shooter) PO).getP().getPosition().getX()+50); I.setVisible(true);}
                    BZ_Collide(I);
                }
            }
        }
    }

    public void Move_SunToken()
    {
        for (Map.Entry<SunToken,ImageView> S : SunTokens.entrySet())
        {
            SunToken SO = S.getKey();
            ImageView SI = S.getValue();
            if ((SO!=null)&&(SI!=null)&&(SO.isActive()))
            {
                if (SI.getOpacity()==0) { Collect_SunToken(); SO.setActive(false); SI.setVisible(false); SI.setX(1000); SI.setY(0); SI.setFitWidth(1); SI.setFitHeight(1);}
                else if(SO.getPosition().getY()>900) { SO.setActive(false); SI.setVisible(false); SI.setX(1000); SI.setY(0); SI.setFitWidth(1); SI.setFitHeight(1); }
                else {SO.getPosition().setY(SO.getPosition().getY()+ZS*0.3);SI.setY(SI.getY()+ZS*0.3);}
            }
        }
    }

    public void Collect_SunToken() { STC.setText( String.valueOf(Integer.parseInt(STC.getText())+50)); }

    public void Use_SunToken(int X) { STC.setText( String.valueOf(Math.max(Integer.parseInt(STC.getText())-X,0))); }

    public void BZ_Collide(ImageView PI)
    {
        for (Map.Entry<Zombie,ImageView> Z : Zombies.entrySet())
        {
            Zombie ZO = Z.getKey();
            ImageView ZI = Z.getValue();
            if ((ZO!=null) && (ZI!=null) && (ZO.isActive())&&(PI.isVisible()))
            {
                double dx = Math.abs(ZI.getX()-PI.getX());
                double dy = Math.abs(ZI.getY()-PI.getY());
                if ((dy<100) && (dx<50)) { ZI.setEffect(new Glow(1)); ZO.setHealth(ZO.getHealth()-10); PI.setVisible(false); PI.setX(PI.getX()+150); }
                if(ZO.getHealth()<0) { ZO.setActive(false); A.BG.getChildren().remove(ZI); AZ--; }
            }
        }
    }

    public void LZ_Collide(ImageView LI)
    {
        for (Map.Entry<Zombie,ImageView> Z : Zombies.entrySet())
        {
            Zombie ZO = Z.getKey();
            ImageView ZI = Z.getValue();
            if ((ZO!=null) && (ZI!=null) && (ZO.isActive()))
            {
                double dx = Math.abs(ZI.getX()-LI.getX());
                double dy = Math.abs(ZI.getY()-LI.getY());
                if ((dy<50) && (dx<50)) { ZO.setActive(false); A.BG.getChildren().remove(ZI); AZ--;}
            }
        }
    }

    public void PZ_Collide(Zombie ZO,ImageView ZI)
    {
        for (Map.Entry<Plant,ImageView> P : Plants.entrySet())
        {
            Plant PO =  P.getKey();
            ImageView PI =  P.getValue();
            if ((PO!=null) && (PI!=null) && (ZO.isActive()))
            {
                if(PO.isActive())
                {
                    double dx = Math.abs(ZO.getPosition().getX() - PO.getPosition().getX());
                    double dy = Math.abs(ZO.getPosition().getY() - PO.getPosition().getY());
                    if ((dy < 100) && (dx < 50)) { ZO.setBlocked(true);Eat(ZO, ZI, PO, PI); }
                }
                else { ZO.setBlocked(false);}
            }
        }
    }

    public void Eat(Zombie ZO,ImageView ZI,Plant PO,ImageView PI)
    {
        if((PO.getType().equals("PotatoMine"))||(PO.getType().equals("Jalapeno")))
        {
            ZO.setActive(false); A.BG.getChildren().remove(ZI); PI.setImage(new Image("sample/Resources/fire.gif")); PO.setHealth(-1);
        }
        PO.setHealth(PO.getHealth()-0.1);
        if(PO.getHealth()<0) {  ZO.setBlocked(false); PO.setActive(false); PI.setVisible(false); PI.setFitWidth(110);; PI.setFitHeight(150); PI.setImage(null);}
    }

    public void ProduceSun()
    {
        for (Map.Entry<Plant,ImageView> P : Plants.entrySet())
        {
            Plant PO =  P.getKey();
            ImageView PI =  P.getValue();
            if ((PO!=null) && (PI!=null) && (PO.isActive()))
            {
                if (PO.getType().equals("SunTokenGenerator"))
                {
                    ImageView I = new ImageView("sample/Resources/sun.gif");
                    I.setX(PO.getPosition().getX());I.setY(PO.getPosition().getY());I.setFitHeight(75);I.setFitWidth(75);
                    A.BG.getChildren().add(I);
                    I.setOnMouseClicked(new EventHandler<MouseEvent>() {@Override public void handle(MouseEvent event) { I.setOpacity(0);Collect_SunToken();A.BG.getChildren().remove(I); }});
                }
            }
        }
    }

    public void Activate_LawnMower(ImageView ZI) {
        for (Map.Entry<LawnMower,ImageView> L : LawnMowers.entrySet())
        {
            LawnMower LO =  L.getKey();
            ImageView LI =  L.getValue();
            if((LO.getPosition().getY()==ZI.getY()))
            { if (!LO.isUsed()) {LO.setRunning(true);} else {try{Main.setRoot_GO();}catch (IOException i){}}}
        }
    }

    public void Move_LawnMower()
    {
        for (Map.Entry<LawnMower,ImageView> L : LawnMowers.entrySet())
        {
            LawnMower LO =  L.getKey();
            ImageView LI =  L.getValue();
            if (LO.isRunning())
            {
                LO.getPosition().setX(LO.getPosition().getX()+ZS); LI.setX(LI.getX()+ZS); LZ_Collide(LI);
                if (LO.getPosition().getX()>1300) {LO.Use(); LO.setActive(false);LO.setActive(false); A.BG.getChildren().remove(LI);}
            }
        }
    }

    public void  Move_Zombie()
    {
        for (Map.Entry<Zombie, ImageView> Z : Zombies.entrySet())
        {
            Zombie ZO = Z.getKey();
            ImageView ZI = Z.getValue();
            if ((ZO!=null) && (ZI!=null) &&(ZO.isActive()))
            {
                PZ_Collide(ZO,ZI);
                if (ZO.getHealth()<=0) { ZO.setActive(false); A.BG.getChildren().remove(ZI); }
                else if(ZO.getPosition().getX()<250) { Activate_LawnMower(ZI); }
                else if (!ZO.isBlocked()) { ZO.getPosition().setX(ZO.getPosition().getX()-ZS*0.1);ZI.setX(ZI.getX()-ZS*0.1);}
            }
        }
    }

    public void CheckWin() throws IOException
    {
        if((AZ==0)&&(ZC==0)&&(ZB.getX())<1000)
        {
            Main.Level++;
            if(Main.Level==6){Main.setRoot_GW(); Main.Level=1;}
            else {Main.setRoot_NL();}
        }
    }
    public void Execute()
    {
        Time++;
        C_PS++;C_SF++;C_WN++;C_PM++;C_TN++;C_JP++;
        Move_ZB();
        Move_SunToken();
        Move_Zombie();
        Move_Pea();
        Move_LawnMower();
        Manage_Inventory();
        if (Time>1000){ try{CheckWin();} catch( IOException i){}}
        if(Time%500==0) {I_SunToken(); ProduceSun();}
        if(Time%1000==0){ I_Zombie();}
    }

    public void Process()
    { T.start(); }

}

