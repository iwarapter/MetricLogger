package com.iadams.BuildMgmt

class User {
	
	String name

    static constraints = {
		name unique: true
		
    }
}
