package com.iadams.BuildMgmt

import com.iadams.BuildMgmt.User
import com.iadams.BuildMgmt.UserRestController

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(UserRestController)
@Mock(User)
class UserRestControllerSpec extends Specification {

	void "GET a list of users as JSON"() {
		
		when: "I invoke the show action without an ID"
		controller.show()
		
		then: "I receive the expected users as a JSON list"
		response.json*.name.sort() == ["Dave", "Joe"]
	}
	
	void "GET a single user as JSON"() {
		
		when: "I invoke the show action with a user name"
		controller.show("Dave")
		
		then: "I get the user back"
		response.json.name == "Dave"
	}
	
	def setup() {
		def joe = new User(name: "Joe").save(failOnError: true)
		def dave = new User(name: "Dave").save(failOnError: true)
	}
}