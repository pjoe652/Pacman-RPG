package controller.model;

public class Story {
	// Level 1 Story
	public static final String[] ScriptData = new String[] { "0Psst psst psst",
			"0The noise of hushed whispers wakes you from your slumber", "1Mmm...?", "0Knock Knock Knock",
			"1Who's there?", "0Bam!", "2Break down the door!", "1!!!",
			"0Three barbaric men barge into your house knocking down the door", "1Who are you guys?",
			"2So you're the son of that damn coward", "1Who are you calling a coward?",
			"2Your father of course, he disappeared with a mountain of debt", "1?!",
			"2Now, guess who going to have to shoulder that debt?",
			"2You of course, you have 5 days to return... Let's say $2000",
			"2And you know what will happen if you run away don't you?",
			"0The man slowly raises his knife while smirking at you", "2Well, here's a tip for 'ya",
			"2The nearby dungeon is rumored to hold a fortune, more than you can imagine",
			"2So idiots like your father will go treature hunting in there and disappear mysteriously",
			"2Even if there isn't any treasure, you can loot from dead explorers", "2GAHAHAHAHA!!!",
			"0The men laughed as they walked out leaving the room ransacked", "1My father is... gone?",
			"1Those men tricked him...?", "0Although you feel sad about the loss of your father",
			"0You feel even more infuriated at the men for their trickery",
			"1I'll show them, I'll return the debt, I will not be shaken!"

	};

	// Level 2 Story
	public static final String[] ScriptQuestion = new String[] {

			"0During your descent into the dungeon you see a suspicious chest", "1Hmm? Why's there a chest here?",
			"0Your instincts tell you that the chest is bad news but it could contain goods",

	};

	public static final String[] ScriptChoice1 = new String[] {

			"0CHOMP", "0It was a mimic!", "0You swipe at it but it quickly jumps away",
			"0You lose $2000 along with one total HP", "0   " };

	public static final String[] ScriptChoice2 = new String[] {

			"1Can't risk it", "0You leave the chest alone" };

	public static final String[] ScriptBoss = new String[] { "1Hm? A spotlight?",
			"0When you look up you see no sign of light", "0Suddenly you hear shuffling across the room",
			"1Who's there?!", "0You feel the air start to shake, obscuring your vision",
			"0You cautiously look around and find that it's prescences is coming there this direction",
			"0Peering into the distance, you start to make out it's shape", "1!!!", "0     ", "1A massive slime!",
			"0After it's discovery, the slime releases a roar", "2     ", "1Wha-",
			"0The big slime started splitting up into many smaller slimes!" };
	
	public static final String[] FinalScript = new String[] {
			"0After defeating the Slime King you return to the surface", "0The village welcomed you home as a hero",
			"0They praised his heroism despite not knowing his purpose", "0The thugs faces' turned white upon seeing your return",
			"0With the money left over you decided to live the good life"};
}
