package net.larsbehnke.petclinicplus.util.namedvocabulary;

public class NVEntry {

	private String listName;
	private String key;
	private String value;

	public NVEntry() {
		this(null, null, null);
	}

	public NVEntry(String listName, String key, String value) {
		setListName(listName);
		setKey(key);
		setValue(value);
	}

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}


}
