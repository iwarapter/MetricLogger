package com.iadams.BuildMgmt

class JavaRuntime {

	String name
	String ver
	
	static hasMany = [ java : Java ]
	
    static constraints = {
    }
}
