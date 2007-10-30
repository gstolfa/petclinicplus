package net.larsbehnke.petclinicplus.util.namedvocabulary;

import java.util.List;

public interface NamedVocabularyManager {

	List<NVEntry> getList(String listName);

	String getListValue(String listName, String entryName);

	NVEntry getListEntry(String listName, String entryName);
	
	String getEntryNameByValue (String listName, String value);
}