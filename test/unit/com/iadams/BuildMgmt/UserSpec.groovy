package com.iadams.BuildMgmt

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Test)
class UserSpec extends Specification {

	void "Test build to string method"() {
		when: "I create a new build domain object"
		def joe = new User(name: "Joe")
		
		then: "I can read the name"
		joe.toString() == "Joe"
	}
}