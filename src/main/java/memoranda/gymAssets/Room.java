package main.java.memoranda.gymAssets;

import java.util.ArrayList;
import java.util.List;

public class Room {
	int id;
	List<GymClass> classList;
	
	public Room(int id) {
		this.id = id;
		classList = new ArrayList<GymClass>();
	}
	public int getClassCount() {
		return classList.size();
	}

	public void addClass(GymClass newClass) {
		if(classList.contains(newClass)) { //Added a check to avoid duplicate classes to exist.
			return;
		}
		for(GymClass gymClass: classList) { //Added a check to avoid double booking a room
			if(newClass.getDate().equals(gymClass.getDate())) {
				return;
			}
		}
		classList.add(newClass);
	}
	public GymClass getClass(int id) {
		return classList.get(id);
	}
	public List<GymClass> getAllClasses(){
		return classList;
	}
	
	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}
}
