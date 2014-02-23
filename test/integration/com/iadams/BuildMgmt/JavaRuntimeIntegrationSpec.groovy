package com.iadams.BuildMgmt

import com.iadams.BuildMgmt.JavaRuntime

import spock.lang.*
import grails.plugin.spock.*

class JavaRuntimeIntegrationSpec extends IntegrationSpec {

	def "Saving our first JavaRuntime to the database"() {
		
		given: "A brand new machine"
		def runtime = new JavaRuntime(name: "Java(TM) SE Runtime Environment", version: "1.6")
		
		when: "we save the user"
		runtime.save()
		
		then: "we find the user in the db"
		runtime.errors.errorCount == 0
		runtime.id != null
		JavaRuntime.get(runtime.id).name == runtime.name
	}
}