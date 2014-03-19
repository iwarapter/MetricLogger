package com.iadams.BuildMgmt

class User {
	
	String name
	
	String toString() { return name }
	
	static hasMany = [ builds : Build ]
	
    static constraints = {
		name unique: true
		
    }
}
