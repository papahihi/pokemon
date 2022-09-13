package pokemon_game;

import javax.swing.ImageIcon;

class Pokemon {
	
		String name;
		int level=5;
		int hp= 50; 
		int maxhp = hp;
		int att= 16;
		int def=5;
		ImageIcon image;
		
		class Skill {
			String Sname;
			int dmg=10;
			
			public Skill(String sname, int dmg) {
				Sname = sname;
				this.dmg = dmg+att;
			}
		}
		
		Skill SK1, SK2, SK3;
		
		void levelup() {
			level += 1;
			hp += 1;
			maxhp += 1;
			att +=1;
			def += 1;
			SK1.dmg += 1;
			SK2.dmg += 1;
			SK3.dmg += 1;
		}
		
		public void set() {}
		
		public Pokemon() {
			set();
		}
}

class Pa extends Pokemon{
	@Override
	public void set() {
		name = "ÆÄÀÌ¸®";
		image = new ImageIcon("image/ÆÄÀÌ¸®.png");
		SK1 = new Skill("ÇÒÄû±â", 5);
		SK2 = new Skill("ºÒ²É¼Î·¹", 10);
		SK3 = new Skill("È­¿°¹æ»ç", 15);
	}
	
	
}

class Ggobu extends Pokemon{
	@Override
	public void set() {
		name ="²¿ºÎ±â";
		image = new ImageIcon("image/²¿ºÎ±â.png");
		SK1 = new Skill("¸öÅë¹ÚÄ¡±â",5);
		SK2 = new Skill("¹°´ëÆ÷",10);
		SK3 = new Skill("¹°ÀÇÆÄµ¿",15);
	}
}

class Lee extends Pokemon{
	@Override
	public void set() {
		name ="ÀÌ»óÇØ¾¾";
		image = new ImageIcon("image/ÀÌ»óÇØ¾¾.png");
		SK1 = new Skill("µ¢ÄðÃ¤Âï",5);
		SK2 = new Skill("ÀÙ³¯ °¡¸£±â",10);
		SK3 = new Skill("¼Ö¶óºö",15);
	}
}

