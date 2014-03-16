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
	
	def encoded = 'Example log file contents'.bytes.encodeBase64().toString()
	
	def setup() {
		def log1 = new Logfile(myFile: "Example log file contents 1").save(failOnError: true)
		def log2 = new Logfile(myFile: "Example log file contents 2").save(failOnError: true)
	}
	
	void "GET a list of logs as JSON"() {
		when: "I request a list of logs"
		controller.list()
		
		then: "I get the expected logs back as a JSON list"
		response.json*.myFile.sort() == [
			"Example log file contents 1",
			"Example log file contents 2"
			]
	}
	
	void "GET a list of logs as XML"() {
		when: "I request a list of logs"
		response.format = "xml"
		controller.list()
		
		then: "I get the expected logs back as a XML list"
		response.xml.logfile.myFile*.text().sort() == [
			"Example log file contents 1",
			"Example log file contents 2"
			]
	}
	
	void "GET a single log as JSON"() {
		when: "I request a log"
		controller.show(1)
		
		then: "I get the expected log back as a JSON"
		response.json.myFile == "Example log file contents 1"
	}

	void "GET a single log as XML"() {
		
		when: "I invoke the show action with a log id"
		response.format = "xml"
		controller.show(2)

		then: "I get the expected log as a JSON"
		response.xml.myFile.text() == "Example log file contents 2"
	}
	
	void "POST a single log as JSON"() {
		when: "I request a new log"
		request.json = '{"log": {"myFile": "' + encoded + '"}}'
		controller.save()

		then: "I get a 201 JSON response with the ID of the new logfile"
		response.status == 201
		response.json.id instanceof Number
	}
	
	void "POST a single log as XML"() {
		when: "I request a new log"
		request.xml = '<log><myFile>'+ encoded + '</myFile></log>'		
		response.format = "xml"
		controller.save()

		then: "I get a 201 XML response with the ID of the new logfile"
		response.status == 201
		response.xml.entry.@key.text() == "id"
        response.xml.entry.text().isNumber()
	}
}