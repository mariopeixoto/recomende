package br.recomende.infra.user;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum Roles {
	
	USER("ROLE_USER"),
	ADMIN("ROLE_ADMIN");
	
	private static final Map<String, Roles> internalMap = new HashMap<String, Roles>();

	static {
		for (Roles e : EnumSet.allOf(Roles.class)) {
			internalMap.put(e.getType(), e);
		}
	}

	private final String type;
	
	Roles(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public static Roles get(String type) {
		return internalMap.get(type);
	}

}
