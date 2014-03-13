package com.iadams.BuildMgmt

import com.iadams.BuildMgmt.Logfile
import com.iadams.BuildMgmt.LogfileRestController

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification
/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(LogfileRestController)
@Mock(Logfile)
class LogfileRestControllerSpec extends Specification {

	void "POST a single log as JSON"() {
		def s = new File("rough-design-doc.txt").text
		String encoded = s.bytes.encodeBase64().toString()
		when: "I request a new log"
		request.json = '{"log": {"myFile": "' + encoded + '"}}'
		controller.save()

		then: "I get a 201 JSON response with the ID of the new logfile"
		response.status == 201
		response.json.id instanceof Number
	}
}