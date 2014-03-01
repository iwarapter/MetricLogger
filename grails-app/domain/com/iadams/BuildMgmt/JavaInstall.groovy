package com.iadams.BuildMgmt

class JavaInstall {
	
	String ver
	String vendor
	String home
	int arch
	
	String runtimeName
	String runtimeVer
	
	String jvmName
	String jvmVer
	
	static hasMany = [ builds : Build ]

    static constraints = {
    }
}
