package com.mrberkd.applets;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;

public class Applwt extends Applet implements Runnable {

	
	
	
	
	Date date;
	int secX,secY,minx,minY,hourX,hourY;
	
	double aci,r,a,b,c;
	double secAci=(double)(6*Calendar.getInstance().get(Calendar.SECOND));	
	double minAci=(double)(6*Calendar.getInstance().get(Calendar.MINUTE));	
	double hourAci=(double)(30*Calendar.getInstance().get(Calendar.HOUR));	
	int x1,x2,y1,y2;
	Graphics2D g2;
	SimpleDateFormat sf=new SimpleDateFormat("hh:mm:ss");
	
	public void init() {
		Thread t=new Thread(this);
		t.start();
	}	  
		
	
	public void run() {					
			try {	
				while(true) {
					//repaint ile paint fonksiyonunu çaðýrýrýz. Eski Graphics'in üzerine yazdýðý için yeni pencere açýlmamýþ olur.
					repaint();
					
					date=new Date();	
					//Thread 1 sn uyutulur.
					Thread.sleep(1000);
					
				}
			} catch(Exception ex) {
				System.out.println("Bir hata Oluþtu" + ex.getMessage() );
			}
	}
	

	public void paint(Graphics g) {
		g2 = (Graphics2D) g;
		
		
		g.setColor(new Color(230,180,200));
		g.fillRect(g.getClipBounds().x,g.getClipBounds().y, g.getClipBounds().width, g.getClipBounds().height);
		aci=0;
		
		g.setFont(new Font("arial",Font.BOLD,20));
		g.setColor(Color.BLACK);
		g.drawString(sf.format(date),60,190);
		
		g.setColor(new Color(255,0,75));
		g2.setStroke(new BasicStroke(8));
		g2.drawOval(36,36,128,128);
		
		
		
		
		
		g2.setStroke(new BasicStroke(2));		
		while(aci <= 360) {
			//Her dakika/saniye için küçük siyah çizgiler çizdirildi.
			
			g.setColor(Color.black);
			r=Math.toRadians(aci);
			x1=(int)(60*Math.cos(r));
			y1=(int)(60*Math.sin(r));
			x2=(int)(50*Math.cos(r));
			y2=(int)(50*Math.sin(r));	
			g.drawLine(100+x1,100+y1,100+x2,100+y2);
			aci += 6;
		}
		aci=0;		
		while(aci<=360) {
			//Her saat için kýrmýzý çubuklar çizdirildi
			g.setColor(Color.red);
			r=Math.toRadians(aci);
			x1=(int)(60*Math.cos(r));
			y1=(int)(60*Math.sin(r));
			x2=(int)(40*Math.cos(r));
			y2=(int)(40*Math.sin(r));	
			g.drawLine(100+x1,100+y1,100+x2,100+y2);
			aci +=30;
		}
			
			//X ve Y uzunluk deðerlerini kullanarak drawLine ile merkezden hedef noktaya kadar saniye ibresi çizdirildi.
			a=Math.toRadians(secAci);
			secX=(int) (40*Math.sin(a));	
			secY=(int) (40*Math.cos(a));	
			g.setColor(Color.black);
			g.drawLine(100,100,100+secX,100-secY);
			// Saniye ibresinin sonu
			
			//Yelkovanýn baþý
			b=Math.toRadians(minAci);
			minx=(int)(30*Math.sin(b));
			minY=(int)(30*Math.cos(b));
			g.setColor(Color.blue);
            g2.setStroke(new BasicStroke(2));
			g2.drawLine(100,100,100+minx,100-minY);		
			//Yelkovan Sonu
			
			
			//Akrebin baþý
			c=Math.toRadians(hourAci);			
			hourX=(int)(20*Math.sin(c));
			hourY=(int)(20*Math.cos(c));
			g.setColor(Color.red);
			g2.setStroke(new BasicStroke(3));
			g2.drawLine(100,100,100+hourX,100-hourY);		
			//Akrebin sonu
			
			
			//Merkezdeki siyah nokta. Sadece güzel görünmesi için eklendi.
			//En üstte gözükmesini istediðim için siyah noktayý en sona ekledim.
			g.setColor(Color.black);
			g.fillOval(96,96,8,8);
			
			secAci+=6;									//Her 1 saniyede, saniye ibresi 6 derece kayar.
			minAci+=0.1;								//Her 1 saniyede yelkovan 0.1 derece kayar.
			hourAci+=0.001;								//Her 1 saniyede akrep 0.001 derece kayar.
			
	}
}
