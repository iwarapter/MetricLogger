package com.iadams.BuildMgmt

import com.iadams.BuildMgmt.JavaInstall
import com.iadams.BuildMgmt.JavaRuntime
import com.iadams.BuildMgmt.JavaVirtualMachine

import spock.lang.*
import grails.plugin.spock.*

class JavaIntegrationSpec extends IntegrationSpec {
	
	def runtime
	def jvm

	def "Saving our first Java install to the database"() {
		
		given: "A brand new java install"
		def J = new JavaInstall(ver: "1.6", vendor: "Oracle", home: "C:\\JDK", jvm: jvm, runtime: runtime)
		
		when: "we save the java install"
		J.save()
		
		then: "we find the java install in the db"
		J.errors.errorCount == 0
		J.id != null
		JavaInstall.get(J.id).ver == J.ver
	}
	
	def setup() {
		runtime = new JavaRuntime(name: "Java(TM) SE Runtime Environment", ver: "1.6").save(failOnError: true)
		jvm = new JavaVirtualMachine(name: "JVM name", ver: "1.6", vendor: "Oracle").save(failOnError: true)
	}
}