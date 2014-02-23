package com.iadams.BuildMgmt

class User {
	
	String name
	
	static hasMany = [ builds : Build ]
	
    static constraints = {
		name unique: true
		
    }
}
