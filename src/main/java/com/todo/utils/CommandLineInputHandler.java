package com.todo.utils;

import java.util.List;
import java.util.Scanner;

import com.todo.model.ToDoItem;
import com.todo.repository.InMemoryToDoRepository;
import com.todo.repository.ToDoRepository;

public class CommandLineInputHandler {
	private ToDoRepository repo = new InMemoryToDoRepository();
	private Scanner scanner = new Scanner(System.in);
	public void printOptions(){
		System.out.println("Enter any of following:");
		System.out.println("a-> Add");
		System.out.println("d-> Delete");
		System.out.println("u-> Update");
		System.out.println("s-> show");
		System.out.println("e-> Exit");
	}
	public String readInput(){
		String output = scanner.nextLine();
		return output;
	}
	public void processInput(CommandLineInput cli){
		if(cli.equals(CommandLineInput.ADD)){
			String name = scanner.nextLine();
			ToDoItem item = new ToDoItem(name);
			repo.insert(item);
		}else if(cli.equals(CommandLineInput.DELETE)){
			Long id = scanner.nextLong();
			ToDoItem item = repo.findById(id);
			if(item != null) repo.delete(item);
		}else if(cli.equals(CommandLineInput.UPDATE)){
			int choice = 0;
			Long id = scanner.nextLong();
			scanner.next();
			ToDoItem item = repo.findById(id);
			if(item == null){
				System.out.println("No such item");
				return;
			}
			do{
				System.out.println("1-> Update name");
				System.out.println("2-> Update complete status");
				System.out.println("3-> Done.");
				choice = scanner.nextInt();
				scanner.next();
				if(choice == 1){
					String next = scanner.nextLine();
					item.setName(next);
				}else if(choice == 2){
					int done = 0;
					done = scanner.nextInt();
					scanner.next();
					item.setCompleted(done == 0 ? false : true);
				}
			}while(choice!=3);
			
		}else if(cli.equals(CommandLineInput.DEFAULT)){
			System.out.println("Wrong input rejected");
		}else if(cli.equals(CommandLineInput.EXIT)){
			System.out.println("BYE");
		}
		else if(cli.equals(CommandLineInput.SHOW)){
			List<ToDoItem> list = repo.findAll();
			System.out.println("\n\nTODO LIST");
			for( ToDoItem item : list ){
				System.out.println(item.getId()+". "+item.getName());
			}
			System.out.println("\n\n");
		}
	}
	@Override
	public void finalize(){
		scanner.close();
	}
}
