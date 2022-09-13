package pokemon_game;

import javax.swing.ImageIcon;

class WildPokemon extends Pokemon{
	
	public WildPokemon() {
		setW();
		set();
	}
	
	public void setW() {
		level = (int)(Math.random()*5) + 1;
		hp = 30 + level*5;
		maxhp = 30;
		att = level;
		def = level;
	}
	
}
class GGorat extends WildPokemon{
	@Override
	public void set(){
		name = "����";
		image = new ImageIcon("image/����.png");
		SK1 = new Skill("�����ġ��", 5);
		SK2 = new Skill("������ȭ", 10);
		SK3 = new Skill("�ʻ�մ�", 15);
	}
}
class Catupi extends WildPokemon{
	@Override
	public void set(){
		name = "ĳ����";
		image = new ImageIcon("image/ĳ����.png");
		SK1 = new Skill("�����ġ��", 5);
		SK2 = new Skill("�ǻձ�", 10);
		SK3 = new Skill("��������", 15);
	}
}
class Gugu extends WildPokemon{
	@Override
	public void set(){
		name = "����";
		image = new ImageIcon("image/����.png");
		SK1 = new Skill("�����ġ��", 5);
		SK2 = new Skill("�ٶ�����Ű��", 10);
		SK3 = new Skill("�������", 15);	
	}
}
class Eve extends WildPokemon{
	@Override
	public void set(){
		name = "�̺���";
		image = new ImageIcon("image/�̺���.png");
		SK1 = new Skill("�����ġ��", 5);
		SK2 = new Skill("������ȭ", 10);
		SK3 = new Skill("���ǵ� ��Ÿ", 15);
	}
}

class BossPok extends WildPokemon{
	@Override
	public void setW() {
		level = 50;
		hp = 150;
		maxhp = 150;
		att = 10;
		def = 10;
	}
	
	@Override
	public void set() {
		name = "����";
		image = new ImageIcon("image/����150.jpg");
		SK1 = new Skill("������", 10);
		SK2 = new Skill("������Ű�׽ý�", 20);
		SK3 = new Skill("���� �ı�", 30);
	}
}