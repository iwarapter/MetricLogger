package com.iadams.BuildMgmt

class JavaVirtualMachine {

	String name
	String ver
	String vendor
	
	static hasMany = [ java : Java ]
	
    static constraints = {
    }
}
