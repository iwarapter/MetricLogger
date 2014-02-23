package com.iadams.BuildMgmt

class Java {
	
	String ver
	String vendor
	String home
	
	static belongsTo = [ jvm : JavaVirtualMachine, runtime : JavaRuntime ]
	
	static hasMany = [ builds : Build ]

    static constraints = {
    }
}
