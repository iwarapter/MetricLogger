package com.iadams.BuildMgmt

import com.iadams.BuildMgmt.Java
import com.iadams.BuildMgmt.Project
import com.iadams.BuildMgmt.User
import com.iadams.BuildMgmt.Machine

class Build {
	
	String task
	String outcome
	String err_msg
	String time
	String project_dir
	String ver
	String grp
	
	static belongsTo = [ java : Java, user : User, machine : Machine, project : Project ]
	
    static constraints = {
    }
}
