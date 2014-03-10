package com.iadams.BuildMgmt

import com.iadams.BuildMgmt.Machine
import com.iadams.BuildMgmt.MachineRestController

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification
/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(GradleRestController)
@Mock(Machine)
class GradleRestControllerSpec extends Specification {

	void "POST a single machine as JSON"() {
		when: "I request a new machine"
		request.json = '{"machine": {"name": "server1", "os": "Linux", "os_ver": "5.9", "os_arch": "x64"}, "user": {"name": "Goose"}}'
		controller.save()

		then: "I get a 201 JSON response with the ID of the new machine"
		response.status == 201
		response.json.id instanceof Number
	}
	
	void "POST a single user as XML"() {
		when: "I request a new machine"
		request.xml = '<xml><machine><name>server2</name><os>Linux</os><os_ver>5.9</os_ver><os_arch>x64</os_arch></machine><user><name>jesus</name></user></xml>'
		response.format = "xml"
		controller.save()

		then: "I get a 201 XML response with the ID of the new machine"
		response.status == 201
		response.xml.entry.@key.text() == "id"
		response.xml.entry.text().isNumber()
	}
}