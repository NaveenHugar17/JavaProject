import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class GameArea extends JPanel implements ActionListener,KeyListener{
	
	private ImageIcon title;
	private ImageIcon snakeup;
	private ImageIcon snakedown;
	private ImageIcon snakeleft;
	private ImageIcon snakeright;
	private ImageIcon snakebody;
	private ImageIcon snakefood;
	
	private boolean up=false; 
	private boolean down=false; 
	private boolean left=false; 
	private boolean right=false; 

	private int[] snakexlen=new int[650];
	private int[] snakeylen=new int[650];
	
	private Timer timer;
	private int delay=100;
	private int lengthofsnake=3;
	private int moves=0;
	private int score=0;
	
	private int[] foodxpos= {25,50,75,100,125,150,175,200,225,
							250,275,300,325,350,375,400,425,450,
							475,500,525,550,575,600,625,650};
	
	private int[] foodypos= {100,125,150,175,200,225,
						    250,275,300,325,350,375,400,425,450};
			
	private Random random=new Random();
	
	private int xpos=random.nextInt(26);
	private int ypos=random.nextInt(15);
	
	public GameArea()
	{
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer=new Timer(delay,this);
		timer.start();
	}
	
	public void paint(Graphics obj)
	{
		if(moves==0)
		{
			snakexlen[0]=75;
			snakexlen[1]=50;
			snakexlen[2]=25;
			
			snakeylen[0]=100;
			snakeylen[1]=100;
			snakeylen[2]=100;
			
		}
		
		
		obj.setColor(Color.WHITE);
		obj.drawRect(1, 1, 602, 90);
		title=new ImageIcon("Images/snaketitle.png");
		title.paintIcon(this, obj, -15, -8);
		
		obj.setColor(Color.YELLOW);
		obj.drawRect(24, 99, 651, 451);
		obj.setColor(Color.BLACK);
		obj.fillRect(25, 100, 650, 450);
		
		obj.setColor(Color.PINK);
		obj.drawRect(604, 1, 85, 90);
		obj.setColor(Color.YELLOW);
		obj.fillRect(605, 2, 84, 50);
		obj.setColor(Color.BLACK);
		obj.setFont(new Font("Cooper",Font.PLAIN,12));
		obj.drawString("SCORE:"+score, 610, 30);
		obj.setColor(Color.ORANGE);
		obj.fillRect(605, 53, 84, 38);
		
		
		
		
		snakeright=new ImageIcon("Images/snakeright.png");
		snakeright.paintIcon(this, obj, snakexlen[0], snakeylen[0]);
		
		for(int i=0;i<lengthofsnake;i++)
		{
			if(i==0 && right)
			{
				snakeright=new ImageIcon("Images/snakeright.png");
				snakeright.paintIcon(this, obj, snakexlen[i], snakeylen[i]);
			}
			
			if(i==0 && left)
			{
				snakeleft=new ImageIcon("Images/snakeleft.png");
				snakeleft.paintIcon(this, obj, snakexlen[i], snakeylen[i]);
			}
			
			if(i==0 && up)
			{
				snakeup=new ImageIcon("Images/snakeup.png");
				snakeup.paintIcon(this, obj, snakexlen[i], snakeylen[i]);
			}
			
			if(i==0 && down)
			{
				snakedown=new ImageIcon("Images/snakedown.png");
				snakedown.paintIcon(this, obj, snakexlen[i], snakeylen[i]);
			}
			
			if(i!=0)
			{
				snakebody=new ImageIcon("Images/snakebody.png");
				snakebody.paintIcon(this, obj, snakexlen[i], snakeylen[i]);
			}
		}
		
		snakefood=new ImageIcon("Images/snakefood.png");
		
		if((foodxpos[xpos]==snakexlen[0])&&(foodypos[ypos]==snakeylen[0]))
		{
			score+=5;
			lengthofsnake++;
			xpos=random.nextInt(26);
			ypos=random.nextInt(15);
		}
		
		snakefood.paintIcon(this, obj, foodxpos[xpos], foodypos[ypos]);
		
		for(int i=1;i<lengthofsnake;i++)
		{
			if((snakexlen[i]==snakexlen[0])&&(snakeylen[i]==snakeylen[0]))
			{
				up=down=right=left=false;
				
				obj.setColor(Color.WHITE);
				obj.setFont(new Font("arial",Font.BOLD,50));
				obj.drawString("GAME OVER!!", 200, 200);
				obj.setFont(new Font("arial",Font.BOLD,20));
				obj.drawString("Press Enter to restart", 200, 250);
			}
			
			
		}
		
		obj.dispose();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_ENTER)
		{
			moves=0;
			score=0;
			lengthofsnake=3;
			repaint();
		}
		
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			moves++;
			
			if(left)
				right=false;
			
			else
				right=true;
			
			
			up=false;
			down=false;
		}
		
		if(e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			moves++;
			
			if(right)
				left=false;
			
			else
				left=true;
			
			
			up=false;
			down=false;
		}
		
		if(e.getKeyCode()==KeyEvent.VK_UP)
		{
			moves++;
			
			if(down)
				up=false;
			
			else
				up=true;
			
			
			left=false;
			right=false;
		}
		
		if(e.getKeyCode()==KeyEvent.VK_DOWN)
		{
			moves++;
			
			if(up)
				down=false;
			
			else
				down=true;
			
			
			left=false;
			right=false;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		
		if(right)
		{
			for(int i=lengthofsnake-1;i>=0;i--)
			{
				snakeylen[i+1]=snakeylen[i];
			}
			
			for(int i=lengthofsnake;i>=0;i--)
			{
				if(i==0)
				{
					snakexlen[i]=snakexlen[i]+25;
				}
				
				else
				{
					snakexlen[i]=snakexlen[i-1];
				}
				
				if(snakexlen[i]>650)
				{
					snakexlen[i]=25;
				}
			}
			
			repaint();
			
		}
		
		if(left)
		{
			for(int i=lengthofsnake-1;i>=0;i--)
			{
				snakeylen[i+1]=snakeylen[i];
			}
			
			for(int i=lengthofsnake;i>=0;i--)
			{
				if(i==0)
				{
					snakexlen[i]=snakexlen[i]-25;
				}
				
				else
				{
					snakexlen[i]=snakexlen[i-1];
				}
				
				if(snakexlen[i]<25)
				{
					snakexlen[i]=650;
				}
			}
			
			repaint();			
		}
		
		if(up)
		{
			for(int i=lengthofsnake-1;i>=0;i--)
			{
				snakexlen[i+1]=snakexlen[i];
			}
			
			for(int i=lengthofsnake;i>=0;i--)
			{
				if(i==0)
				{
					snakeylen[i]=snakeylen[i]-25;
				}
				
				else
				{
					snakeylen[i]=snakeylen[i-1];
				}
				
				if(snakeylen[i]<100)
				{
					snakeylen[i]=525;
				}
			}
			
			repaint();
		}
		
		if(down)
		{
			for(int i=lengthofsnake-1;i>=0;i--)
			{
				snakexlen[i+1]=snakexlen[i];
			}
			
			for(int i=lengthofsnake;i>=0;i--)
			{
				if(i==0)
				{
					snakeylen[i]=snakeylen[i]+25;
				}
				
				else
				{
					snakeylen[i]=snakeylen[i-1];
				}
				
				if(snakeylen[i] > 525)
				{
					snakeylen[i]=100;
				}
			}
			
			repaint();			
		}
		
		
		
		
	}

}
