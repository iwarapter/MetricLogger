package com.iadams.BuildMgmt

import com.iadams.BuildMgmt.Build
import com.iadams.BuildMgmt.Java
import com.iadams.BuildMgmt.JavaRuntime
import com.iadams.BuildMgmt.JavaVirtualMachine
import com.iadams.BuildMgmt.Project
import com.iadams.BuildMgmt.User
import com.iadams.BuildMgmt.Machine

import spock.lang.*
import grails.plugin.spock.*

class BuildIntegrationSpec extends IntegrationSpec {
	
	def joe
	def exampleProj
	def laptop
	def J

	def "Saving our first build to the database"() {
		
		given: "A brand new build"
		def build1 = new Build(	name: "grdl-Build1",
								outcome: "Success",
								err_msg: "",
								time: "1.12345",
								project_dir: "C:\\workingDir",
								ver: "1.0",
								group: "com.iadams.BuildMgmt",
								user: joe.id,
								machine: laptop.id,
								java: J.id,
								project: exampleProj.id )
		
		when: "we save the build"
		build1.save()
		
		then: "we find the build in the db"
		build1.errors.errorCount == 0
		build1.id != null
		Build.get(build1.id).ver == build1.ver
	}
	
	def setup() {
		joe = new User(name: "Joe").save(failOnError: true)
		exampleProj = new Project(name: "example", tasks: ["one", "two"], description: "The example project description").save(failOnError: true)
		laptop = new Machine(name: "Laptop", os: "Windows", os_ver: "6.1", os_arch: "x64").save(failOnError: true)
		def runtime = new JavaRuntime(name: "Java(TM) SE Runtime Environment", ver: "1.6").save(failOnError: true)
		def jvm = new JavaVirtualMachine(name: "JVM name", ver: "1.6", vendor: "Oracle").save(failOnError: true)
		J = new Java(ver: "1.6", vendor: "Oracle", home: "C:\\JDK", jvm: jvm.id, runtime: runtime.id).save(failOnError: true)
	}
}