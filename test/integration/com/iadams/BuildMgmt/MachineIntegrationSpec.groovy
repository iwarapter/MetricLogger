package com.iadams.BuildMgmt

import com.iadams.BuildMgmt.Machine

import spock.lang.*
import grails.plugin.spock.*

class MachineIntegrationSpec extends IntegrationSpec {

	def "Saving our first user to the database"() {
		
		given: "A brand new machine"
		def laptop = new Machine(name: "Laptop", os: "Windows", os_ver: "6.1", os_arch: "x64")
		
		when: "we save the user"
		laptop.save()
		
		then: "we find the user in the db"
		laptop.errors.errorCount == 0
		laptop.id != null
		Machine.get(laptop.id).name == laptop.name
	}
}