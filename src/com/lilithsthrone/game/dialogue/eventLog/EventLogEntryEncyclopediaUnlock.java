package com.lilithsthrone.game.dialogue.eventLog;

import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.colours.BaseColour;
import com.lilithsthrone.utils.colours.Colour;
import com.lilithsthrone.utils.colours.PresetColour;

/**
 * @since 0.1.85
 * @version 0.1.85
 * @author Innoxia
 */
public class EventLogEntryEncyclopediaUnlock extends EventLogEntry {

	
	public EventLogEntryEncyclopediaUnlock(String description, Colour highlightDescriptionColour) {
		super("百科全书",  "<span style='color:"+highlightDescriptionColour.toWebHexString()+";'>"+Util.capitaliseSentence(description)+"</span>");
	}
	
	public EventLogEntryEncyclopediaUnlock(String description, BaseColour highlightDescriptionColour) {
		super("百科全书",  "<span style='color:"+highlightDescriptionColour.toWebHexString()+";'>"+Util.capitaliseSentence(description)+"</span>");
	}
	
	@Override
	public String getFormattedEntry() {
		return "<span style='color:"+PresetColour.GENERIC_EXCELLENT.toWebHexString()+";'>新百科条目</span>: "+description;
	}
	
	@Override
	public String getMainDialogueDescription() {
		return "<p style='text-align:center;'>"
				+ "<b style='color:"+PresetColour.GENERIC_EXCELLENT.toWebHexString()+";'>百科全书收录了新条目</b>"
				+ "<br/>"
				+ description
			+ "</p>";
	}
	
}
