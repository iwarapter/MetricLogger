package com.iadams.BuildMgmt

class Project {

	String name
	String description
	
	static hasMany = [ builds : Build,  tasks : Task, plugins : Plugin ]
	
	static belongsTo = [ Plugin, Task ]
	
    static constraints = {
    }
}
