package ncu.csie.game.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	public static BufferedImage grass, water, fire, stone, lawn, tree, back_img, end_back_img,game_start;
	public static BufferedImage[] actor1_down, actor1_up, actor1_left, actor1_right;
	public static BufferedImage[] actor2_down, actor2_up, actor2_left, actor2_right;
	public static BufferedImage[] actor3_down, actor3_up, actor3_left, actor3_right;
	public static BufferedImage[] actor4_down, actor4_up, actor4_left, actor4_right;
	public static BufferedImage[] actor5_down, actor5_up, actor5_left, actor5_right;
	public static BufferedImage[] actor6_down, actor6_up, actor6_left, actor6_right;
	public static BufferedImage[] FireDragon_left, FireDragon_right, Piplup_left, Piplup_right, WalkingGrass_left, WalkingGrass_right, LightningBird_left, LightningBird_right;
	public static BufferedImage[] btn_start, btn_actor ,btn_instruct,btn_option,btn_exit,btn_right, btn_left, btn_back;
	public static BufferedImage[] title_img;
	public static BufferedImage flash , crystallize , ghost_walk , snowball,snowballEntity ,ultralight;
	public static BufferedImage[] rollingBall;
	public static BufferedImage[] illustrations;
	public static BufferedImage[] ultra_ani_right , ultra_ani_down ,ultra_ani_up , ultra_ani_left;
	public static BufferedImage[] choose_Asuna,choose_Hao,choose_Hasaki,choose_Jade,choose_Yuki,choose_Sai;
	public static BufferedImage[] crystallizeVertical , crystallizeHori;
	public static BufferedImage flashDisplay;
	public static BufferedImage bagLoc_img,mapall;

	public static void init(){
		
		actor1_down =  new BufferedImage[4];
		actor1_up =  new BufferedImage[4];
		actor1_left =  new BufferedImage[4];
		actor1_right =  new BufferedImage[4];
		
		actor2_down =  new BufferedImage[4];
		actor2_up =  new BufferedImage[4];
		actor2_left =  new BufferedImage[4];
		actor2_right =  new BufferedImage[4];
		
		actor3_down =  new BufferedImage[4];
		actor3_up =  new BufferedImage[4];
		actor3_left =  new BufferedImage[4];
		actor3_right =  new BufferedImage[4];
		
		actor4_down =  new BufferedImage[4];
		actor4_up =  new BufferedImage[4];
		actor4_left =  new BufferedImage[4];
		actor4_right =  new BufferedImage[4];
		
		actor5_down =  new BufferedImage[4];
		actor5_up =  new BufferedImage[4];
		actor5_left =  new BufferedImage[4];
		actor5_right =  new BufferedImage[4];
		
		actor6_down =  new BufferedImage[4];
		actor6_up =  new BufferedImage[4];
		actor6_left =  new BufferedImage[4];
		actor6_right =  new BufferedImage[4];
		
		FireDragon_left = new BufferedImage[4];
		FireDragon_right = new BufferedImage[4];
		Piplup_left = new BufferedImage[4];
		Piplup_right = new BufferedImage[4];
		WalkingGrass_left = new BufferedImage[4];
		WalkingGrass_right = new BufferedImage[4];
		LightningBird_left = new BufferedImage[4];
		LightningBird_right = new BufferedImage[4];

		btn_start = new BufferedImage[2];
		btn_instruct = new BufferedImage[2];
		btn_actor = new BufferedImage[2];
		btn_option = new BufferedImage[2];
		btn_exit = new BufferedImage[2];
		btn_right= new BufferedImage[2];
		btn_left= new BufferedImage[2];
		btn_back= new BufferedImage[2];
		title_img=new BufferedImage[2];
		choose_Asuna=new BufferedImage[2];
		choose_Hao=new BufferedImage[2];
		choose_Hasaki=new BufferedImage[2];
		choose_Jade=new BufferedImage[2];
		choose_Yuki=new BufferedImage[2];
		choose_Sai=new BufferedImage[2];
		
		rollingBall = new BufferedImage[2];
		illustrations= new BufferedImage[6];
		
		ultra_ani_right = new BufferedImage[17];
		ultra_ani_left = new BufferedImage[17];
		ultra_ani_down = new BufferedImage[17];
		ultra_ani_up = new BufferedImage[17];
		
		crystallizeVertical = new BufferedImage[18];
		crystallizeHori = new BufferedImage[18];
		
		
		btn_start[0] = ImageLoader.loadImage("/textures/btn1.png");
		btn_start[1] = ImageLoader.loadImage("/textures/btn2.png");

		btn_instruct[0] = ImageLoader.loadImage("/textures/btn3.png");
		btn_instruct[1] = ImageLoader.loadImage("/textures/btn4.png");
		
		btn_actor[0] = ImageLoader.loadImage("/textures/btn5.png");
		btn_actor[1] = ImageLoader.loadImage("/textures/btn6.png");
		
		btn_option[0] = ImageLoader.loadImage("/textures/btn7.png");
		btn_option[1] = ImageLoader.loadImage("/textures/btn8.png");
		
		btn_exit[0] = ImageLoader.loadImage("/textures/btn9.png");
		btn_exit[1] = ImageLoader.loadImage("/textures/btn10.png");
		
		btn_right[0] = ImageLoader.loadImage("/textures/right1.png");
		btn_right[1] = ImageLoader.loadImage("/textures/right2.png");
		
		btn_left[0] = ImageLoader.loadImage("/textures/left1.png");
		btn_left[1] = ImageLoader.loadImage("/textures/left2.png");
		
		btn_back[0] = ImageLoader.loadImage("/textures/back1.png");
		btn_back[1] = ImageLoader.loadImage("/textures/back2.png");
		
		actor1_down[0] = ImageLoader.loadImage("/character/Asuna_d_1.png");
		actor1_down[1] = ImageLoader.loadImage("/character/Asuna_d_2.png");
		actor1_down[2] = ImageLoader.loadImage("/character/Asuna_d_1.png");
		actor1_down[3] = ImageLoader.loadImage("/character/Asuna_d_3.png");
		actor1_up[0] = ImageLoader.loadImage("/character/Asuna_u_1.png");
		actor1_up[1] = ImageLoader.loadImage("/character/Asuna_u_2.png");
		actor1_up[2] = ImageLoader.loadImage("/character/Asuna_u_1.png");
		actor1_up[3] = ImageLoader.loadImage("/character/Asuna_u_3.png");
		actor1_left[0] = ImageLoader.loadImage("/character/Asuna_l_1.png");
		actor1_left[1] = ImageLoader.loadImage("/character/Asuna_l_2.png");
		actor1_left[2] = ImageLoader.loadImage("/character/Asuna_l_1.png");
		actor1_left[3] = ImageLoader.loadImage("/character/Asuna_l_3.png");
		actor1_right[0] = ImageLoader.loadImage("/character/Asuna_r_1.png");
		actor1_right[1] = ImageLoader.loadImage("/character/Asuna_r_2.png");
		actor1_right[2] = ImageLoader.loadImage("/character/Asuna_r_1.png");
		actor1_right[3] = ImageLoader.loadImage("/character/Asuna_r_3.png");
		
		actor2_down[0] = ImageLoader.loadImage("/character/Hao_d_1.png");
		actor2_down[1] = ImageLoader.loadImage("/character/Hao_d_2.png");
		actor2_down[2] = ImageLoader.loadImage("/character/Hao_d_1.png");
		actor2_down[3] = ImageLoader.loadImage("/character/Hao_d_3.png");
		actor2_up[0] = ImageLoader.loadImage("/character/Hao_u_1.png");
		actor2_up[1] = ImageLoader.loadImage("/character/Hao_u_2.png");
		actor2_up[2] = ImageLoader.loadImage("/character/Hao_u_1.png");
		actor2_up[3] = ImageLoader.loadImage("/character/Hao_u_3.png");
		actor2_left[0] = ImageLoader.loadImage("/character/Hao_l_1.png");
		actor2_left[1] = ImageLoader.loadImage("/character/Hao_l_2.png");
		actor2_left[2] = ImageLoader.loadImage("/character/Hao_l_1.png");
		actor2_left[3] = ImageLoader.loadImage("/character/Hao_l_3.png");
		actor2_right[0] = ImageLoader.loadImage("/character/Hao_r_1.png");
		actor2_right[1] = ImageLoader.loadImage("/character/Hao_r_2.png");
		actor2_right[2] = ImageLoader.loadImage("/character/Hao_r_1.png");
		actor2_right[3] = ImageLoader.loadImage("/character/Hao_r_3.png");
		
		actor3_down[0] = ImageLoader.loadImage("/character/Hasaki_d_1.png");
		actor3_down[1] = ImageLoader.loadImage("/character/Hasaki_d_2.png");
		actor3_down[2] = ImageLoader.loadImage("/character/Hasaki_d_1.png");
		actor3_down[3] = ImageLoader.loadImage("/character/Hasaki_d_3.png");
		actor3_up[0] = ImageLoader.loadImage("/character/Hasaki_u_1.png");
		actor3_up[1] = ImageLoader.loadImage("/character/Hasaki_u_2.png");
		actor3_up[2] = ImageLoader.loadImage("/character/Hasaki_u_1.png");
		actor3_up[3] = ImageLoader.loadImage("/character/Hasaki_u_3.png");
		actor3_left[0] = ImageLoader.loadImage("/character/Hasaki_l_1.png");
		actor3_left[1] = ImageLoader.loadImage("/character/Hasaki_l_2.png");
		actor3_left[2] = ImageLoader.loadImage("/character/Hasaki_l_1.png");
		actor3_left[3] = ImageLoader.loadImage("/character/Hasaki_l_3.png");
		actor3_right[0] = ImageLoader.loadImage("/character/Hasaki_r_1.png");
		actor3_right[1] = ImageLoader.loadImage("/character/Hasaki_r_2.png");
		actor3_right[2] = ImageLoader.loadImage("/character/Hasaki_r_1.png");
		actor3_right[3] = ImageLoader.loadImage("/character/Hasaki_r_3.png");
		
		actor4_down[0] = ImageLoader.loadImage("/character/Jade_d_1.png");
		actor4_down[1] = ImageLoader.loadImage("/character/Jade_d_2.png");
		actor4_down[2] = ImageLoader.loadImage("/character/Jade_d_1.png");
		actor4_down[3] = ImageLoader.loadImage("/character/Jade_d_3.png");
		actor4_up[0] = ImageLoader.loadImage("/character/Jade_u_1.png");
		actor4_up[1] = ImageLoader.loadImage("/character/Jade_u_2.png");
		actor4_up[2] = ImageLoader.loadImage("/character/Jade_u_1.png");
		actor4_up[3] = ImageLoader.loadImage("/character/Jade_u_3.png");
		actor4_left[0] = ImageLoader.loadImage("/character/Jade_l_1.png");
		actor4_left[1] = ImageLoader.loadImage("/character/Jade_l_2.png");
		actor4_left[2] = ImageLoader.loadImage("/character/Jade_l_1.png");
		actor4_left[3] = ImageLoader.loadImage("/character/Jade_l_3.png");
		actor4_right[0] = ImageLoader.loadImage("/character/Jade_r_1.png");
		actor4_right[1] = ImageLoader.loadImage("/character/Jade_r_2.png");
		actor4_right[2] = ImageLoader.loadImage("/character/Jade_r_1.png");
		actor4_right[3] = ImageLoader.loadImage("/character/Jade_r_3.png");
		
		actor5_down[0] = ImageLoader.loadImage("/character/Sai_d_1.png");
		actor5_down[1] = ImageLoader.loadImage("/character/Sai_d_2.png");
		actor5_down[2] = ImageLoader.loadImage("/character/Sai_d_1.png");
		actor5_down[3] = ImageLoader.loadImage("/character/Sai_d_3.png");
		actor5_up[0] = ImageLoader.loadImage("/character/Sai_u_1.png");
		actor5_up[1] = ImageLoader.loadImage("/character/Sai_u_2.png");
		actor5_up[2] = ImageLoader.loadImage("/character/Sai_u_1.png");
		actor5_up[3] = ImageLoader.loadImage("/character/Sai_u_3.png");
		actor5_left[0] = ImageLoader.loadImage("/character/Sai_l_1.png");
		actor5_left[1] = ImageLoader.loadImage("/character/Sai_l_2.png");
		actor5_left[2] = ImageLoader.loadImage("/character/Sai_l_1.png");
		actor5_left[3] = ImageLoader.loadImage("/character/Sai_l_3.png");
		actor5_right[0] = ImageLoader.loadImage("/character/Sai_r_1.png");
		actor5_right[1] = ImageLoader.loadImage("/character/Sai_r_2.png");
		actor5_right[2] = ImageLoader.loadImage("/character/Sai_r_1.png");
		actor5_right[3] = ImageLoader.loadImage("/character/Sai_r_3.png");
		
		actor6_down[0] = ImageLoader.loadImage("/character/Yuki_d_1.png");
		actor6_down[1] = ImageLoader.loadImage("/character/Yuki_d_2.png");
		actor6_down[2] = ImageLoader.loadImage("/character/Yuki_d_1.png");
		actor6_down[3] = ImageLoader.loadImage("/character/Yuki_d_3.png");
		actor6_up[0] = ImageLoader.loadImage("/character/Yuki_u_1.png");
		actor6_up[1] = ImageLoader.loadImage("/character/Yuki_u_2.png");
		actor6_up[2] = ImageLoader.loadImage("/character/Yuki_u_1.png");
		actor6_up[3] = ImageLoader.loadImage("/character/Yuki_u_3.png");
		actor6_left[0] = ImageLoader.loadImage("/character/Yuki_l_1.png");
		actor6_left[1] = ImageLoader.loadImage("/character/Yuki_l_2.png");
		actor6_left[2] = ImageLoader.loadImage("/character/Yuki_l_1.png");
		actor6_left[3] = ImageLoader.loadImage("/character/Yuki_l_3.png");
		actor6_right[0] = ImageLoader.loadImage("/character/Yuki_r_1.png");
		actor6_right[1] = ImageLoader.loadImage("/character/Yuki_r_2.png");
		actor6_right[2] = ImageLoader.loadImage("/character/Yuki_r_1.png");
		actor6_right[3] = ImageLoader.loadImage("/character/Yuki_r_3.png");
		
		FireDragon_left[0] = ImageLoader.loadImage("/monster/FireDragonL1.png");
		FireDragon_left[1] = ImageLoader.loadImage("/monster/FireDragonL2.png");
		FireDragon_left[2] = ImageLoader.loadImage("/monster/FireDragonL3.png");
		FireDragon_left[3] = ImageLoader.loadImage("/monster/FireDragonL4.png");
		FireDragon_right[0] = ImageLoader.loadImage("/monster/FireDragonR1.png");
		FireDragon_right[1] = ImageLoader.loadImage("/monster/FireDragonR2.png");
		FireDragon_right[2] = ImageLoader.loadImage("/monster/FireDragonR3.png");
		FireDragon_right[3] = ImageLoader.loadImage("/monster/FireDragonR4.png");

		Piplup_left[0] = ImageLoader.loadImage("/monster/PiplupL1.png");
		Piplup_left[1] = ImageLoader.loadImage("/monster/PiplupL2.png");
		Piplup_left[2] = ImageLoader.loadImage("/monster/PiplupL3.png");
		Piplup_left[3] = ImageLoader.loadImage("/monster/PiplupL4.png");
		Piplup_right[0] = ImageLoader.loadImage("/monster/PiplupR1.png");
		Piplup_right[1] = ImageLoader.loadImage("/monster/PiplupR2.png");
		Piplup_right[2] = ImageLoader.loadImage("/monster/PiplupR3.png");
		Piplup_right[3] = ImageLoader.loadImage("/monster/PiplupR4.png");
		
		WalkingGrass_left[0] = ImageLoader.loadImage("/monster/WalkingGrassL1.png");
		WalkingGrass_left[1] = ImageLoader.loadImage("/monster/WalkingGrassL2.png");
		WalkingGrass_left[2] = ImageLoader.loadImage("/monster/WalkingGrassL3.png");
		WalkingGrass_left[3] = ImageLoader.loadImage("/monster/WalkingGrassL4.png");
		WalkingGrass_right[0] = ImageLoader.loadImage("/monster/WalkingGrassR1.png");
		WalkingGrass_right[1] = ImageLoader.loadImage("/monster/WalkingGrassR2.png");
		WalkingGrass_right[2] = ImageLoader.loadImage("/monster/WalkingGrassR3.png");
		WalkingGrass_right[3] = ImageLoader.loadImage("/monster/WalkingGrassR4.png");
		
		LightningBird_left[0] = ImageLoader.loadImage("/monster/LightningBirdL1.png");
		LightningBird_left[1] = ImageLoader.loadImage("/monster/LightningBirdL2.png");
		LightningBird_left[2] = ImageLoader.loadImage("/monster/LightningBirdL3.png");
		LightningBird_left[3] = ImageLoader.loadImage("/monster/LightningBirdL4.png");
		LightningBird_right[0] = ImageLoader.loadImage("/monster/LightningBirdR1.png");
		LightningBird_right[1] = ImageLoader.loadImage("/monster/LightningBirdR2.png");
		LightningBird_right[2] = ImageLoader.loadImage("/monster/LightningBirdR3.png");
		LightningBird_right[3] = ImageLoader.loadImage("/monster/LightningBirdR4.png");
		
		illustrations[0] = ImageLoader.loadImage("/character/¤p³·.png");
		illustrations[1] = ImageLoader.loadImage("/character/¥É¨à.png");
		illustrations[2] = ImageLoader.loadImage("/character/¸­¤ý.png");
		illustrations[3] = ImageLoader.loadImage("/character/¨È®R.png");
		illustrations[4] = ImageLoader.loadImage("/character/SAI.png");
		illustrations[5] = ImageLoader.loadImage("/character/HASAKI.png");

		rollingBall[0] = ImageLoader.loadImage("/item/snowballEntity.png");
		rollingBall[1] = ImageLoader.loadImage("/item/snowballEntity.png");
		choose_Asuna[0] = ImageLoader.loadImage("/character/Choose_Asuna_1.png");
		choose_Asuna[1] = ImageLoader.loadImage("/character/Choose_Asuna_2.png");
		choose_Hao[0]= ImageLoader.loadImage("/character/Choose_Hao_1.png");
		choose_Hao[1]= ImageLoader.loadImage("/character/Choose_Hao_2.png");
		choose_Hasaki[0]= ImageLoader.loadImage("/character/Choose_Hasaki_1.png");
		choose_Hasaki[1]= ImageLoader.loadImage("/character/Choose_Hasaki_2.png");
		choose_Jade[0]= ImageLoader.loadImage("/character/Choose_Jade_1.png");
		choose_Jade[1]= ImageLoader.loadImage("/character/Choose_Jade_2.png");
		choose_Yuki[0]= ImageLoader.loadImage("/character/Choose_Yuki_1.png");
		choose_Yuki[1]= ImageLoader.loadImage("/character/Choose_Yuki_2.png");
		choose_Sai[0]= ImageLoader.loadImage("/character/Choose_Sai_1.png");
		choose_Sai[1]= ImageLoader.loadImage("/character/Choose_Sai_2.png");
		for(int i = 0 ; i < 17 ; i++)
		{
			String read = "/item/ultra_ani_right (" + (i+1) + ").png";
			ultra_ani_right[i] = ImageLoader.loadImage(read);
		}
		
		for(int i = 0 ; i < 17 ; i++)
		{
			String read = "/item/ultra_ani_down (" + (i+1) + ").png";
			ultra_ani_down[i] = ImageLoader.loadImage(read);
		}
		
		for(int i = 0 ; i < 17 ; i++)
		{
			String read = "/item/ultra_ani_up (" + (i+1) + ").png";
			ultra_ani_up[i] = ImageLoader.loadImage(read);
		}
		
		for(int i = 0 ; i < 17 ; i++)
		{
			String read = "/item/ultra_ani_left (" + (i+1) + ").png";
			ultra_ani_left[i] = ImageLoader.loadImage(read);
		}
		
		for(int i = 0 ; i < 18 ; i++)
		{
			String read = "/item/wall (" + (i+1) + ").png";
			crystallizeVertical[i] = ImageLoader.loadImage(read);
		}
		
		for(int i = 0 ; i < 18 ; i++)
		{
			String read = "/item/wallhori (" + (i+1) + ").png";
			crystallizeHori[i] = ImageLoader.loadImage(read);
		}
		
		
		title_img[0] = ImageLoader.loadImage("/textures/title1.png");
		title_img[1] = ImageLoader.loadImage("/textures/title2.png");
		back_img= ImageLoader.loadImage("/textures/background.png");
		end_back_img= ImageLoader.loadImage("/textures/end_bg.png");
		bagLoc_img= ImageLoader.loadImage("/textures/bagLoc.png");
		mapall= ImageLoader.loadImage("/textures/mapall.png");
		
		grass = ImageLoader.loadImage("/textures/grass.png");
		water = ImageLoader.loadImage("/textures/water.png");
		fire = ImageLoader.loadImage("/textures/fire.png");
		stone = ImageLoader.loadImage("/textures/stone.png");
		lawn = ImageLoader.loadImage("/textures/lawn.png");
		tree = ImageLoader.loadImage("/obstacles/BigTree1.png");
		
		game_start = ImageLoader.loadImage("/textures/gamestart.png");
		flash = ImageLoader.loadImage("/item/flash.PNG");
		crystallize = ImageLoader.loadImage("/item/crystallize.PNG");
		ghost_walk = ImageLoader.loadImage("/item/ghost_walk.PNG");
		snowball = ImageLoader.loadImage("/item/snowball.PNG");
		ultralight = ImageLoader.loadImage("/item/Ultralight.PNG");
		snowballEntity = ImageLoader.loadImage("/item/snowballEntity.png");
		flashDisplay = ImageLoader.loadImage("/item/flash_ani.png");
	}
}
