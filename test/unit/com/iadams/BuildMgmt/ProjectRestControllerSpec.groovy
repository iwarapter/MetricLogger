package com.iadams.BuildMgmt

import com.iadams.BuildMgmt.Project
import com.iadams.BuildMgmt.ProjectRestController

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(ProjectRestController)
@Mock(Project)
class ProjectRestControllerSpec extends Specification {

	void "GET a list of projects as JSON"() {
		
		when: "I invoke the list action"
		controller.list()
		
		then: "I receive the expected projects as a JSON list"
		response.json*.name.sort() == ["example1", "example2"]
	}
	
	void "GET a single project as JSON"() {
		
		when: "I invoke the show action with a project name"
		controller.show("example1")
		
		then: "I get the project back"
		response.json.name == "example1"
	}
	
	void "GET a list of projects as XML"() {
		
		when: "I invoke the list action"
		response.format = "xml"
		controller.list()

		then: "I get the expected projects as a JSON list"
		response.xml.project.name*.text().sort() == [
			"example1",
			"example2"]
	}
	
	void "GET a single project as XML"() {

		when: "I invoke the show action with a project name"
		response.format = "xml"
		controller.show("example1")

		then: "I get the expected projects as a JSON list"
		response.xml.name.text() == "example1"
	}
	
	void "POST a single project as JSON"() {
		when: "I request a new project"
		request.json = '{"project": {"name": "example3", "description": "The example project description"}}'
		controller.save()

		then: 'I get a 201 JSON response with the ID of the new project'
		response.status == 201
		response.json.id instanceof Number
		println response.json
	}
	
	void "POST a single failing project as JSON"() {
		when: "I request a new project"
		request.json = '{"project": {"name": "example3"}}'
		controller.save()

		then: 'I get a 403 JSON response with the error message'
		response.status == 403
		response.json.error == "Invalid data"
	}
	 
	void "POST a single project as XML"() {
		when: 'I request a new project'
		request.xml = '<project><name>example4</name><description>The example project description</description></project>'
		response.format = 'xml'
		controller.save()

		then: 'I get a 201 JSON response with the ID of the new project'
		response.status == 201
		response.xml.entry.@key.text() == 'id'
		response.xml.entry.text().isNumber()
	}
	
	void "POST a single failing project as XML"() {
		when: 'I request a new project'
		request.xml = '<project><name>example4</name></project>'
		response.format = 'xml'
		controller.save()

		then: 'I get a 403 JSON response with the error message'
		response.status == 403
		response.xml.entry.@key.text() == 'error'
		response.xml.entry.text() == 'Invalid data'
	}
	
	def setup() {
		def exampleProj1 = new Project(name: "example1", tasks: ["one", "two"], description: "The example project description").save(failOnError: true)
		def exampleProj2 = new Project(name: "example2", tasks: ["three", "four"], description: "The example project description").save(failOnError: true)
	}
}