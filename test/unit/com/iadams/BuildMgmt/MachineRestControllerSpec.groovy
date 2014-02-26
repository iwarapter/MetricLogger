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
	
	void "GET a list of machines as XML"() {

		when: "I invoke the list action"
		response.format = "xml"
		controller.list()

		then: "I get the expected machines as a JSON list"
		response.xml.machine.name*.text().sort() == [
			"desktop",
			"laptop"]
	}
	
	void "GET a single machine as XML"() {

		when: "I invoke the show action with a user name"
		response.format = "xml"
		controller.show("desktop")

		then: "I get the expected builds as a JSON list"
		response.xml.name.text() == "desktop"
	}
	
	void "POST a single machine as JSON"() {
		when: "I request a new machine"
		request.json = '{"machine": {"name": "server1", "os": "Linux", "os_ver": "5.9", "os_arch": "x64"}}'
		controller.save()

		then: "I get a 201 JSON response with the ID of the new machine"
		response.status == 201
		response.json.id instanceof Number
	}
	
	void "POST a single user as XML"() {
		when: "I request a new machine"
		request.xml = '<machine><name>server2</name><os>Linux</os><os_ver>5.9</os_ver><os_arch>x64</os_arch></machine>'
		response.format = "xml"
		controller.save()

		then: "I get a 201 JSON response with the ID of the new machine"
		response.status == 201
		response.xml.entry.@key.text() == "id"
		response.xml.entry.text().isNumber()
	}
	
	def setup() {
		def laptop = new Machine(name: "laptop", os: "Windows", os_ver: "6.1", os_arch: "x64").save(failOnError: true)
		def desktop = new Machine(name: "desktop", os: "Linux", os_ver: "5.9", os_arch: "x86").save(failOnError: true)
	}
}