package com.iadams.BuildMgmt

import com.iadams.BuildMgmt.JavaVirtualMachine

import spock.lang.*
import grails.plugin.spock.*

class JavaVirtualMachineIntegrationSpec extends IntegrationSpec {

	def "Saving our first JavaVirtualMachine to the database"() {
		
		given: "A brand new JavaVirtualMachine"
		def jvm = new JavaVirtualMachine(name: "JVM name", ver: "1.6", vendor: "Oracle")
		
		when: "we save the jvm"
		jvm.save()
		
		then: "we find the jvm in the db"
		jvm.errors.errorCount == 0
		jvm.id != null
		JavaVirtualMachine.get(jvm.id).name == jvm.name
	}
}