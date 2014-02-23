package com.iadams.BuildMgmt

class Project {

	String name
	List tasks
	String description
	
	static hasMany = [ builds : Build ]
	
    static constraints = {
    }
}
