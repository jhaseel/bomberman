package bomberman;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Console;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class main1 extends JFrame implements Runnable, KeyListener  {
	
	
	static Thread personaje = new Thread();
	static Thread bomba = new Thread();
	static Thread bot1 = new Thread();
	static Thread bot2 = new Thread();
	static Thread bot3 = new Thread();


	//tamaño mapa
	 int tamx=15;
	 int tamy=15;
	//inicial
	int vidas=3;
	int xinicial=1;
	int yinicial=1;
	//actual
	int xactual=xinicial;
	int yactual=yinicial;
	//temporal
	int xtemporal;
	int ytemporal;
	//posbomba
	int tiempoBomba=3000;
	int xbomba;
	int ybomba;
	boolean bombaActiva=false;
	int tamañoExplosion=1;
	//limite bombas
	boolean limiteArriba=false;
	boolean limiteAbajo=false;
	boolean limiteIzq=false;
	boolean limiteDer=false;
	//variables bot
	int enemigos=3;
	char name1='1';
	char name2='2';
	char name3='3';
	
	int tamGx = 682;
	int tamGy=653;
	

	public  char world[][] = new char[tamx][tamy];
	
	public JPanel contentPane;

	public main1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		setBounds(0, 0, 700, 700);
		this.setMinimumSize(new Dimension(700, 700)); // valores de ejemplo...
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		//this.addKeyListener(this);
		
		 this.addComponentListener(new java.awt.event.ComponentAdapter() {
	            public void componentResized(java.awt.event.ComponentEvent evt) {
	            	
	
	            	
	            	dimension=contentPane.getWidth()/16;
	            	dimensiony=contentPane.getHeight()/16;
	            	
	            	repaint();
	            	System.out.println("tamaño cambialdo "+dimension);
	            	System.out.println("tamaño cambialdo "+dimensiony);
	            
	            }
	        });
	}
	
	int x=20,y=50;	 
	int dimension=40;
	int dimensiony=40;
	 public  synchronized  void paint(Graphics g){
		 int x=20,y=50;	 
		 super.paint(g);
		
			for(int i = 0;i<tamx;i++){
				for(int j = 0;j<tamy;j++){
					if(world[i][j]=='*') {
						g.setColor(Color.gray);
				        g.fillRect   (x,y,dimension,dimensiony);
					
					}else if(world[i][j]=='+') {
						g.setColor(Color.orange);
			            g.fillRect   (x,y,dimension,dimensiony);
						
					}else if(world[i][j]=='-') {
						g.setColor(Color.red);
				        g.fillRect   (x,y,dimension,dimensiony);
					}else if(world[i][j]=='|') {
						ImageIcon Img = new ImageIcon(getClass().getResource("/images/img1.jpg")); 
						g.drawImage(Img.getImage(), x, y,dimension , dimensiony, null);
						 
					}else if(world[i][j]=='o') {
						ImageIcon Img = new ImageIcon(getClass().getResource("/images/bomba.png")); 
						g.drawImage(Img.getImage(), x, y,dimension , dimensiony, null);
						 
					}else if(world[i][j]=='<') {
					
						ImageIcon Img = new ImageIcon(getClass().getResource("/images/boom.png")); 
						g.drawImage(Img.getImage(), x, y,dimension , dimensiony, null);
						 
					}else if(world[i][j]=='.') {
					
						ImageIcon Img = new ImageIcon(getClass().getResource("/images/premio.png")); 
						g.drawImage(Img.getImage(), x, y,dimension , dimensiony, null);
						 
					}else if(world[i][j]=='1') {
					
						ImageIcon Img = new ImageIcon(getClass().getResource("/images/bot1.png")); 
						g.drawImage(Img.getImage(), x, y,dimension , dimensiony, null);
						 
					}else if(world[i][j]=='2') {
					
						ImageIcon Img = new ImageIcon(getClass().getResource("/images/bot1.png")); 
						g.drawImage(Img.getImage(), x, y,dimension , dimensiony, null);
						 
					}else if(world[i][j]=='3') {
					
						ImageIcon Img = new ImageIcon(getClass().getResource("/images/bot1.png")); 
						g.drawImage(Img.getImage(), x, y,dimension , dimensiony, null);
						 
					}
					
			        g.setColor(Color.black);
			        g.drawRect  (x,y,dimension,dimensiony);
					x=x+dimension;
				}
				x=20;
				y=y+dimensiony;
	    }
			
	        
	       
	       
	 }


	//----------------------------------------------
	public void crearLimites(){
		for (int i = 0; i < tamx; i++)
		{
			char pared='*';
			world[i][0]=pared;// P=pared
			world[0][i]=pared;// P=pared
			world[tamy-1][i]=pared;
			world[i][tamy-1]=pared;// P=pared
		}
	}

	public void muros(){
		char muro='*';
		world[1][8]=muro;// muro
		
		world[2][2]=muro;// muro
		world[2][4]=muro;// muro
		world[2][5]=muro;// muro
		world[2][7]=muro;// muro
		world[2][8]=muro;// muro
		world[2][11]=muro;// muro
		
		world[3][5]=muro;// muro
		world[3][10]=muro;// muro
		world[3][13]=muro;// muro
		world[3][11]=muro;// muro
		
		world[4][1]=muro;// muro
	    world[4][2]=muro;// muro
		world[4][3]=muro;// muro
		world[4][5]=muro;// muro
		world[4][6]=muro;// muro
		world[4][7]=muro;// muro
		world[4][11]=muro;// muro

		world[5][9]=muro;// muro
		world[5][11]=muro;// muro

		world[6][3]=muro;// muro
	    world[6][6]=muro;// muro
		world[6][7]=muro;// muro
		world[6][9]=muro;// muro

		world[7][1]=muro;// muro
		world[7][2]=muro;// muro
		world[7][3]=muro;// muro
		world[7][5]=muro;// muro
		world[7][6]=muro;// muro
		world[7][9]=muro;// muro

		world[8][3]=muro;// muro
		world[8][11]=muro;// muro
		world[8][12]=muro;// muro
		world[8][13]=muro;// muro

		world[9][3]=muro;// muro
		world[9][6]=muro;// muro
		world[9][9]=muro;// muro

		world[10][2]=muro;// muro
		world[10][3]=muro;// muro
		world[10][5]=muro;// muro
		world[10][6]=muro;// muro
		world[10][7]=muro;// muro
		world[10][9]=muro;// muro
		world[10][11]=muro;// muro
		world[10][12]=muro;// muro

		world[11][9]=muro;// muro
		world[11][12]=muro;// muro

		world[12][1]=muro;// muro
		world[12][4]=muro;// muro
		world[12][5]=muro;// muro
		world[12][6]=muro;// muro
		world[12][7]=muro;// muro
		world[12][9]=muro;// muro
		world[12][10]=muro;// muro
		world[12][12]=muro;// muro

	}
	
	public void murosRompibles(){
		char muro='+';
		world[1][6]=muro;// muro
		
		world[2][3]=muro;// muro
		world[2][6]=muro;// muro
		world[2][9]=muro;// muro
		world[2][12]=muro;// muro
		
		world[3][1]=muro;// muro
		world[3][2]=muro;// muro
		world[3][6]=muro;// muro
		world[3][9]=muro;// muro
		
		world[4][13]=muro;// muro

		world[5][2]=muro;// muro
		world[5][3]=muro;// muro
		world[5][7]=muro;// muro
		world[5][8]=muro;// muro

		world[6][5]=muro;// muro
	    world[6][11]=muro;// muro

		world[7][4]=muro;// muro

		world[8][2]=muro;// muro
		world[8][6]=muro;// muro
		world[8][7]=muro;// muro
		world[8][9]=muro;// muro

		world[10][8]=muro;// muro

		world[11][5]=muro;// muro
		world[11][8]=muro;// muro
		world[11][10]=muro;// muro

		world[12][2]=muro;// muro
		world[12][3]=muro;// muro
		world[12][8]=muro;// muro
		world[12][11]=muro;// muro

		world[13][6]=muro;// muro
		world[13][11]=muro;// muro

	}
	public void murosRompiblesPremio(){
			char muro='-';
		world[1][12]=muro;// muro
		
		world[5][5]=muro;// muro

	    world[6][13]=muro;// muro

		world[8][1]=muro;// muro
		world[8][10]=muro;// muro

		world[10][13]=muro;// muro

		world[11][6]=muro;// muro
	}
	


	public void validadMovimiento(int x, int y){
		if(world[x][y]=='.'){
			tamañoExplosion=tamañoExplosion+1;
		}
		if (world[x][y]!='*' && world[x][y]!='+'&& world[x][y]!='o'&&world[x][y]!='-')
		{
			if (world[xactual][yactual]=='|')//si deja bomba la mantiene
			{
				world[xactual][yactual] =' ';
			}

			xactual=x; yactual=y;
			world[xactual][yactual]='|';
			
			revalidate();
			repaint();

		}
		
	}

	public void explotaDibujo(){
		for (int z = 1; z <= tamañoExplosion; z++){
			//arriba
			if ((xbomba-z)>0 && !limiteArriba){

				if (world[xbomba-z][ybomba]==' '){
					world[xbomba-z][ybomba] ='<';
				}
				if (world[xbomba-z][ybomba]=='+'){//pared explotable
					limiteArriba=true;
					world[xbomba-z][ybomba] ='<';
				}else if(world[xbomba-z][ybomba]=='-'){//pared premio
					limiteArriba=true;
					world[xbomba-z][ybomba] ='.';
				}else if(world[xbomba-z][ybomba]=='|'){//personaje
					world[xbomba-z][ybomba] ='<';
					vidas=vidas-1;
					xactual=xinicial; yactual=yinicial;
					world[xinicial][yinicial]='|';
				}else if(world[xbomba-z][ybomba]=='*'){//pared
					limiteArriba=true;
				}else if(world[xbomba-z][ybomba]=='1'){//enemigo
					limiteArriba=true;
					world[xbomba-z][ybomba]='<';
					name1='0';
					enemigos=enemigos-1;
				}else if(world[xbomba-z][ybomba]=='2'){//enemigo
					limiteArriba=true;
					world[xbomba-z][ybomba]='<';
					name2='0';
					enemigos=enemigos-1;
				}else if(world[xbomba-z][ybomba]=='3'){//enemigo
					limiteArriba=true;
					world[xbomba-z][ybomba]='<';
					name3='0';
					enemigos=enemigos-1;
				}
			}
			//abajo
			if ((xbomba+z)<tamx &&!limiteAbajo){
				if (world[xbomba+z][ybomba]==' '){
					world[xbomba+z][ybomba] ='<';
				}
				if (world[xbomba+z][ybomba]=='+'){//pared explotable
					limiteAbajo=true;
					world[xbomba+z][ybomba] ='<';
				}else if(world[xbomba+z][ybomba]=='-'){//pared premio
					limiteAbajo=true;
					world[xbomba+z][ybomba] ='.';
				}else if(world[xbomba+z][ybomba]=='|'){//personaje
					world[xbomba+z][ybomba] ='<';
					vidas=vidas-1;
					xactual=xinicial; yactual=yinicial;
					world[xinicial][yinicial]='|';
				}else if(world[xbomba+z][ybomba]=='*'){//pared
					limiteAbajo=true;
				}else if(world[xbomba+z][ybomba]=='1'){//enemigo
					limiteArriba=true;
					world[xbomba+z][ybomba]='<';
					name1='0';
					enemigos=enemigos-1;
				}else if(world[xbomba+z][ybomba]=='2'){//enemigo
					limiteArriba=true;
					world[xbomba+z][ybomba]='<';
					name2='0';
					enemigos=enemigos-1;
				}else if(world[xbomba+z][ybomba]=='3'){//enemigo
					limiteArriba=true;
					world[xbomba+z][ybomba]='<';
					name3='0';
					enemigos=enemigos-1;
				}
			}
			//derecha
			if ((ybomba+z)<tamy && !limiteDer){
			if (world[xbomba][ybomba+z]==' '){
					world[xbomba][ybomba+z] ='<';
				}
				if (world[xbomba][ybomba+z]=='+'){//pared explotable
					world[xbomba][ybomba+z] ='<';
					limiteDer=true;
				}else if(world[xbomba][ybomba+z]=='-'){//pared premio
					world[xbomba][ybomba+z] ='.';
					limiteDer=true;
				}else if(world[xbomba][ybomba+z]=='|'){//personaje
					world[xbomba][ybomba+z]='<';
					vidas=vidas-1;
					xactual=xinicial; yactual=yinicial;
					world[xinicial][yinicial]='|';
				}else if(world[xbomba][ybomba+z]=='*'){//pared rigida
					limiteDer=true;
				}else if(world[xbomba][ybomba+z]=='1'){//enemigo
					limiteArriba=true;
					world[xbomba][ybomba+z]='<';
					name1='0';
					enemigos=enemigos-1;
				}else if(world[xbomba][ybomba+z]=='2'){//enemigo
					limiteArriba=true;
					world[xbomba][ybomba+z]='<';
					name2='0';
					enemigos=enemigos-1;
				}else if(world[xbomba][ybomba+z]=='3'){//enemigo
					limiteArriba=true;
					world[xbomba][ybomba+z]='<';
					name3='0';
					enemigos=enemigos-1;
				}
			}
			//izq
			if ((ybomba-z)>0 &&!limiteIzq){
				if (world[xbomba][ybomba-z]==' '){
					world[xbomba][ybomba-z]='<';
				}
				if (world[xbomba][ybomba-z]=='+'){//pared explotable
					limiteIzq=true;
					world[xbomba][ybomba-z]='<';
				}else if(world[xbomba][ybomba-z]=='-'){//pared premio
					limiteIzq=true;
					world[xbomba][ybomba-z]='.';
				}else if(world[xbomba][ybomba-z]=='|'){//personaje
					world[xbomba][ybomba-z]='<';
					vidas=vidas-1;
					xactual=xinicial; yactual=yinicial;
					world[xinicial][yinicial]='|';
				}else if(world[xbomba][ybomba-z]=='*'){//pared rigida
					limiteIzq=true;
				}else if(world[xbomba][ybomba-z]=='1'){//enemigo
					limiteArriba=true;
					world[xbomba][ybomba-z]='<';
					name1='0';
					enemigos=enemigos-1;
				}else if(world[xbomba][ybomba-z]=='z'){//enemigo
					limiteArriba=true;
					world[xbomba][ybomba-z]='<';
					name2='0';
					enemigos=enemigos-1;
				}else if(world[xbomba][ybomba-z]=='3'){//enemigo
					limiteArriba=true;
					world[xbomba][ybomba-z]='<';
					name3='0';
					enemigos=enemigos-1;
				}
			}
		}
		repaint();
		limiteArriba=false;
		limiteAbajo=false;
		limiteIzq=false;
		limiteDer=false;
	}
	
	public void explota(){
		
		for (int z = 1; z <= tamañoExplosion; z++){
			//arriba
			if ((xbomba-z)>0 && !limiteArriba){
			
				if (world[xbomba-z][ybomba]=='<'){
				world[xbomba-z][ybomba] =' ';
				}
			}
			//abajo
			if ((xbomba+z)<tamx &&!limiteAbajo){
				if (world[xbomba+z][ybomba]=='<'){
				world[xbomba+z][ybomba] =' ';
				}

			}
			//derecha
			if ((ybomba+z)<tamy && !limiteDer){
						 if (world[xbomba][ybomba+z]=='<'){
				world[xbomba][ybomba+z]=' ';
				}
			
			}
			//izq
			if ((ybomba-z)>0 &&!limiteIzq){
				 if (world[xbomba][ybomba-z]=='<'){
				world[xbomba][ybomba-z]=' ';
				}
				
			}
		}
		limiteArriba=false;
		limiteAbajo=false;
		limiteIzq=false;
		limiteDer=false;
		
		world[xbomba][ybomba]=' ';
		bombaActiva=false;
		repaint();
	}
	
	boolean bomba() {
		if(!bombaActiva){
			bombaActiva=true;
			xbomba=xactual;
			ybomba=yactual;
			world[xbomba][ybomba]='o';
			repaint();
			return true;
		}else return false;
	}
	
	
	public void jugar(int tecla){
		
//			while(vidas>0&&enemigos>0){}
			 if (tecla==38){//^ arriba
				 xtemporal=xactual-1;
				 ytemporal=yactual;
				 validadMovimiento(xtemporal,ytemporal);
			 }else if (tecla==40){// abajo
				 xtemporal=xactual+1;
				 ytemporal=yactual;
				 validadMovimiento(xtemporal,ytemporal);
			 }else if(tecla==39){//derecha -->
				 xtemporal=xactual;
				 ytemporal=yactual+1;
				 validadMovimiento(xtemporal,ytemporal);
			}else if(tecla==37){ //izq <--
				 xtemporal=xactual;
				 ytemporal=yactual-1;
				 validadMovimiento(xtemporal,ytemporal);
			}else if(tecla==27){//salir
				//salir
			}else if(tecla==88){//´push x -> bomba
				/*if(!bombaActiva){
					bombaActiva=true;
					xbomba=xactual;
					ybomba=yactual;
					world[xbomba][ybomba]='o';
					
					repaint();
					try {
						Thread.sleep(tiempoBomba);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					explota();
					world[xbomba][ybomba]=' ';
					
					
					bombaActiva=false;
				}*/
				}
			 
			if(enemigos==0){
				System.out.println("gamaste");
			}else if(vidas==0) System.out.println("suerte para la proxima");
		}

		 boolean comprobarMuerte(char nombre){
			if (nombre=='1'){
				if(name1!=nombre){return false;}else return true;
			}else if (nombre=='2'){
				if(name2!=nombre){return false; }else return true;
			}else if (nombre=='3'){
				if(name3!=nombre){return false; }else return true;
			}else {return true;}
		}
		 
		 int xpasadobtot1=12;
		 int ypasadobot1=12;
		 
		 void bots(int x, int y,char nombre){
			int num;
			int xactualbot=x;
			int yactualbot=y;
			//while(comprobarMuerte(nombre)){}
			int xtemporalbot=0;
			int ytemporalbot=0;
			num = (int) (Math.random() *4 ) + 1;
			do {
			if (num==1){//sube
				 xtemporalbot=xactualbot-1;
				 ytemporalbot=yactualbot;
			}else if(num==2){//baja
				 xtemporalbot=xactualbot+1;
				 ytemporalbot=yactualbot;
			}else if (num==3){//derecha
				 xtemporalbot=xactualbot;
				 ytemporalbot=yactualbot+1;
			}else if(num==4){//izq
				 xtemporalbot=xactualbot;
				 ytemporalbot=yactualbot-1;
			}
			}while( xtemporalbot>13  | ytemporalbot>13 );
			//valida movimiento
			System.out.println("x: "+xtemporalbot);
			System.out.println("y"+ ytemporalbot);
			if (world[xtemporalbot][ytemporalbot]!='*' && world[xtemporalbot][ytemporalbot]!='+'&& world[xtemporalbot][ytemporalbot]!='o'&& world[xtemporalbot][ytemporalbot]!='-'){
				world[xactualbot][yactualbot] =' ';
				xpasadobtot1=xactualbot;
				ypasadobot1=xactualbot;
				xactualbot=xtemporalbot; 
				yactualbot=ytemporalbot;
				world[xactualbot][yactualbot]=nombre;
				repaint();
				//duerme despues de la muerte
			}

		}

	
	public static void main(String[] args) {
		
		//	personaje;
		// bomba;
		// bot1;
		
			main1 m = new main1();

			personaje = new Thread(m,"pensonaje");
			personaje.start();
			bomba = new Thread(m,"bombita");
			bomba.start();
			bot1 = new Thread(m,"bot1");
			bot2 = new Thread(m,"bot2");
			bot3 = new Thread(m,"bot3");
			bot1.start();
			bot2.start();
			bot3.start();
			m.setVisible(true);
			m.crearLimites();
			m.muros();
			m.murosRompibles();
			m.murosRompiblesPremio();
			m.world[m.xinicial][m.yinicial]='|';
			
			
			
			 
			
/*		std::thread inicio(jugar);
		std::thread bomb(jugar);


		inicio.join();
		bomb.join();
		bot1.join();
		bot2.join();
		bot3.join();*/

	}


	@Override
	public void run() {
		 Thread miHilo=Thread.currentThread();
		 System.out.println("dormir hilo:"+miHilo);
		 if(miHilo==bomba) {
			while(true) {
				 while(!bombaActiva) {
					 System.out.println("bomba no activa");
				 }
				 try {
						System.out.println("bomba colocada");
					bomba.sleep(tiempoBomba);
					explotaDibujo();
					bomba.sleep(900);
					explota();
				} catch (InterruptedException e) {
				}
			}

		 }else if(miHilo==personaje) {
			 addKeyListener(this);
			 while(vidas>0 && enemigos>0) {		
				 System.out.println(vidas);
			 }
			 if(vidas==0) {
				 JOptionPane.showMessageDialog(null, "derrota");
			 }else JOptionPane.showMessageDialog(null, "victoria");
				 System.exit(0);
				 
		 }else if(miHilo==bot1) {
			 int num;
			 System.out.println("bot1");
				int xactualbot=12;
				int yactualbot=12;
				char nombre='1';
			 while(comprobarMuerte(nombre)) {
					//while(comprobarMuerte(nombre)){}
					int xtemporalbot=0;
					int ytemporalbot=0;
					do {
						 xtemporalbot=xactualbot;
						 ytemporalbot=xactualbot;
						num = (int) (Math.random() *4 ) + 1;
					if (num==1){//sube
						 xtemporalbot=xactualbot-1;
						 ytemporalbot=yactualbot;
					}else if(num==2){//baja
						 xtemporalbot=xactualbot+1;
						 ytemporalbot=yactualbot;
					}else if (num==3){//derecha
						 xtemporalbot=xactualbot;
						 ytemporalbot=yactualbot+1;
					}else if(num==4){//izq
						 xtemporalbot=xactualbot;
						 ytemporalbot=yactualbot-1;
					}
					}while( xtemporalbot>14  && ytemporalbot>14 && xtemporalbot<0 && ytemporalbot<0);
					
					//valida movimiento
					System.out.println("x: "+xtemporalbot);
					System.out.println("y"+ ytemporalbot);
					if (world[xtemporalbot][ytemporalbot]!='*' && world[xtemporalbot][ytemporalbot]!='+'&& world[xtemporalbot][ytemporalbot]!='o'&& world[xtemporalbot][ytemporalbot]!='-'){
						world[xactualbot][yactualbot] =' ';
						xactualbot=xtemporalbot; 
						yactualbot=ytemporalbot;
						world[xactualbot][yactualbot]=nombre;
						revalidate();
						repaint();
						//duerme despues de la muerte
					}
			 try { bot1.sleep(500);
			} catch (InterruptedException e) {}
			 }
			 
		 }else if(miHilo==bot2) {
			 int num;
				int xactualbot=13;
				int yactualbot=1;
				char nombre='2';
			 while(comprobarMuerte(nombre)) {
					//while(comprobarMuerte(nombre)){}
					int xtemporalbot=0;
					int ytemporalbot=0;

					do {
						 xtemporalbot=xactualbot;
						 ytemporalbot=xactualbot;
						num = (int) (Math.random() *4 ) + 1;
					if (num==1){//sube
						 xtemporalbot=xactualbot-1;
						 ytemporalbot=yactualbot;
					}else if(num==2){//baja
						 xtemporalbot=xactualbot+1;
						 ytemporalbot=yactualbot;
					}else if (num==3){//derecha						
						 xtemporalbot=xactualbot;
						 ytemporalbot=yactualbot+1;
					}else if(num==4){//izq						
						 xtemporalbot=xactualbot;
						 ytemporalbot=yactualbot-1;
					}
					}while( xtemporalbot>14  && ytemporalbot>14 && xtemporalbot<0 && ytemporalbot<0);
					
					//valida movimiento
					System.out.println("x: "+xtemporalbot);
					System.out.println("y"+ ytemporalbot);
					if (world[xtemporalbot][ytemporalbot]!='*' && world[xtemporalbot][ytemporalbot]!='+'&& world[xtemporalbot][ytemporalbot]!='o'&& world[xtemporalbot][ytemporalbot]!='-'){
						world[xactualbot][yactualbot] =' ';
						xactualbot=xtemporalbot; 
						yactualbot=ytemporalbot;
						world[xactualbot][yactualbot]=nombre;
						revalidate();
						repaint();
						//duerme despues de la muerte
					}
			 try { bot1.sleep(500);
			} catch (InterruptedException e) {}
			 }
			 
			 
		 }else if(miHilo==bot3) {
			 int num;
			
				int xactualbot=2;
				int yactualbot=13;
				char nombre='3';
			 while(comprobarMuerte(nombre)) {
					//while(comprobarMuerte(nombre)){}
					int xtemporalbot=0;
					int ytemporalbot=0;
					do {
						 xtemporalbot=xactualbot;
						 ytemporalbot=xactualbot;
						num = (int) (Math.random() *4 ) + 1;
					if (num==1){//sube
						 xtemporalbot=xactualbot-1;
						 ytemporalbot=yactualbot;
					}else if(num==2){//baja
						 xtemporalbot=xactualbot+1;
						 ytemporalbot=yactualbot;
					}else if (num==3){//derecha
						 xtemporalbot=xactualbot;
						 ytemporalbot=yactualbot+1;
					}else if(num==4){//izq
						 xtemporalbot=xactualbot;
						 ytemporalbot=yactualbot-1;
					}
					}while( xtemporalbot>14  && ytemporalbot>14 && xtemporalbot<0 && ytemporalbot<0);					
					//valida movimiento
					System.out.println("x: "+xtemporalbot);
					System.out.println("y"+ ytemporalbot);
					if (world[xtemporalbot][ytemporalbot]!='*' && world[xtemporalbot][ytemporalbot]!='+'&& world[xtemporalbot][ytemporalbot]!='o'&& world[xtemporalbot][ytemporalbot]!='-'){
						world[xactualbot][yactualbot] =' ';
						xactualbot=xtemporalbot; 
						yactualbot=ytemporalbot;
						world[xactualbot][yactualbot]=nombre;
						revalidate();
						repaint();
						//duerme despues de la muerte
					}
			 try { bot1.sleep(500);
			} catch (InterruptedException e) {}
			 }

			 
		 }
		 
}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==88) {
			bomba();
		}else {
			jugar(e.getKeyCode());
		}
	}


	@Override
	public void keyReleased(KeyEvent e) {
	}


	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	

	}
