package com.todo.utils;

public enum CommandLineInput {
	ADD('a'),
	UPDATE('u'),
	DELETE('d'),
	EXIT('e'),
	SHOW('s'),
	DEFAULT('\u0000');
	private final char c;
	private CommandLineInput(char c){
		this.c = c;
	}
	public char getShortCmd(){
		return c;
	}
	public static CommandLineInput getCommandLineInputForInput(char c){
		if(c == 'a') return ADD;
		else if(c == 'u') return UPDATE;
		else if(c == 'd') return DELETE;
		else if(c == 'e') return EXIT;
		else if(c == 's') return SHOW;
		else return DEFAULT;
	}
}
