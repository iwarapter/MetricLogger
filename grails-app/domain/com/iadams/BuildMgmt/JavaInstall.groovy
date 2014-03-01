package com.iadams.BuildMgmt

class JavaInstall {
	
	String ver
	String vendor
	String home
	
	static belongsTo = [ jvm : JavaVirtualMachine, runtime : JavaRuntime ]
	
	static hasMany = [ builds : Build ]

    static constraints = {
    }
}
