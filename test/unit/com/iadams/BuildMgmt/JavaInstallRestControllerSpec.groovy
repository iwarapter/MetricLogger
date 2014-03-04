package com.iadams.BuildMgmt

import com.iadams.BuildMgmt.JavaInstall
import com.iadams.BuildMgmt.JavaInstallRestController

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification
/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(JavaInstallRestController)
@Mock(JavaInstall)
class JavaInstallRestControllerSpec extends Specification {

	void "GET a list of JavaInstalls as JSON"() {
		
		when: "I invoke the list action"
		controller.list()
		
		then: "I receive the expected JavaInstalls as a JSON list"
		response.json*.ver.sort() == ["1.6", "1.7.0_07"]
	}
	
	void "GET a single JavaInstall as JSON"() {
		
		when: "I invoke the show action with a JavaInstall name"
		controller.show("1.7.0_07", 64)
		
		then: "I get the JavaInstall back"
		response.json.ver == "1.7.0_07"
	}
	
	void "GET a list of JavaInstalls as XML"() {
		
		when: "I invoke the list action"
		response.format = "xml"
		controller.list()

		then: "I get the expected JavaInstalls as a XML list"
		response.xml.javaInstall.ver*.text().sort() == ["1.6","1.7.0_07"]
	}
	
	def setup() {
		def J1 = new JavaInstall(ver: "1.6",
			vendor: "Oracle",
			home: "C:\\JDK1.6",
			arch: 32,
			runtimeName: "Java(TM) SE Runtime Environment",
			runtimeVer: "1.6",
			jvmName: "JVM name",
			jvmVer: "1.6").save(failOnError: true)
		
		def J2 = new JavaInstall(ver: "1.7.0_07",
			vendor: "Oracle Corporation",
			home: "C:\\Program Files\\Java\\jdk1.7.0_07\\jre",
			arch: 64,
			runtimeName: "Java(TM) SE Runtime Environment",
			runtimeVer: "1.7.0_07-b11",
			jvmName: "Java HotSpot(TM) 64-Bit Server VM",
			jvmVer: "23.3-b01").save(failOnError: true)
	}
}