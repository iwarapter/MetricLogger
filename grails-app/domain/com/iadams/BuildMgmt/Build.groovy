package com.iadams.BuildMgmt

import com.iadams.BuildMgmt.JavaInstall
import com.iadams.BuildMgmt.Project
import com.iadams.BuildMgmt.User
import com.iadams.BuildMgmt.Machine
import com.iadams.BuildMgmt.Logfile

class Build {
	
	String task
	String outcome
	String err_msg
	String time
	String project_dir
	String ver
	String grp
	
	static belongsTo = [ javaInstall : JavaInstall, user : User, machine : Machine, project : Project, buildLog : Logfile ]
	
    static constraints = {
    }
}
