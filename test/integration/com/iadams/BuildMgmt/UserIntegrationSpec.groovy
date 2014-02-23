package com.iadams.BuildMgmt

import com.iadams.BuildMgmt.User

import spock.lang.*
import grails.plugin.spock.*

class UserIntegrationSpec extends IntegrationSpec {

	def "Saving our first user to the database"() {
		
		given: "A brand new user"
		def joe = new User(name: "Joe")
		
		when: "we save the user"
		joe.save()
		
		then: "we find the user in the db"
		joe.errors.errorCount == 0
		joe.id != null
		User.getAt(joe.id).name == joe.name
	}
}