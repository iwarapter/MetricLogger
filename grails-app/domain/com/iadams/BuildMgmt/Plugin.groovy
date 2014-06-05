package com.iadams.BuildMgmt

class Plugin {
	
	String name
	
	static hasMany = [ projects : Project ]

    static constraints = {
		name unique: true
    }
}
