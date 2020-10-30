package net.turtle;

public final class ExampleStructures {
	
	private static final String[][] PATTERN = new String[][] {
		new String[] {
				"XXXXXXXX",
				"XXXXXXXX",
				"XXXXXXXX",
				"XXXXXXXX",
				"XXXXXXXX"
		},
		new String[] {
				"XXXXXXXX",
				"X      X",
				"X       ",
				"X      X",
				"XXXXXXXX"
		},
		new String[] {
				"XXXXXXXX",
				"X      X",
				"X       ",
				"X      X",
				"XXXXXXXX"
		},
		new String[] {
				"XXXXXXXX",
				"X      X",
				"X      X",
				"X      X",
				"XXXXXXXX"
		},
		new String[] {
				"XXXXXXXX",
				"XXXXXXXX",
				"XXXXXXXX",
				"XXXXXXXX",
				"XXXXXXXX"
		},
		new String[] {
				"XXXXXXXX",
				"X      X",
				"X       ",
				"X      X",
				"XXXXXXXX"
		},
		new String[] {
				"XXXXXXXX",
				"X      X",
				"X       ",
				"X      X",
				"XXXXXXXX"
		},
		new String[] {
				"XXXXXXXX",
				"X      X",
				"X      X",
				"X      X",
				"XXXXXXXX"
		},
		new String[] {
				"XXXXXXXX",
				"XXXXXXXX",
				"XXXXXXXX",
				"XXXXXXXX",
				"XXXXXXXX"
		},
		new String[] {
				"        ",
				"XXXXXXXX",
				"XXXXXXXX",
				"XXXXXXXX",
				"        "
		},
		new String[] {
				"        ",
				"        ",
				"XXXXXXXX",
				"        ",
				"        "
		},
	};
	
	public static final Structure EXAMPLE = Structure.createFromLayers(PATTERN).getZeroStructure();
}
