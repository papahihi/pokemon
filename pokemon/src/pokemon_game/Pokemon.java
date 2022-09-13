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
		name = "���̸�";
		image = new ImageIcon("image/���̸�.png");
		SK1 = new Skill("������", 5);
		SK2 = new Skill("�Ҳɼη�", 10);
		SK3 = new Skill("ȭ�����", 15);
	}
	
	
}

class Ggobu extends Pokemon{
	@Override
	public void set() {
		name ="���α�";
		image = new ImageIcon("image/���α�.png");
		SK1 = new Skill("�����ġ��",5);
		SK2 = new Skill("������",10);
		SK3 = new Skill("�����ĵ�",15);
	}
}

class Lee extends Pokemon{
	@Override
	public void set() {
		name ="�̻��ؾ�";
		image = new ImageIcon("image/�̻��ؾ�.png");
		SK1 = new Skill("����ä��",5);
		SK2 = new Skill("�ٳ� ������",10);
		SK3 = new Skill("�ֶ��",15);
	}
}

