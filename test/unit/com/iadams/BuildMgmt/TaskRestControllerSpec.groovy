package com.iadams.BuildMgmt

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(TaskRestController)
@Mock([Task])
class TaskRestControllerSpec extends Specification {

	def task1
	def task2

	void "GET a list of tasks as JSON"() {
		
		when: "I invoke the list action"
		controller.list()
		
		then: "I receive the expected tasks as a JSON list"
		response.json*.id.sort() == [task1.id, task2.id].sort()
	}
	
	void "GET a single task as JSON"() {
		
		when: "I invoke the show action with a task name"
		controller.show(task1.name)
		
		then: "I get the task back"
		response.json.id == task1.id
	}
	
	void "GET a list of tasks as XML"() {
		
		when: "I invoke the list action"
		response.format = "xml"
		controller.list()

		then: "I get the expected tasks as a JSON list"
		response.xml.task.name*.text().sort() == [
			task1.name,
			task2.name].sort()
	}
	
	void "GET a single task as XML"() {

		when: "I invoke the show action with a task name"
		response.format = "xml"
		controller.show(task2.name)

		then: "I get the expected tasks as a JSON list"
		response.xml.name.text() == task2.name
	}
	
	void "POST a single task as JSON"() {
		when: "I request a new task"
		request.json = '{"task": {"name": "Java", "description": "The example task description"}}'
		controller.save()

		then: 'I get a 201 JSON response with the ID of the new task'
		response.status == 201
		response.json.id instanceof Number
	}
	
	void "POST a single failing task as JSON"() {
		when: "I request a new task"
		request.json = '{"task": {"cheese": "Java"}}'
		controller.save()

		then: 'I get a 403 JSON response with the error message'
		response.status == 403
		response.json.error == "Invalid data"
	}
	 
	void "POST a single task as XML"() {
		when: 'I request a new task'
		request.xml = '<task><name>Java</name></task>'
		response.format = 'xml'
		controller.save()

		then: 'I get a 201 JSON response with the ID of the new task'
		response.status == 201
		response.xml.entry.@key.text() == 'id'
		response.xml.entry.text().isNumber()
	}
	
	void "POST a single failing task as XML"() {
		when: 'I request a new task'
		request.xml = '<task><cheese>Java</cheese></task>'
		response.format = 'xml'
		controller.save()

		then: 'I get a 403 JSON response with the error message'
		response.status == 403
		response.xml.entry.@key.text() == 'error'
		response.xml.entry.text() == 'Invalid data'
	}
	
	def setup() {		
		task1 = new Task(name: "clean").save(failOnError: true)
		task2 = new Task(name: "build").save(failOnError: true)
	}
}