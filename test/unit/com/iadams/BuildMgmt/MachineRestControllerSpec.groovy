package com.iadams.BuildMgmt

import com.iadams.BuildMgmt.Machine
import com.iadams.BuildMgmt.MachineRestController

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification
/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(MachineRestController)
@Mock(Machine)
class MachineRestControllerSpec extends Specification {

	void "GET a list of machines as JSON"() {
		
		when: "I invoke the list action"
		controller.list()
		
		then: "I receive the expected machines as a JSON list"
		response.json*.name.sort() == ["desktop", "laptop"]
	}
	
	void "GET a single machine as JSON"() {
		
		when: "I invoke the show action with a machine name"
		controller.show("desktop")
		
		then: "I get the machine back"
		response.json.name == "desktop"
	}
	
	def setup() {
		def laptop = new Machine(name: "laptop", os: "Windows", os_ver: "6.1", os_arch: "x64").save(failOnError: true)
		def desktop = new Machine(name: "desktop", os: "Linux", os_ver: "5.9", os_arch: "x86").save(failOnError: true)
	}
}