package com.iadams.BuildMgmt

class Logfile {

	String myFile
	
    static constraints = {
		myFile maxSize: 1024 * 1024 * 5
    }
}
