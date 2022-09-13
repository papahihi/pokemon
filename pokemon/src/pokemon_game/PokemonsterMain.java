package pokemon_game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PokemonsterMain extends JFrame{
	
	class SkipTitle implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
			PokemonsterMain.this.add(mapCenterButton);
			PokemonsterMain.this.add(sentalk);
			
			PokemonsterMain.this.add(mapTownButton);
			PokemonsterMain.this.add(towntalk);

			PokemonsterMain.this.add(mapWildButton);
			PokemonsterMain.this.add(wildtalk);
			
			titleButton.removeActionListener(this);//버튼 누른 액션 제거
			PokemonsterMain.this.titlePanel.setVisible(false);//버튼 패널 안보이게
			PokemonsterMain.this.remove(titlePanel);//버튼 패널 제거
			
			PokemonsterMain.this.requestFocus();   //포커스 가져오기
			
			playerBox.setVisible(true);
			playerBox.setLocation(400, 400);
			
			momBox.setVisible(true);
			momBox.setLocation(400,200);
			
			PokemonsterMain.this.titleLabel1.setVisible(false);
			PokemonsterMain.this.remove(titleLabel1);
			
			mapTownButton.setEnabled(false);
			mapCenterButton.setEnabled(false);
			mapWildButton.setEnabled(false);
			
			townScreen = true;
		}
	}
	
	class GoWild implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(townScreen) {
				PokemonsterMain.this.wild.setVisible(true);
				PokemonsterMain.this.town.setVisible(false);
				townScreen = false;
			}
			if(centerScreen) {
				PokemonsterMain.this.wild.setVisible(true);
				PokemonsterMain.this.center.setVisible(false);
				centerScreen = false;
			}
			
			if(mainPok.level >=10) {
				canfightBoss = true;
				bossBox.setVisible(true);
				bossBox.setLocation(10, 400);
			}
			playerBox.setLocation(720,10);
			momBox.setVisible(false);
			PokemonsterMain.this.requestFocus();
			ha.setVisible(false);
			wildScreen = true;
		}
	}
	
	class GoTown implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(wildScreen) {
				PokemonsterMain.this.town.setVisible(true);
				PokemonsterMain.this.wild.setVisible(false);
				wildScreen = false;
			}
			if(centerScreen) {
				PokemonsterMain.this.town.setVisible(true);
				PokemonsterMain.this.center.setVisible(false);
				centerScreen = false;
			}
			bossBox.setVisible(false);
			momBox.setVisible(true);
			ha.setVisible(false);
			playerBox.setLocation(400,400);
			PokemonsterMain.this.requestFocus();
			townScreen = true;
		}
	}
	
	class GoCenter implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(townScreen) {
				PokemonsterMain.this.center.setVisible(true);
				PokemonsterMain.this.town.setVisible(false);
				townScreen = false;
			}
			if(wildScreen) {
				PokemonsterMain.this.center.setVisible(true);
				PokemonsterMain.this.wild.setVisible(false);
				wildScreen = false;
			}
			bossBox.setVisible(false);
			momBox.setVisible(false);
			ha.setVisible(true);
			PokemonsterMain.this.requestFocus();
			centerScreen = true;
			playerBox.setLocation(440,530);
			ha.setLocation(460, 105);
		}
	}
	
	class Battle extends Thread{
		@Override
		public void run() {
			enemyPok = wpArray.get((int)(Math.random()*4));
			
			enemySkArr.add(enemyPok.SK1);
			enemySkArr.add(enemyPok.SK2);
			enemySkArr.add(enemyPok.SK3);
			
			JLabel enemyPokImg = new JLabel(enemyPok.image);
			enemyPokImg.setBounds(50, 300, 100, 100);
			BattleF.add(enemyPokImg);
			
			EnemyStat.setText("<html>"+enemyPok.name+"<br/>레벨: "+enemyPok.level+"<br/>체력: "+ enemyPok.hp+"<html>");
			
			BattleM.setText("야생의 " + enemyPok.name+"(이)가 나타났다!");
			
			while(!battleFinish) {
				mapTownButton.setEnabled(false);
				mapWildButton.setEnabled(false);
				mapCenterButton.setEnabled(false);
				try {
					sleep(500);
					
					if(myTurn) {
						MyPokSk1.setEnabled(true);
						MyPokSk2.setEnabled(true);
						MyPokSk3.setEnabled(true);
					}
					
					if(enemyPok.hp<=0) {
						sleep(1000);
						if(mainPok.hp>0) {
							for(int i = 0;i<enemyPok.level;i++) {
							mainPok.levelup();
							}
							BattleM.setText("<html>배틀 종료! <br/>" + mainPok.name +"의 레벨이 "+enemyPok.level+"만큼 올랐습니다!<html>");
							JFrame ClearFrame = new JFrame("배틀 승리");
							ClearFrame.setLayout(null);
							ClearFrame.setBounds(700, 450, 300, 150);
							JButton ClearButton = new JButton("배틀 종료");
							ClearButton.setBounds(100, 20, 100, 50);
							ClearButton.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									BattleF.dispose();
									ClearFrame.dispose();
								}
							});
							ClearFrame.setVisible(true);
							ClearFrame.add(ClearButton);
						}
						MyStat.setText("<html>"+mainPok.name+ "<br/>레벨: "+mainPok.level+"<br/>체력: "+ mainPok.hp+"<html>");
						info.setText("<html>이름:"+mainPok.name+" 레벨: "+mainPok.level+"<br/>"+"HP/MAXHP:"+mainPok.hp+"/"+mainPok.maxhp+"<br/>공격력: "+mainPok.att+" 방어력: "+mainPok.def+"<br/>"+"<html>");
						insk.setText("<html>스킬1: "+ mainPok.SK1.Sname+" 데미지: "+mainPok.SK1.dmg+"<br/> 스킬2: "+ mainPok.SK2.Sname+" 데미지: "+mainPok.SK2.dmg+"<br/>"+"스킬3: "+ mainPok.SK3.Sname+" 데미지: "+mainPok.SK3.dmg+"<html>");
						battleFinish = true;
						break;
					}
					else if(!myTurn){
						sleep(500);
						int randNum = (int)(Math.random()*3);
						skillname = enemySkArr.get(randNum).Sname;
						int attackDmg = (enemySkArr.get(randNum).dmg - mainPok.def);
						if(attackDmg<1)attackDmg = 1;
						mainPok.hp -= attackDmg;
						eneAttMes = enemyPok.name + "의 " + skillname + " 공격!";
						battleMes = eneAttMes;
						BattleM.setText(battleMes);
						MyStat.setText("<html>"+mainPok.name+ "<br/>레벨: "+mainPok.level+"<br/>체력: "+ mainPok.hp+"<html>");
						info.setText("<html>이름:"+mainPok.name+" 레벨: "+mainPok.level+"<br/>"+"HP/MAXHP:"+mainPok.hp+"/"+mainPok.maxhp+"<br/>공격력: "+mainPok.att+" 방어력: "+mainPok.def+"<br/>"+"<html>");
						myTurn = true;
					}
					
					if(mainPok.hp<=0) {
						sleep(500);
						BattleM.setText("<html>배틀 종료.. <br/>" + mainPok.name +"(이)가 죽었습니다..<html>");
						battleFinish = true;
					}
					
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			
			myTurn = true;
			
			for(int i = 0; i<4;i++) {
				wpArray.remove(0);
				if(i>=1) enemySkArr.remove(0);
			}
			
			mapTownButton.setEnabled(true);
			mapWildButton.setEnabled(true);
			mapCenterButton.setEnabled(true);
			
			if(mainPok.level>=10) {
					if(!canfightBoss) {
					bossBox.setVisible(true);
					bossBox.setLocation(10,400);
					JFrame BossAlert = new JFrame("보스 출현");
					BossAlert.setBounds(400, 300, 400, 200);
					JLabel BossAlertMessage = new JLabel("<html>플레이어 포켓몬의 레벨이 10이 넘어<br/>"
							+ "전설의 포켓몬 뮤츠가 출현했습니다.<br/>"
							+"포켓몬 센터에서 치료를 하고 도전하세요!<html>");
					BossAlertMessage.setFont(new Font("Courier", Font.BOLD,12));
					BossAlertMessage.setBounds(50, 50, 300, 70);
					BossAlert.add(BossAlertMessage);
					BossAlert.setLayout(null);
					BossAlert.setVisible(true);
					canfightBoss = true;
				}
			}
		}
	}
	
	
	class BossBattle extends Thread{
		@Override
		public void run() {
			enemyPok = new BossPok();
			
			enemySkArr.add(enemyPok.SK1);
			enemySkArr.add(enemyPok.SK2);
			enemySkArr.add(enemyPok.SK3);
			
			JLabel enemyPokImg = new JLabel(enemyPok.image);
			enemyPokImg.setBounds(20, 270, 150, 163);
			BattleF.add(enemyPokImg);
			
			EnemyStat.setText("<html>"+enemyPok.name+"<br/>레벨: "+enemyPok.level+"<br/>체력: "+ enemyPok.hp+"<html>");
			
			BattleM.setText("최종보스 전설의 포켓몬 " + enemyPok.name+"가 나타났다!");
			
			while(!battleFinish) {
				mapTownButton.setEnabled(false);
				mapWildButton.setEnabled(false);
				mapCenterButton.setEnabled(false);
				try {
					sleep(500);
					if(myTurn) {
						MyPokSk1.setEnabled(true);
						MyPokSk2.setEnabled(true);
						MyPokSk3.setEnabled(true);
					}
					if(mainPok.hp<=0 || enemyPok.hp<=0) {
						sleep(1000);
						BattleM.setText("<html>배틀 종료! <br/>" + mainPok.name +"의 레벨이 1 올랐습니다!<html>");
						if(mainPok.hp>0) {
							mainPok.levelup();
							BattleM.setText("<html>배틀 종료! <br/>축하합니다 게임을 클리어 하셨습니다!<html>");
							JFrame ClearFrame = new JFrame("게임 클리어!");
							ClearFrame.setLayout(null);
							ClearFrame.setBounds(440, 350, 300, 200);
							JButton ClearExitButton = new JButton("게임 종료");
							ClearExitButton.setBounds(50, 75, 95, 50);
							ClearExitButton.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									PokemonsterMain.this.dispose();
									System.exit(0);
								}
							});
							JButton ContinueButton = new JButton("계속하기");
							ContinueButton.setBounds(150, 75, 95, 50);
							ContinueButton.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									ClearFrame.dispose();
									BattleF.dispose();
								}
							});
							ClearFrame.add(ClearExitButton);
							ClearFrame.add(ContinueButton);
							ClearFrame.setVisible(true);
						}
						else{
							BattleM.setText("<html>배틀 종료.. <br/>" + mainPok.name +"(이)가 죽었습니다..<html>");
						}
						battleFinish = true;
					}
					else if(!myTurn){
						sleep(500);
						int randNum = (int)(Math.random()*3);
						skillname = enemySkArr.get(randNum).Sname;
						mainPok.hp -= (enemySkArr.get(randNum).dmg - mainPok.def);
						eneAttMes = enemyPok.name + "의 " + skillname + " 공격!";
						battleMes = eneAttMes;
						BattleM.setText(battleMes);
						MyStat.setText("<html>"+mainPok.name+ "<br/>레벨: "+mainPok.level+"<br/>체력: "+ mainPok.hp+"<html>");
						info.setText("<html>이름:"+mainPok.name+" 레벨: "+mainPok.level+"<br/>"+"HP/MAXHP:"+mainPok.hp+"/"+mainPok.maxhp+"<br/>공격력: "+mainPok.att+" 방어력: "+mainPok.def+"<br/>"+"<html>");
						myTurn = true;
					}
					if(mainPok.hp<=0) {
						sleep(500);
						BattleM.setText("<html>배틀 종료.. <br/>" + mainPok.name +"(이)가 죽었습니다..<html>");
						battleFinish = true;
					}
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
			myTurn = true;
			
			for(int i = 0; i<3; i++) {
				enemySkArr.remove(0);
			}
			
			mapTownButton.setEnabled(true);
			mapWildButton.setEnabled(true);
			mapCenterButton.setEnabled(true);
		}
	}
	
	
	class PlayerMove implements KeyListener{
		@Override
		public void keyTyped(KeyEvent e) {
		}
		//키 누르면 이동
		@Override
		public void keyPressed(KeyEvent e) {
			x = playerBox.getX();
			y = playerBox.getY();
			int key = e.getKeyCode();
			switch(key){
			case 37:
				x-=30;
				//System.out.println("왼쪽");
				break;
			case 39:
				x+=30;
				//System.out.println("오른쪽");
				break;
			case 38:
				y-=30;
				//System.out.println("위");
				break;
			case 40:
				y+=30;
				//System.out.println("아래");
				break;
			}

			if(townScreen) {
				if(x<50) {
					x=50;
				}
				if(y<60) {
					y=60;
				}
				if(x>860-50-20) {
					x=860-50-20;
				}
				if(y>500-50-40) {
					y=500-50-40;
				}
				if(y>220 && y<350 && x>450 && x<700) {
					x = 550;
					y = 400;
				}
				if(y>70 && y<180 && x>150 && x<350) {
					x = 230;
					y = 200;
				}
				if(y>70 && y<180 && x>500 && x<750) {
					x = 630;
					y = 200;
				}
				if(!momMeeting) {
					if(x>380 && x<450 && y>180 && y<250) {
						ImageIcon paImage = new ImageIcon("image/파이리.png");
						ImageIcon ggoImage = new ImageIcon("image/꼬부기.png");
						ImageIcon leeImage = new ImageIcon("image/이상해씨.png");
						ImageIcon momImage = new ImageIcon("image/BigMom.png");
								
						SelPok = new JFrame("포켓몬 선택 창");
						JLabel momIm = new JLabel(momImage);
						momIm.setBounds(0, 0, 250,200);
						JTextArea momtalk = new JTextArea("어서 오렴~ "+"\n"+"기다리고 있었단다"+"\n"+"마음에 드는"+"\n"+"포켓몬을 고르렴");
						momtalk.setBounds(300,50,200,200);
						JTextArea patalk = new JTextArea("파이리");
						JTextArea ggotalk = new JTextArea("꼬부기");
						JTextArea leetalk = new JTextArea("이상해씨");
						patalk.setBounds(60, 350, 100, 100);
						ggotalk.setBounds(210, 350, 100, 100);
						leetalk.setBounds(350, 350, 100, 100);
						momtalk.setFont(new Font("Courier", Font.BOLD,20));
						patalk.setFont(new Font("Courier", Font.BOLD,20));
						patalk.setFont(new Font("Courier", Font.BOLD,20));
						ggotalk.setFont(new Font("Courier", Font.BOLD,20));
						leetalk.setFont(new Font("Courier", Font.BOLD,20));
								
						JButton pa1 = new JButton(paImage);
						JButton ggo1 = new JButton(ggoImage);
						JButton lee1 = new JButton(leeImage);
						pa1.setBackground(Color.white);
						ggo1.setBackground(Color.white);
						lee1.setBackground(Color.white);
						pa1.setBounds(40, 250, 105, 100);
						ggo1.setBounds(190, 250, 105, 100);
						lee1.setBounds(340, 250, 105, 100);

						SelPok.add(patalk);
						SelPok.add(ggotalk);
						SelPok.add(leetalk);
						SelPok.add(momtalk);
						SelPok.add(momIm);
						SelPok.add(pa1);
						SelPok.add(ggo1);
						SelPok.add(lee1);
						SelPok.getContentPane().setBackground(Color.white);
						SelPok.setLayout(null);
						SelPok.setBounds(420, 280, 500, 500);
						SelPok.setVisible(true);
						SelPok.setResizable(false);

						SelPok.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						
						pa1.addActionListener(new GivePIR());
						ggo1.addActionListener(new GiveGBG());
						lee1.addActionListener(new GiveLSHC());
						
						momMeeting = true;
												
					}
				}
			}		
			if(centerScreen) {
				if(x<65) {
					x=65;
				}
				if(y<250) {
					y=250;
				}
				if(x>640) {
					x=650;
				}
				if(y>530) {
					y=530;
				} if(y<255 & x>400 & x<500) {
					y= 260;
					x = 500;
					playerBox.setLocation(440, 530);
						centerF = new JFrame("포켓몬센터");
						
						JButton btn= new JButton("회복하기");
						
						ImageIcon hp = new ImageIcon("image/녹십자.png");
						JLabel hpL =new JLabel(hp);
						ImageIcon hpp = new ImageIcon("image/녹십자.png");
						JLabel hppL =new JLabel(hpp);
						
						ImageIcon gan = new ImageIcon("image/간호순.png");
						JLabel ganL =new JLabel(gan);
						
						//포켓몬 상태창
						JLabel myPokImg = new JLabel(mainPok.image);
						stat = new JLabel();
						
						stat.setText("<html>"+mainPok.name+ "<br/>레벨: "+mainPok.level+"<br/>체력: "+ mainPok.hp+"/"+mainPok.maxhp+"<html>");
						stat.setFont(new Font("굴림체",Font.BOLD,20));
						
						hpL.setBounds(40,190,60,60);
						hppL.setBounds(280,190,60,60);
						ganL.setBounds(20,20,347,165);
						btn.setBounds(140,190,100,60);
						myPokImg.setBounds(50, 300, 100, 100);
						stat.setBounds(230,250, 200, 200);
						centerF.setLayout(null);
						centerF.setBounds(440,350,400,500);
						
						centerF.add(hpL);
						centerF.add(hppL);
						centerF.add(ganL);
						centerF.add(btn);
						centerF.add(stat);
						centerF.add(myPokImg);
						centerF.setVisible(true);
						centerF.setResizable(false);
						
						btn.addActionListener(new Recover());
					}
				}
//			
			if(wildScreen) {
				if(x<150) {
					x=150;
				}
				if(y<20) {
					y=20;
				}
				if(x>860) {
					x=860;
				}
				if(y>535) {
					y=535;
				}
				
				if(y>350 && y<400 && x>500 && x<800) { 
					battleFinish = false;
					
					BattleF = new JFrame("배틀 시작");
					BattleF.setBounds(250, 250, 800, 500);
					BattleF.setLayout(null);
					
					JLabel myPokImg = new JLabel(mainPok.image);
					myPokImg.setBounds(50, 50, 100, 100);
					BattleF.add(myPokImg);
					
					wpArray.add(new GGorat());
					wpArray.add(new Catupi());
					wpArray.add(new Eve());
					wpArray.add(new Gugu());
					
					MyPokSk1 = new JButton(mainPok.SK1.Sname);
					MyPokSk1.setBounds(550, 50, 150, 100);
					MyPokSk1.setEnabled(false);
					MyPokSk1.addActionListener(new MyPokSkill());
					BattleF.add(MyPokSk1);
					
					MyPokSk2 = new JButton(mainPok.SK2.Sname);
					MyPokSk2.setBounds(550, 175, 150, 100);
					MyPokSk2.setEnabled(false);
					MyPokSk2.addActionListener(new MyPokSkill());
					BattleF.add(MyPokSk2);
					
					BattleM = new JLabel();
					BattleM.setBounds(50, 180, 350, 100);
					BattleM.setFont(new Font("굴림체",Font.BOLD,20));
					BattleF.add(BattleM);
					
					EnemyStat= new JLabel();
					EnemyStat.setBounds(200, 250, 200, 200);
					EnemyStat.setFont(new Font("굴림체",Font.BOLD,20));
					BattleF.add(EnemyStat);
					
					MyPokSk3 = new JButton(mainPok.SK3.Sname);
					MyPokSk3.setBounds(550, 300, 150, 100);
					MyPokSk3.setEnabled(false);
					MyPokSk3.addActionListener(new MyPokSkill());
					BattleF.add(MyPokSk3);
					
					
					MyStat = new JLabel();
					MyStat.setBounds(200, 0, 200, 200);
					MyStat.setText("<html>"+mainPok.name+ "<br/>레벨: "+mainPok.level+"<br/>체력: "+ mainPok.hp+"<html>");
					MyStat.setFont(new Font("굴림체",Font.BOLD,20));
					BattleF.add(MyStat);
					
					myTurn = true;
					
					BattleF.setVisible(true);

					bt = new Battle();
					bt.start();
				}
				
				if(canfightBoss) {
					if(y>400 && y<440 && x>130 && x<170) {
						x = 170;
						y = 440;
						battleFinish = false;
						
						BattleF = new JFrame("배틀 시작");
						BattleF.setBounds(250, 250, 800, 500);
						BattleF.setLayout(null);
						
						JLabel myPokImg = new JLabel(mainPok.image);
						myPokImg.setBounds(50, 50, 100, 100);
						BattleF.add(myPokImg);
						
						MyPokSk1 = new JButton(mainPok.SK1.Sname);
						MyPokSk1.setBounds(550, 50, 150, 100);
						MyPokSk1.setEnabled(false);
						MyPokSk1.addActionListener(new MyPokSkill());
						BattleF.add(MyPokSk1);
						
						MyPokSk2 = new JButton(mainPok.SK2.Sname);
						MyPokSk2.setBounds(550, 175, 150, 100);
						MyPokSk2.setEnabled(false);
						MyPokSk2.addActionListener(new MyPokSkill());
						BattleF.add(MyPokSk2);
						
						MyPokSk3 = new JButton(mainPok.SK3.Sname);
						MyPokSk3.setBounds(550, 300, 150, 100);
						MyPokSk3.setEnabled(false);
						MyPokSk3.addActionListener(new MyPokSkill());
						BattleF.add(MyPokSk3);
						
						BattleM = new JLabel();
						BattleM.setBounds(50, 180, 600, 100);
						BattleM.setFont(new Font("굴림체",Font.BOLD,20));
						BattleF.add(BattleM);
						
						EnemyStat= new JLabel();
						EnemyStat.setBounds(200, 250, 200, 200);
						EnemyStat.setFont(new Font("굴림체",Font.BOLD,20));
						BattleF.add(EnemyStat);
						
						MyStat = new JLabel();
						MyStat.setBounds(200, 0, 200, 200);
						MyStat.setText("<html>"+mainPok.name+ "<br/>레벨: "+mainPok.level+"<br/>체력: "+ mainPok.hp+"<html>");
						MyStat.setFont(new Font("굴림체",Font.BOLD,20));
						BattleF.add(MyStat);
						
						myTurn = true;
						
						BattleF.setVisible(true);

						bossbt = new BossBattle();
						bossbt.start();
					}
				}
			}
			
			playerBox.setLocation(x,y);
		}
		@Override
		public void keyReleased(KeyEvent e) {
		}
	}
	
	
	class GivePIR implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			mainPok = new Pa();
			ImageIcon paImageC = new ImageIcon("image/Big파이리.png");
			InfoFrame = new JFrame("포켓몬 정보");
			InfoFrame.getContentPane().setBackground(Color.white);
			InfoFrame.setBounds(1150, 100, 700, 320);
			pachoice = new JLabel(paImageC);
			pachoice.setBounds(0, 0, 300, 300);
			info = new JLabel("<html>이름:"+mainPok.name+" 레벨: "+mainPok.level+"<br/>"+"HP/MAXHP:"+mainPok.hp+"/"+mainPok.maxhp+"<br/>공격력: "+mainPok.att+" 방어력: "+mainPok.def+"<br/>"+"<html>");
			info.setBounds(300, -50, 200, 200);
			info.setFont(new Font("Courier", Font.BOLD,20));
			insk = new JLabel("<html>스킬1: "+ mainPok.SK1.Sname+" 데미지: "+mainPok.SK1.dmg+"<br/> 스킬2: "+ mainPok.SK2.Sname+" 데미지: "+mainPok.SK2.dmg+"<br/>"+"스킬3: "+ mainPok.SK3.Sname+" 데미지: "+mainPok.SK3.dmg+"<html>");
			insk.setBounds(300, 60, 520, 200);
			insk.setFont(new Font("Courier", Font.BOLD,20));
			InfoFrame.add(pachoice);
			InfoFrame.add(info);
			InfoFrame.add(insk);
			InfoFrame.setLayout(null);
			InfoFrame.setVisible(true);
			
			mapTownButton.setEnabled(true);
			mapCenterButton.setEnabled(true);
			mapWildButton.setEnabled(true);
			
			SelPok.dispose();
		}
	}
	
	class GiveGBG implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			mainPok = new Ggobu();
			ImageIcon paImageC = new ImageIcon("image/Big꼬부기.jpg");
			InfoFrame = new JFrame("포켓몬 정보");
			InfoFrame.getContentPane().setBackground(Color.white);
			InfoFrame.setBounds(1150, 100, 700, 320);
			pachoice = new JLabel(paImageC);
			pachoice.setBounds(0, 0, 300, 300);
			info = new JLabel("<html>이름:"+mainPok.name+" 레벨: "+mainPok.level+"<br/>"+"HP/MAXHP:"+mainPok.hp+"/"+mainPok.maxhp+"<br/>공격력: "+mainPok.att+" 방어력: "+mainPok.def+"<br/>"+"<html>");
			info.setBounds(300, -50, 250, 200);
			info.setFont(new Font("Courier", Font.BOLD,20));
			insk = new JLabel("<html>스킬1: "+ mainPok.SK1.Sname+" 데미지: "+mainPok.SK1.dmg+"<br/> 스킬2: "+ mainPok.SK2.Sname+" 데미지: "+mainPok.SK2.dmg+"<br/>"+"스킬3: "+ mainPok.SK3.Sname+" 데미지: "+mainPok.SK3.dmg+"<html>");
			insk.setBounds(300, 70, 520, 200);
			insk.setFont(new Font("Courier", Font.BOLD,20));
			InfoFrame.add(pachoice);
			InfoFrame.add(info);
			InfoFrame.add(insk);
			InfoFrame.setLayout(null);
			InfoFrame.setVisible(true);
			
			mapTownButton.setEnabled(true);
			mapCenterButton.setEnabled(true);
			mapWildButton.setEnabled(true);
			
			SelPok.dispose();

		}
	}
	
	class GiveLSHC implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			mainPok = new Lee();
			ImageIcon paImageC = new ImageIcon("image/Big이상해씨.jpg");
			InfoFrame = new JFrame("포켓몬 정보");
			InfoFrame.getContentPane().setBackground(Color.white);
			InfoFrame.setBounds(1150, 100, 700, 320);
			pachoice = new JLabel(paImageC);
			pachoice.setBounds(0, 0, 300, 300);
			info = new JLabel("<html>이름:"+mainPok.name+" 레벨: "+mainPok.level+"<br/>"+"HP/MAXHP:"+mainPok.hp+"/"+mainPok.maxhp+"<br/>공격력: "+mainPok.att+" 방어력: "+mainPok.def+"<br/>"+"<html>");
			info.setBounds(300, -50, 250, 200);
			info.setFont(new Font("Courier", Font.BOLD,20));
			insk = new JLabel("<html>스킬1: "+ mainPok.SK1.Sname+" 데미지: "+mainPok.SK1.dmg+"<br/> 스킬2: "+ mainPok.SK2.Sname+" 데미지: "+mainPok.SK2.dmg+"<br/>"+"스킬3: "+ mainPok.SK3.Sname+" 데미지: "+mainPok.SK3.dmg+"<html>");
			insk.setBounds(300, 70, 520, 200);
			insk.setFont(new Font("Courier", Font.BOLD,20));
			InfoFrame.add(pachoice);
			InfoFrame.add(info);
			InfoFrame.add(insk);
			InfoFrame.setLayout(null);
			InfoFrame.setVisible(true);
			
			mapTownButton.setEnabled(true);
			mapCenterButton.setEnabled(true);
			mapWildButton.setEnabled(true);
			
			SelPok.dispose();
		}
	}
	
	class Recover implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			mainPok.hp = mainPok.maxhp;
			playerBox.setLocation(440,530);
			stat.setText("<html>"+mainPok.name+ "<br/>레벨: "+mainPok.level+"<br/>체력: "+ mainPok.hp+"/"+mainPok.maxhp+"<html>");
			info.setText("<html>이름:"+mainPok.name+" 레벨: "+mainPok.level+"<br/>"+"HP/MAXHP:"+mainPok.hp+"/"+mainPok.maxhp+"<br/>공격력: "+mainPok.att+" 방어력: "+mainPok.def+"<br/>"+"<html>");
		}
	}
	
	class ButtonInfoPage implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton inputPok = (JButton)e.getSource();
			
			if(inputPok.getText().equals("파이리")) {
				
			}
		}
	}
	
	class MyPokSkill implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton Input = (JButton)e.getSource();
			
			if(Input.getText().equals(mainPok.SK1.Sname)) {
				int attackDmg = (mainPok.SK1.dmg - enemyPok.def);
				if(attackDmg<1) attackDmg =1;
				enemyPok.hp -= attackDmg;
				skillname = mainPok.SK1.Sname;
			}
			
			else if(Input.getText().equals(mainPok.SK2.Sname)) {
				int attackDmg = (mainPok.SK2.dmg - enemyPok.def);
				if(attackDmg<1) attackDmg =1;
				enemyPok.hp -= attackDmg;
				skillname = mainPok.SK2.Sname;
			}
			
			else if(Input.getText().equals(mainPok.SK3.Sname)) {
				int attackDmg = (mainPok.SK3.dmg - enemyPok.def);
				if(attackDmg<1) attackDmg =1;
				enemyPok.hp -= attackDmg;
				skillname = mainPok.SK3.Sname;
			}
			
			myAttMes = mainPok.name + "의 " + skillname + " 공격!";
			battleMes = myAttMes;
			
			MyPokSk1.setEnabled(false);
			MyPokSk2.setEnabled(false);
			MyPokSk3.setEnabled(false);
			
			BattleM.setText(battleMes);
			EnemyStat.setText("<html>"+enemyPok.name+"<br/>레벨: "+enemyPok.level+"<br/>체력: "+ enemyPok.hp+"<html>");
			
			myTurn = false;
		}
	}
	
	String myAttMes, eneAttMes, skillname, battleMes;
	
	//변수들 선언
	JFrame SelPok,centerF, BattleF, InfoFrame;
	ArrayList<WildPokemon> wpArray = new ArrayList<WildPokemon>();
	ArrayList<Pokemon.Skill> enemySkArr = new ArrayList<Pokemon.Skill>();
	JButton mapGymButton, mapTownButton, mapCenterButton, mapWildButton;
	JButton titleButton;
	JButton MyPokSk1, MyPokSk2, MyPokSk3;
	JPanel titlePanel;
	JPanel town, wild, center;
	JLabel townLabel,wildLabel,centerLabel, towntalk, sentalk, wildtalk;
	JLabel playerBox, momBox, ha, titleLabel1, bossBox;
	JLabel MyStat, EnemyStat, BattleM, stat, pachoice, info, insk;
	ImageIcon tilteImage;
	ImageIcon townImage;
	boolean townScreen = false;
	boolean centerScreen = false;
	boolean wildScreen = false;
	boolean momMeeting = false;
	boolean battleFinish = false;
	boolean canfightBoss = false;
	boolean myTurn;
	int x = 300,y = 600;
	Pokemon mainPok;
	WildPokemon enemyPok;
	int myPokDmg;
	Battle bt;
	BossBattle bossbt;

	public PokemonsterMain(String title) {
		super(title);
		setLayout(null);
		setBounds(200, 100, 920, 930);
		
		titleLabel1 = new JLabel("타이틀 화면 클릭 시 게임 시작!");
		titleLabel1.setBounds(250,650,700,100);
		titleLabel1.setFont(new Font("Courier", Font.BOLD,30));
		add(titleLabel1);
		
//		//타이틀 화면
		titlePanel = new JPanel();
		titlePanel.setLayout(new BorderLayout());
		titlePanel.setBounds(0, 0, 900, 600);
		tilteImage = new ImageIcon("image/title.png");
		titleButton = new JButton(tilteImage);
		titlePanel.add(titleButton);
		add(titlePanel);
//		//누르면 타이틀 화면 스킵
		titleButton.addActionListener(new SkipTitle());
		
		//플레이어박스
		playerBox = new JLabel(new ImageIcon("image/주인공.png"));
		playerBox.setBounds(x, y, 30,30);
		playerBox.setOpaque(true);
		add(playerBox);
		playerBox.setVisible(false);
		
		//엄마NPC
		momBox = new JLabel(new ImageIcon("image/엄마.png"));
		momBox.setBounds(x+50, y, 30,30);
		momBox.setOpaque(true);
		add(momBox);
		momBox.setVisible(false);
		addKeyListener(new PlayerMove());
		
		//간호사
		ha = new JLabel(new ImageIcon("image/ha.JPG"));
		ha.setBounds(440, 20, 50,50);
		ha.setOpaque(false);	
		add(ha);
		ha.setVisible(false);
				
		ImageIcon bossImg = new ImageIcon("image/뮤츠150.jpg");
		bossBox = new JLabel(bossImg);
		bossBox.setBounds(10, 400, 150, 162);
		bossBox.setOpaque(true);
		add(bossBox);
		bossBox.setVisible(false);
	
		//마을설정
		town = new JPanel();
		town.setBounds(0,0, 900, 600);
		town.setLayout(null);
		
		townLabel = new JLabel(new ImageIcon("image/town1.jpg"));
		townLabel.setBounds(0, 0, 900, 570);
		townLabel.setOpaque(false);
		town.add(townLabel);
		
		//사냥터
		wild = new JPanel();
		wild.setBounds(0,0, 900, 600);
		wild.setLayout(null);
		wild.setVisible(false);
		add(wild);
		
		wildLabel = new JLabel(new ImageIcon("image/사냥터3.png"));
		wildLabel.setBounds(0, 0, 900, 570);
		wildLabel.setOpaque(false);
		wild.add(wildLabel);
//		
		//센터
		center = new JPanel();
		center.setBounds(0,0, 900, 600);
		center.setLayout(null);
		center.setVisible(false);
		add(center);
		
		centerLabel = new JLabel(new ImageIcon("image/center.png"));
		centerLabel.setBounds(0, 0, 900,570);
		centerLabel.setOpaque(false);
		center.add(centerLabel);
		
		add(town);
		
		ImageIcon senter = new ImageIcon("image/smallsenter.png");
		ImageIcon wild = new ImageIcon("image/smallWild.png");
		ImageIcon town = new ImageIcon("image/smalltown.jpg");
		
		sentalk = new JLabel("포켓몬 센터 이동");
		wildtalk = new JLabel("사냥터 이동");
		towntalk = new JLabel("태초 마을 이동");
		
		sentalk.setBounds(100, 620, 130, 23);
		wildtalk.setBounds(410, 620, 130, 23);
		towntalk.setBounds(710, 620, 130, 23);
		
		sentalk.setFont(new Font("Courier", Font.BOLD,13));
		towntalk.setFont(new Font("Courier", Font.BOLD,13));
		wildtalk.setFont(new Font("Courier", Font.BOLD,13));
		
		//포켓몬센터
		mapCenterButton = new JButton(senter);
		mapCenterButton.setBounds(50, 650, 200, 200);
		//사냥터
		mapWildButton = new JButton(wild);
		mapWildButton.setBounds(350, 650, 200, 200);
		
		//태초마을
		mapTownButton = new JButton(town);
		mapTownButton.setBounds(650, 650, 200, 200);
		
		mapTownButton.setEnabled(true);
		mapCenterButton.setEnabled(true);
		mapWildButton.setEnabled(true);
		
		//버튼 누르면 이벤트
		mapCenterButton.addActionListener(new GoCenter());
		mapTownButton.addActionListener(new GoTown());
		mapWildButton.addActionListener(new GoWild());
		
		JMenuBar menubar = new JMenuBar();
		JMenu menu = new JMenu("제어");
		setJMenuBar(menubar);
		menubar.add(menu);
		JMenuItem showInfo = new JMenuItem("내 포켓몬 상태 보기");
		menu.add(showInfo);
		showInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(InfoFrame!=null)
				InfoFrame.setVisible(true);
			}
		});
		JMenuItem ExitGame = new JMenuItem("게임 종료");
		menu.add(ExitGame);
		ExitGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		setVisible(true);
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new PokemonsterMain("포켓몬스터 그린");
	}
}
