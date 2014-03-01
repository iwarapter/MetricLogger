package com.iadams.BuildMgmt

import com.iadams.BuildMgmt.JavaInstall

import spock.lang.*
import grails.plugin.spock.*

class JavaInstallIntegrationSpec extends IntegrationSpec {

	def "Saving our first Java install to the database"() {
		
		given: "A brand new java install"
		def J = new JavaInstall(ver: "1.6", 
								vendor: "Oracle", 
								home: "C:\\JDK",
								runtimeName: "Java(TM) SE Runtime Environment",
								runtimeVer: "1.6",
								jvmName: "JVM name",
								jvmVer: "1.6")
		
		when: "we save the java install"
		J.save()
		
		then: "we find the java install in the db"
		J.errors.errorCount == 0
		J.id != null
		JavaInstall.get(J.id).ver == J.ver
	}
}