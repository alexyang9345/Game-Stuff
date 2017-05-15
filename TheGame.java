import java.util.*;
import java.lang.reflect.*;

public class TheGame
{
    public static void main(String[] args) throws SecurityException, NoSuchMethodException, IllegalArgumentException,
            IllegalAccessException, InvocationTargetException
    {
        Room[][][] map = new Room[7][13][2];
        
        //Rooms
        
        //Chimney
        Item bread = new Item("BREAD", "A hunk of freshly baked bread. Tasty!", 
        "A hunk of bread lies on the floor", 0, 10, "You eat the bread... not bad", 5);
        
        Item paper = new Item("QUESTCO AD", "A QuestCo advertisement poster", 
        "A QuestCo Ad lies face up on the floor", 0, 0, 
        "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\nSeeking adventure? Fame? Riches? \n\nJoin QuestCo!\n\nAs a member of QuestCo,\n you'll have the rare oppurtunity\nto explore the world and \nget rich doing so!\nDive deep into the \ndungeons of old to collect valuable\ntreasures! Find a QuestCo \nrepresentative at your \nlocal inn to learn more!\n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^",
        1);
        
        Room chimney = new Room(4,12,1, "Chimney", "You find yourself in a circular room with a large cooking fire in the center. A stack of firewood sits to the west of the fire. Strangely enough, there are lit torches on the wall. There is an open wooden door to the north.",
        true,false,false,false,false,false,new ArrayList<Item>(Arrays.asList(bread,paper)), null, null);
        
        map[4][12][1] = chimney;
        
        //Kitchen
        Room kitchen = new Room(4,11,1, "Kitchen", "You enter a low ceilinged room. The walls are lined with cabinets filled with silverware and dishes. Counters for food preperation line the center of the room. There is a cupboard to the east, and a hallway to the west",
        false,true,true,true,false,false, new ArrayList<Item>(), null, null);
        
        map[4][11][1] = kitchen;
        
        //Cupboard
        Item ale = new Item("ALE", "A bottle of good-quality nordic ale", "A bottle of ale sits on a back shelf", 0,
        15, "You down the ale, feeling a bit warmer and a bit braver", 10);
        
        Room cupboard = new Room(5,11,1,"Cupboard","You enter a dark room crowded with shelves stocked with all sorts of cooking supplies. The door to the kitchen is to the west",
        false,false,false,true,false,false, new ArrayList<Item>(Arrays.asList(ale)), null,null);
        
        map[5][11][1] = cupboard;
        
        //Hallway
        Room hallway = new Room(3,11,1,"Hallway","A long, narrow, torch lined hallway. It opens up to a dining room to the west and the kitchen is to the east",
        false,false,true,true,false,false, new ArrayList<Item>(),null,null);
        
        map[3][11][1] = hallway;
        
        //Dining Room
        Item bread2 = new Item("BREAD", "An old loaf of bread... Seems edible", "A loaf of bread lies untouched on the table",
        0,10, "You eat the bread... a bit stale but could be worse", 1);
        
        Item knife = new Item("KNIFE", "A kitchen knife probably better suited to cutting vegetables than dungeon monsters, but it will do for now",
        "A large knife rests at the head of the table", 5,0, "You swiftly slash with the knife", 15);
        
        Room diningRoom = new Room(2,11,1,"Dining Room", "A torch lit dining room dominated by a long table with chairs neatly spaced around it. The room opens to a hallway to the east. To the north is a firmly locked wooden door, and to the south another wooden door",
        false,true,true,false,false,false, new ArrayList<Item>(Arrays.asList(bread2,knife)),null, "APOTHECARY'S KEY");
        
        map[2][11][1] = diningRoom;
        
        //Servants Quarters
        Item apKey = new Item("APOTHECARY'S KEY", "A rusty key used to gain entrance to the Apothecary's Study",
        "The Apothecary's key rests in a bowl on a nearby dresser", 0, 0, "You carefully twist the key into the lock, and the door creaks open", 10);
        
        Enemy skeleton1 = new Enemy("SKELETON", 15, 5, "A living skeleton stands before you holding a dagger... It does not seem friendly");
        
        Room servantsQuarters = new Room(2,12,1, "Servant's Quarters", "A long room lined with rows of shabby looking beds on the east and west walls. The door to the dining room is to the north.",
        true,false,false,false,false,false, new ArrayList<Item>(Arrays.asList(apKey)), skeleton1,null);
        
        map[2][12][1] = servantsQuarters;
        
        //Apothecary's Study
        Item journal = new Item("JOURNAL", "The journal of an Apothecary... His discoveries are written here",
        "An old journal sits on a candlelit desk", 0,0, 
        "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\nI've finally done it! By adding 3g \nof crushed Beech Bark to the recipe I've\ncreated a potion that can restore\nall but the most greivous\nof injuries. I must inform the King and\ncontinue brewing at once...\n^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^", 150);
        
        Room apothecarysStudy = new Room(2,10,1,"Apothecary's Study", "A cramped, dimly lit room. A bed, desk, and several bookshelves are tightly packed together along the walls. A laboratory can be seen through an open door to the west, and there are doors to the west and south.",
        false,true,true,true,false,false, new ArrayList<Item>(Arrays.asList(journal)), null, null);
        
        map[2][10][1] = apothecarysStudy;
        
        //Apothecary's Lab
        Item potion1 = new Item("POTION", "A glass bottle filled with some sort of glistening yellow liquid",
        "A potion sits on a brewing stand", 0, 40, "You drink the potion and are immediately overcome with a feeling of rejuvenation and energy", 75);
        
        Room apothecarysLab = new Room(1,10,1,"Apothecary's Lab", "A small laboratory dedicated to the crafting of potions. Cabinets filled with ingredients line the walls and all sorts of strange beakers and flasks lie on countertops. A storage room is to the west, and the study is to the east.",
        false,false,true,true,false,false, new ArrayList<Item>(Arrays.asList(potion1)), null, null);
        
        map[1][10][1] = apothecarysLab;
        
        //Potion Room
        Item potion2 = new Item("POTION", "A glass bottle filled with some sort of glistening yellow liquid",
        "A potion sits on a shelf", 0, 40, "You drink the potion and are immediately overcome with a feeling of rejuvenation and energy", 75);
        
        Item potion3 = new Item("POTION", "A glass bottle filled with some sort of glistening yellow liquid",
        "An identical potion sits next to it", 0, 40, "You drink the potion and are immediately overcome with a feeling of rejuvenation and energy", 75);
        
        Enemy skeleton2 = new Enemy("SKELETON", 15, 5, "A skeleton in tattered robes holding the end of a shattered beaker approaches...");
        
        Room potionRoom = new Room(0,10,1,"Potion Room", "A dark storage closet filled with bottles, flasks, and other brewing supplies. The door to the lab is to the east",
        false,false,true,false,false,false, new ArrayList<Item>(Arrays.asList(potion2, potion3)), skeleton2, null);
        
        map[0][10][1] = potionRoom;
        
        //Corridor
        
        Room corridor = new Room(3,10,1,"Corridor", "A dark narrow corridor which runs to the east and west",
        false,false,true,true,false,false, new ArrayList<Item>(), null, null);
        
        map[3][10][1] = corridor;
        
        //Southern Gate House
        
        Room sgh = new Room(4,10,1,"Southern Gate House", "A brightly lit guard post watching over the south end of the Cave Walkway. The gate, which lies to the north, is open. The corridor is to the west.",
        true,false,false,true,false,false,new ArrayList<Item>(), null, null);
        
        map[4][10][1] = sgh;
        
        //Cave Walkway
        
        Room cw = new Room(4,9,1,"Cave Walkway", "A torch lit walkway carved out of the cave wall which runs north to south. To the east is a massive cave. It's too dark to see the bottom...",
        true,true,false,false,false,false,new ArrayList<Item>(), null, null);
        
        map[4][9][1] = cw;
        
        //Northern Gate House
        
        Item shortSword = new Item("SHORTSWORD", "A sturdy steel sword", "A shortsword lies on the ground next to a collapsed skeleton wearing mangled plate armor, presumably a former guard...",
        10,0,"You unleash a fierce strike with the sword", 50);
        
        Room ngh = new Room(4,8,1,"Northern Gate House","A brightly lit guard post watching over the north end of the Cave Walkway. The gate, which lies to the south, is open. A passageway leads off to the west and an open door lies to the north",
        true,true,false,true,false,false,new ArrayList<Item>(Arrays.asList(shortSword)),null, null);
        
        map[4][8][1] = ngh;
        
        //Guard's Quarters
        
        Item ale1 =new Item("ALE", "A bottle of good-quality nordic ale", "A bottle of ale sits on top of a dresser", 0,
        15, "You down the ale, feeling a bit warmer and a bit braver", 10);
        
        Item purse = new Item("COIN PURSE", "A small cloth sack, heavy with gold coins", "A coin purse rests on the dresser",
        0,0,null, 200);
        
        Room gq = new Room(3,8,1,"Guard's Quarters", "A wide room with beds and dressers, most likely used as the guards living space. A passageway leads back to the east",
        false,false,true,false,false,false,new ArrayList<Item>(Arrays.asList(ale1,purse)), null,null);
        
        map[3][8][1] = gq;
        
        //Tapestry Hall
        
        Enemy spectralGhoul = new Enemy("SPECTRAL GHOUL", 25, 10, "A glowing blue spectral ghoul with deadly claws screams at you");
        
        Room th = new Room(4,7,1,"Tapestry Hall", "A tall long room, it's northern wall covered in dilapidated old tapestries. The room continues on to the east and west, and the Northern Gate House is to the south",
        false,true,true,true,false,false,new ArrayList<Item>(), spectralGhoul,null);
        
        map[4][7][1] = th;
        
        //Library
        
        Item book = new Item("BOOK", "A heavy old book with what appears to be a family tree printed on the cover. Could be valuable...",
        "An old book sits alone on a desk", 0,0,"The book is written in an ancient tongue, illegible to you", 500);
        
        Room l = new Room(3,7,1,"Library", "An impressive library with walls of bookshelves, most of which appear to be in ruin. The Tapestry Hall can be seen to the east.",
        false,false,true,false,false,false,new ArrayList<Item>(Arrays.asList(book)),null,null);
        
        map[3][7][1] = l;
        
        //Hall of Armor
        
        Room ha = new Room(5,7,1,"Hall of Armor","A long hall, its walls lined with ornate suits of armor flanked by lit torches. The Tapestry Hall can be seen to the west, there is a door to the east, and to the north, the Hall opens into the Great Bridge",
        true,false,true,true,false,false,new ArrayList<Item>(),null,null);
        
        map[5][7][1] = ha;
        
        //Armorer's Workshop
        
        Item rope = new Item("ROPE", "A long length of strong rope", "A long coil of rope hangs over an anvil",
        0,0,"You tie the rope around the boulder, making it possible to rapel down", 5);
        
        Room aw = new Room(6,7,1,"Armorer's Workshop","A cluttered room filled with pieces of metal, armor, and various tools. There is a door to the west",
        false,false,false,true,false,false, new ArrayList<Item>(Arrays.asList(rope)),null,null);
        
        map[6][7][1] = aw;
        
        //The Great Bridge
        
        Room gb = new Room(5,6,1,"The Great Bridge", "A masive bridge spanning across the cave. Due to a cave in, a middle portion of the bridge has collapse, making it impassable. You can see a crypt below you. A large boulder sits near the edge of the bridge. The Hall of Armor is to the south.",
        false,true,false,false,false,false,new ArrayList<Item>(), null,"ROPE");
        
        map[5][6][1] = gb;
        
        //Crypt Entrance
        
        Room ce = new Room(5,6,0,"Crypt Entrance", "You find yourself at the entrance to a crypt, its door to the west is flanked by gargoyles. A rope leads up to the great bridge",
        false,false,false,true,true,false,new ArrayList<Item>(),null,null);
        
        map[5][6][0] = ce;
        
        //Undertaker's Office
        
        Item ale3 = new Item("ALE", "A bottle of good-quality nordic ale", "A bottle of ale sits on the desk", 0,
        15, "You down the ale, feeling a bit warmer and a bit braver", 10);
        
        Room uo = new Room(4,6,0,"Undertaker's Office", "The workspace of the crypts undertaker. Various burial tools lay scattered on tables and cabinets. A skeleton sits slumped in a chair behind a heavy stone desk. The door to the Crypt Entrance is to the east, and the crypt continues further to the west.",
        false,false,true,true,false,false, new ArrayList<Item>(Arrays.asList(ale3)),null,null);
        
        map[4][6][0] = uo;
        
        //Crypt Hall
        
        Enemy cghoul = new Enemy("CRYPT GHOUL",30,15,"A sarcaphogus opens, and a half-decayed ghoul weilding a sword rushes you");
        
        Item stick = new Item("STICK", "A wooden stick which was once part of a shovel", "A stick that was once part of a shovel lies on the ground", 0,0,
        "You shove the stick into the lever and pull. The gate opens!", 0);
        
        Room ch = new Room(3,6,0,"Crypt Hall", "A dark room with rows of sarchophogui. The Undertaker's Office is to the east and the crypt continues to the north",
        true,false,true,false,false,false,new ArrayList<Item>(Arrays.asList(stick)),cghoul,null);
        
        map[3][6][0] = ch;
        
        //Hall of Warriors
        
        Room hw = new Room(3,5,0,"Hall of Warriors", "A long room, its east and west walls lined with large sarcophogui with impressive suits of armor above each. A closed gate lies to the north. The mechanism to open it needs a handle. The crypt continues to the south",
        false,true,false,false,false,false,new ArrayList<Item>(),null,"STICK");
        
        map[3][5][0] = hw;
        
        //Warrior Shrine
        
        Item dreds = new Item("DREADSEIVER", "The legendary greatsword Dreadseiver. A strange energy radiates from it...", "Above the sarcophogus hangs the greatsword Dreadseiver",
        30,0,"You swing the mighty sword, delivering a crushing blow",2000);
        
        Room ws = new Room(3,4,0,"Warrior Shrine", "A long hall, with a single sarcophogus on the northern wall. The Hall of Warriors is to the south and the exit to the crypt is to the east",
        false,true,true,false,false,false,new ArrayList<Item>(Arrays.asList(dreds)),null,null);
        
        map[3][4][0] = ws;
        
        //Crypt Exit
        
        Item potion4 = new Item("POTION", "A glass bottle filled with some sort of glistening yellow liquid",
        "A potion sits near the door", 0, 40, "You drink the potion and are immediately overcome with a feeling of rejuvenation and energy", 75);
        
        Room cE = new Room(4,4,0,"Crypt Exit", "The exit to the crypt. The door to the west is flanked by gargoyles. To the west lie the Bridge Stairs.",
        false,false,true,true,false,false, new ArrayList<Item>(Arrays.asList(potion4)),null,null);
        
        map[4][4][0] = cE;
        
        //Bridge Stairs
        
        Room bs = new Room(5,4,0,"Bridge Stairs", "A tall, spiral staircase leading up to the northern end of The Great Bridge",
        false,false,false,true,true,false, new ArrayList<Item>(), null, null);
        
        map[5][4][0] = bs;
        
        //Master's Gate
        
        Room mg = new Room(5,4,1,"Master's Gate", "A massive iron gate flanked by two large stone statues of ancient warriors. To the north is the gate, which is just open enough to allow you to slip through.",
        true,false,false,false,false,true, new ArrayList<Item>(),null,null);
        
        map[5][4][1] = mg;
        
        //King's hall
        
        Enemy wraith = new Enemy("WRAITH", 30, 20, "A wraith summoned by a powerful necromancer spots you and attacks with a wail");
        
        Room kh = new Room(5,3,1,"King's Hall", "A grand dining room with a massive table fit for a king at its center. The Treasure Room is to the west, and the King's Cupboard is to the east. The Master's Gate looms to the south, and the throne room is to the north.",
        true,true,true,true,false,false, new ArrayList<Item>(),wraith, null);
        
        map[5][3][1] = kh;
        
        //Treasure Room
        
        Item goldBar = new Item("GOLD BAR", "A brick of solid gold inlaid with a royal seal... Holy shit.", "A gold bar rests in an ornate wood case",
        0,0,null,3000);
        
        Item neck = new Item("DIAMOND NECKLACE", "A beautiful necklace encrusted with diamonds. Impressive!","A diamond necklace carefully sits in a glass case",
        0,0,null,4000);
        
        Item ring = new Item("RING", "A golden ring with a single large emerald in the center", "A ring lies in a glass case",
        0,0,null, 1000);
        
        Room tr = new Room(4,3,1,"Treasure Room", "A small, beautiful room with beautiful tapestrys on the walls. The room is warmly lit by four torches. The door to the King's Hall is to the east",
        false,false,true,false,false,false, new ArrayList<Item>(Arrays.asList(goldBar,neck,ring)), null,null);
        
        map[4][3][1] = tr;
        
        //King's Cupboard
        Item jam = new Item("ELDERBERRY JAM", "A delicious jam made from delicate berries", "On a shelf sits a jar of elderberry jam",
        50,0,"You eat the jam, savoring every bite", 200);
        
        Item mead = new Item("MEAD", "A flask of vintage mead... This here is the good stuff", "On another shelf sits a flask of fine mead",
        40,0,"You drink the mead, feeling stronger with every sip", 250);
        
        Room kc = new Room(6,3,1,"King's Cupboard","A fine cupboard, its shelves stocked with a variety of once fine delicacies",
        false,false,false,true,false,false, new ArrayList<Item>(Arrays.asList(jam,mead)),null,null);
        
        map[6][3][1] = kc;
        
        //Throne Room
        
        Enemy boss = new Enemy("NECROMANCER", 35, 40, "The necromancer; the cause of all of the dungeons horrible creatures stands before you. Prepare for battle");
        
        Room tR = new Room(5,2,1,"Throne Room", "A massive room with a tall vaulted ceiling. To the north is the King's Throne, and beyond that, a passageway. To the south is the King's Hall.",
        true,true,false,false,false,false, new ArrayList<Item>(),boss,null);
        
        map[5][2][1] = tR;
        
        //King's Ascent
        
        Item crown = new Item("CROWN", "The King's elegant gold crown...", "The King's crown sits on the pedestal",
        0,0,null,5000);
        
        Room ka = new Room(5,1,1,"King's Ascent", "A circular room entirely shrouded in darkness except for a light shining on a pedestal in the center of the room. To the south is the Throne Room, and you can barely make out a passage to the north.",
        true,true,false,false,false,false, new ArrayList<Item>(Arrays.asList(crown)),null,null);
        
        map[5][1][1] = ka;
        
        //Overlook
        
        Room o = new Room(5,0,1,"Overlook", "You exit the dungeon and find yourself on the side of a mountain. As you exit the passage, a heavy stone door slams shut behind you. It is done.",
        false,false,false,false,false,false,new ArrayList<Item>(),null,null);
        
        map[5][0][1] = o;
        
        //Game
        Player player = new Player(map);
        Interpreter test = new Interpreter(player);
        
        GameGui game = new GameGui(player, test);
}
}
