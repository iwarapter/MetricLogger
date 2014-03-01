package com.iadams.BuildMgmt

class JavaInstall {
	
	String ver
	String vendor
	String home
	
	String runtimeName
	String runtimeVer
	
	String jvmName
	String jvmVer
	
	static hasMany = [ builds : Build ]

    static constraints = {
    }
}
