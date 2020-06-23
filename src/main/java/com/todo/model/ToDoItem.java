package com.todo.model;

public class ToDoItem implements Comparable<ToDoItem>{
	private Long id;
	private String name;
	private boolean completed;

	public ToDoItem(String task){
		this.name = task;
		completed = false;
	}

	@Override
	public int compareTo(ToDoItem o) {
		return id.compareTo(o.id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
}
