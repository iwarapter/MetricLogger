package com.iadams.BuildMgmt

class Project {

	String name
	String description
	
	static hasMany = [ builds : Build ]
	
    static constraints = {
    }
}
