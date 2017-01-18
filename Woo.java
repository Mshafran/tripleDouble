import java.util.ArrayList;
import cs1.Keyboard;

public class Woo {
    static int money = 1000;
    public static void main(String[] args){
	System.out.println("                                              YYYY            YY");
	System.out.println("HHHHH         HHHHH                             YYY          YYYY");
	System.out.println("HHHHH         HHHHH                  PP PPPPPP   YYY         YYY");
	System.out.println("  HHH         HHH                     PPP    PP   YYY       YYY");
	System.out.println("  HHH         HHH    AAAAA  PP PPPPPP  PP    PP    YYY     YYY");
	System.out.println("  HHH         HHH   AAAAAAA  PPP    PP PP    PP     YYY   YYY");
	System.out.println("  HHH         HHH  AA     AA  PP    PP PP    PP      YYY YYY");
	System.out.println("  HHH         HHH  AA     AA  PP    PP PPPPPPP        YYYYY");
	System.out.println("  HHHHHHHHHHHHHHH  AA     AA  PP    PP PP             YYYY");
	System.out.println("  HHHHHHHHHHHHHHH  AA     AA  PPPPPPP  PP            YYYY   -------");
	System.out.println("  HHHHHHHHHHHHHHH  AAAAAAAAA  PP       PP           YYYY    -------");
	System.out.println("  HHH         HHH  AAAAAAAAA  PP       PP          YYYY     -------");
	System.out.println("  HHH         HHH  AA     AA  PP       PP         YYYY");
	System.out.println("  HHH         HHH  AA     AA  PP       PP        YYYY");
	System.out.println("  HHH         HHH  AA     AA  PP       PP      YYYYYY");
	System.out.println("  HHH         HHH  AA     AA  PP       PP     YYYYYYY");
	System.out.println("  HHH         HHH             PP       PP    YYYYYYY");
	System.out.println("HHHHH         HHHHH           PP       PP   YYYYYYY");
	System.out.println("HHHHH         HHHHH           PP       PP  YYYYYYY");
	System.out.println("                                            YYYYY");
	System.out.println("BBBBBBBBBBBBB                                YYY");
	System.out.println("BBBBBBBBBBBBBB                                Y");
	System.out.println(" BBBB       BBB    II                                   YYY             YYY");
	System.out.println("  BB         BB    II               DDDDDDDDDDDDD       YYYY           YYYY");
	System.out.println("  BB         BB                     DDDDDDDDDDDDDD        YY            YY");
	System.out.println("  BB         BB   III  RRR RRRR        DDD      DDD      A YY          YY");
	System.out.println("  BB         BB    II   RRRR  RR       DDD      DDD     AAA YY        YY");
	System.out.println("  BB        BBB    II    RRR           DDD      DDD    AAAAA YY      YY");
	System.out.println("  BBB     BBBB     II    RR            DDD      DDD   AAAAAAA YY    YY");
	System.out.println("  BBBBBBBBBBB      II    RR            DDD      DDD  AA     AA YY  YY");
	System.out.println("  BBBBBBBBB        II    RR            DDD      DDD  AA     AA  YYYY");
	System.out.println("  BBBBBBBBBBB      II    RR            DDD      DDD  AAAAAAAAA   YYY");
	System.out.println("  BBB     BBBB    IIII  RRRR           DDD      DDD  AAAAAAAAA   YYY");
	System.out.println("  BB        BBB             HHH        DDD      DDD  AA     AA   YYY");
	System.out.println("  BB         BBB    TT     HHHH        DDD      DDD  AA     AA   YYY");
	System.out.println("  BB         BBB    TT     HH          DDD      DDD  AA     AA   YYY");
	System.out.println("  BB          BBB TTTTTT   HH          DDD      DDD  AA     AA   YYY");
	System.out.println("  BB          BBB   TT     HH          DDD      DDD              YYY");
	System.out.println("  BB          BBB   TT     HHHHHHHH  DDDDDDDDDDDDD               YYY");
	System.out.println("  BB         BBB    TT     HH     HH DDDDDDDDDDDD               YYYY");
	System.out.println(" BBBB       BBBB    TT     HH     HH                           YYYY");
	System.out.println("BBBBBBBBBBBBBBB     TT  TT HH     HH    YYYYYYYYYYYYYYYYYYYYYYYYYY");
	System.out.println("BBBBBBBBBBBBBB       TTTT  HH     HH    YYYYYYYYYYYYYYYYYYYYYYYY");
	System.out.println("                          HHHH   HHHH   YYYYYYYYYYYYYYYYYYYYYY");

	System.out.println("\nMark!!!\n\n");
	
	System.out.println("Hank Hank it be Casino time");
	System.out.println("What game do you want to play?");
	System.out.println("1. BlackJack \n2. war \n3. Crazy8s \n4. CardGame (General)");
	int response = Keyboard.readInt();
	if (response == 1){
	    BlackJack.main(args);
	} else if (response == 2){
	    //War.main(args);
	} else if (response == 3){
	    Crazy8s.main(args);
	} else {
	    CardGame.main(args);
	}
    }
}
