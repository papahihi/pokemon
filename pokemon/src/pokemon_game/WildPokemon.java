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
		name = "꼬렛";
		image = new ImageIcon("image/꼬렛.png");
		SK1 = new Skill("몸통박치기", 5);
		SK2 = new Skill("전광석화", 10);
		SK3 = new Skill("필살앞니", 15);
	}
}
class Catupi extends WildPokemon{
	@Override
	public void set(){
		name = "캐터피";
		image = new ImageIcon("image/캐터피.png");
		SK1 = new Skill("몸통박치기", 5);
		SK2 = new Skill("실뿜기", 10);
		SK3 = new Skill("벌레먹음", 15);
	}
}
class Gugu extends WildPokemon{
	@Override
	public void set(){
		name = "구구";
		image = new ImageIcon("image/구구.png");
		SK1 = new Skill("몸통박치기", 5);
		SK2 = new Skill("바람일으키기", 10);
		SK3 = new Skill("에어슬래시", 15);	
	}
}
class Eve extends WildPokemon{
	@Override
	public void set(){
		name = "이브이";
		image = new ImageIcon("image/이브이.png");
		SK1 = new Skill("몸통박치기", 5);
		SK2 = new Skill("전광석화", 10);
		SK3 = new Skill("스피드 스타", 15);
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
		name = "뮤츠";
		image = new ImageIcon("image/뮤츠150.jpg");
		SK1 = new Skill("염동력", 10);
		SK2 = new Skill("사이코키네시스", 20);
		SK3 = new Skill("정신 파괴", 30);
	}
}