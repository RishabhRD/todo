package com.todo.utils;

import java.util.Scanner;

import com.todo.model.ToDoItem;
import com.todo.repository.InMemoryToDoRepository;
import com.todo.repository.ToDoRepository;

public class CommandLineInputHandler {
	private ToDoRepository repo = new InMemoryToDoRepository();
	public void printOptions(){
		System.out.println("Enter any of following:");
		System.out.println("a-> Add");
		System.out.println("d-> Delete");
		System.out.println("u-> Update");
		System.out.println("e-> Exit");
	}
	public String readInput(){
		Scanner scanner = new Scanner(System.in);
		String output = scanner.next();
		scanner.close();
		return output;
	}
	public void processInput(CommandLineInput cli){
		Scanner scanner = new Scanner(System.in);
		if(cli.equals(CommandLineInput.ADD)){
			String name = scanner.next();
			ToDoItem item = new ToDoItem(name);
			repo.insert(item);
		}else if(cli.equals(CommandLineInput.DELETE)){
			while(!scanner.hasNextLong()){
				continue;
			}
			Long id = scanner.nextLong();
			ToDoItem item = repo.findById(id);
			if(item != null) repo.delete(item);
		}else if(cli.equals(CommandLineInput.UPDATE)){
			int choice = 0;
			while(!scanner.hasNextLong()){
				continue;
			}
			Long id = scanner.nextLong();
			ToDoItem item = repo.findById(id);
			if(item == null){
				System.out.println("No such item");
				scanner.close();
				return;
			}
			do{
				System.out.println("1-> Update name");
				System.out.println("2-> Update complete status");
				System.out.println("3-> Done.");
				while(!scanner.hasNextInt()) continue;
				choice = scanner.nextInt();
				if(choice == 1){
					String next = scanner.next();
					item.setName(next);
				}else if(choice == 2){
					int done = 0;
					while(!scanner.hasNextInt()) continue;
					done = scanner.nextInt();
					item.setCompleted(done == 0 ? false : true);
				}
			}while(choice!=3);
			
		}else if(cli.equals(CommandLineInput.DEFAULT)){
			System.out.println("Wrong input rejected");
		}else if(cli.equals(CommandLineInput.EXIT)){
			System.out.println("BYE");
		}
		scanner.close();
	}
}
