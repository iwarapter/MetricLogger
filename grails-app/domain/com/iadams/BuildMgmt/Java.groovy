package com.iadams.BuildMgmt

class Java {
	
	String ver
	String vendor
	String home
	String jvm
	String runtime
	
	static belongsTo = [ jvm : JavaVirtualMachine, runtime : JavaRuntime ]

    static constraints = {
    }
}