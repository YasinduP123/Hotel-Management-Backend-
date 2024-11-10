package lk.icet.hotel.util;

public enum RoomType {
	AC("A/C"),
	NON_AC("Non A/C");

	private final String displayName;

	RoomType(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
