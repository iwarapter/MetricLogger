package com.iadams.BuildMgmt

import com.iadams.BuildMgmt.Build
import com.iadams.BuildMgmt.BuildRestController
import com.iadams.BuildMgmt.User
import com.iadams.BuildMgmt.Machine
import com.iadams.BuildMgmt.Project
import com.iadams.BuildMgmt.JavaInstall
import com.iadams.BuildMgmt.Logfile

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(BuildRestController)
@Mock([User, Build, Project, Machine, JavaInstall, Logfile])
class BuildRestControllerSpec extends Specification {

	void "GET a list of builds as JSON"() {
		
		when: "I invoke the list action"
		controller.list()
		
		then: "I receive the expected build as a JSON list"
		response.json*.task.sort() == ["build", "clean"]
	}
	
	void "GET a list of builds as XML"() {
		
		when: "I invoke the list action"
		response.format = "xml"
		controller.list()

		then: "I get the expected builds as a JSON list"
		response.xml.build.task*.text().sort() == [	"build", "clean"]
	}
	
	def setup() {
		def joe = new User(name: "Joe").save(failOnError: true)
		def log1 = new Logfile(myFile: "Example log file contents 1").save(failOnError: true)
		def laptop = new Machine(name: "laptop", os: "Windows", os_ver: "6.1", os_arch: "x64").save(failOnError: true)
		def exampleProj1 = new Project(name: "example1", tasks: ["one", "two"], description: "The example project description").save(failOnError: true)
		def javaInstall1 = new JavaInstall(ver: "1.7.0_07",	vendor: "Oracle Corporation", home: "C:\\Program Files\\Java\\jdk1.7.0_07\\jre", arch: 64, runtimeName: "Java(TM) SE Runtime Environment", runtimeVer: "1.7.0_07-b11", jvmName: "Java HotSpot(TM) 64-Bit Server VM", jvmVer: "23.3-b01").save(failOnError: true)
		
		def b1 = new Build(task: "clean", outcome: "Success", err_msg: "", time: "1.123", project_dir: "C:\\dir", ver: "1.0", grp: "com.iadams.BuildMgmt", javaInstall: javaInstall1, user: joe, machine: laptop, project: exampleProj1, buildLog: log1).save(failOnError: true)
		def b2 = new Build(task: "build", outcome: "Failed", err_msg: "Didn't work", time: "1.123", project_dir: "C:\\dir", ver: "1.0", grp: "com.iadams.BuildMgmt", javaInstall: javaInstall1, user: joe, machine: laptop, project: exampleProj1, buildLog: log1).save(failOnError: true)
	}
}