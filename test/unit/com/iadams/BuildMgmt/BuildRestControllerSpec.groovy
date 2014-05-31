package com.iadams.BuildMgmt

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(BuildRestController)
@Mock([User, Build, Project, Machine, JavaInstall, Logfile, Task, Plugin])
class BuildRestControllerSpec extends Specification {
	
	def b1, b2

	void "GET a list of builds as JSON"() {
		
		when: "I invoke the list action"
		controller.list()
		
		then: "I receive the expected build as a JSON list"
		response.json.id.sort() == [ b1.id, b2.id ]
	}
	
	void "GET a list of builds as XML"() {
		
		when: "I invoke the list action"
		response.format = "xml"
		controller.list()

		then: "I get the expected builds as a JSON list"
		response.xml.build.outcome*.text().sort() == [ b2.outcome, b1.outcome ]
	}
	
	def setup() {
		def joe = new User(name: "Joe").save(failOnError: true)
		def log1 = new Logfile(myFile: "Example log file contents 1").save(failOnError: true)
		def laptop = new Machine(name: "laptop", os: "Windows", os_ver: "6.1", os_arch: "x64").save(failOnError: true)
		def javaInstall1 = new JavaInstall(ver: "1.7.0_07",	vendor: "Oracle Corporation", home: "C:\\Program Files\\Java\\jdk1.7.0_07\\jre", arch: 64, runtimeName: "Java(TM) SE Runtime Environment", runtimeVer: "1.7.0_07-b11", jvmName: "Java HotSpot(TM) 64-Bit Server VM", jvmVer: "23.3-b01").save(failOnError: true)
		def task1 = new Task(name: "clean").save(failOnError: true)
		def task2 = new Task(name: "build").save(failOnError: true)
		def plugin1 = new Plugin(name: "DBdeploy").save(failOnError: true)
		def exampleProj1 = new Project(name: "example1", description: "The example project description", tasks: [task1, task2], plugins: [plugin1]).save(failOnError: true)
		
		
		b1 = new Build(outcome: "Success", err_msg: "", time: "1.123", project_dir: "C:\\dir", ver: "1.0", grp: "com.iadams.BuildMgmt", javaInstall: javaInstall1, user: joe, machine: laptop, project: exampleProj1, buildLog: log1, tasks: task1).save(failOnError: true)
		b2 = new Build(outcome: "Failed", err_msg: "Didn't work", time: "1.123", project_dir: "C:\\dir", ver: "1.0", grp: "com.iadams.BuildMgmt", javaInstall: javaInstall1, user: joe, machine: laptop, project: exampleProj1, buildLog: log1, tasks: [task1, task2]).save(failOnError: true)
	}
}