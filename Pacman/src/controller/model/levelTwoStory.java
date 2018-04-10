package controller.model;

public class levelTwoStory {
	
	public static final String[] ScriptChoice = new String[] {
			
			"0During your descent into the dungeon you see a suspicious chest",
			"1Hmm? Why's there a chest here?",
			"0Your instincts tell you that the chest is bad news but it could contain goods",

	};
	
	public static final String[] ScriptOpen = new String[] {
			
			"0CHOMP",
			"0It was a mimic!",
			"0You swipe at it but it quickly jumps away",
			"0You lose $100 along with one life"
	};
	
	public static final String[] ScriptLeave = new String[] {
			
			"1Can't risk it",
			"0You leave the chest alone"
	};

}

