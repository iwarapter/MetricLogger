package com.iadams.BuildMgmt

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(ProjectRestController)
@Mock([Project, Task, Plugin])
class ProjectRestControllerSpec extends Specification {
	
	def exampleProj1
	def exampleProj2

	void "GET a list of projects as JSON"() {
		
		when: "I invoke the list action"
		controller.list()
		
		then: "I receive the expected projects as a JSON list"
		response.json*.id.sort() == [exampleProj1.id, exampleProj2.id]
	}
	
	void "GET a single project as JSON"() {
		
		when: "I invoke the show action with a project name"
		controller.show(exampleProj1.name)
		
		then: "I get the project back"
		response.json.id == exampleProj1.id
	}
	
	void "GET a list of projects as XML"() {
		
		when: "I invoke the list action"
		response.format = "xml"
		controller.list()

		then: "I get the expected projects as a JSON list"
		response.xml.project.name*.text().sort() == [
			exampleProj1.name,
			exampleProj2.name]
	}
	
	void "GET a single project as XML"() {

		when: "I invoke the show action with a project name"
		response.format = "xml"
		controller.show(exampleProj2.name)

		then: "I get the expected projects as a JSON list"
		response.xml.name.text() == exampleProj2.name
	}
	
	void "POST a single project as JSON"() {
		when: "I request a new project"
		request.json = '{"project": {"name": "example3", "description": "The example project description"}}'
		controller.save()

		then: 'I get a 201 JSON response with the ID of the new project'
		response.status == 201
		response.json.id instanceof Number
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
		def task1 = new Task(name: "clean").save(failOnError: true)
		def task2 = new Task(name: "build").save(failOnError: true)
		
		def plugin1 = new Plugin(name: "Java").save(failOnError: true)
		
		exampleProj1 = new Project(name: "example1", tasks: [task1], plugin: [plugin1], description: "The example project description").save(failOnError: true)
		exampleProj2 = new Project(name: "example2", tasks: [task1, task2],plugin: [plugin1],  description: "The example project description").save(failOnError: true)
	}
}