package com.iadams.BuildMgmt

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(PluginRestController)
@Mock([Plugin])
class PluginRestControllerSpec extends Specification {

	def plugin1
	def plugin2

	void "GET a list of plugins as JSON"() {
		
		when: "I invoke the list action"
		controller.list()
		
		then: "I receive the expected plugins as a JSON list"
		response.json*.id.sort() == [plugin1.id, plugin2.id].sort()
	}
	
	void "GET a single plugin as JSON"() {
		
		when: "I invoke the show action with a plugin name"
		controller.show(plugin1.name)
		
		then: "I get the plugin back"
		response.json.id == plugin1.id
	}
	
	void "GET a list of plugins as XML"() {
		
		when: "I invoke the list action"
		response.format = "xml"
		controller.list()

		then: "I get the expected plugins as a JSON list"
		response.xml.plugin.name*.text().sort() == [
			plugin1.name,
			plugin2.name].sort()
	}
	
	void "GET a single plugin as XML"() {

		when: "I invoke the show action with a plugin name"
		response.format = "xml"
		controller.show(plugin2.name)

		then: "I get the expected plugins as a JSON list"
		response.xml.name.text() == plugin2.name
	}
	
	void "POST a single plugin as JSON"() {
		when: "I request a new plugin"
		request.json = '{"plugin": {"name": "Java"}}'
		controller.save()

		then: 'I get a 201 JSON response with the ID of the new plugin'
		response.status == 201
		response.json.id instanceof Number
	}
	
	void "POST a single failing plugin as JSON"() {
		when: "I request a new plugin"
		request.json = '{"plugin": {"cheese": "Java"}}'
		controller.save()

		then: 'I get a 403 JSON response with the error message'
		response.status == 403
		response.json.error == "Invalid data"
	}
	 
	void "POST a single plugin as XML"() {
		when: 'I request a new plugin'
		request.xml = '<plugin><name>Java</name></plugin>'
		response.format = 'xml'
		controller.save()

		then: 'I get a 201 JSON response with the ID of the new plugin'
		response.status == 201
		response.xml.entry.@key.text() == 'id'
		response.xml.entry.text().isNumber()
	}
	
	void "POST a single failing plugin as XML"() {
		when: 'I request a new plugin'
		request.xml = '<plugin><cheese>Java</cheese></plugin>'
		response.format = 'xml'
		controller.save()

		then: 'I get a 403 JSON response with the error message'
		response.status == 403
		response.xml.entry.@key.text() == 'error'
		response.xml.entry.text() == 'Invalid data'
	}
	
	def setup() {		
		plugin1 = new Plugin(name: "Java").save(failOnError: true)
		plugin2 = new Plugin(name: "Groovy").save(failOnError: true)
	}
}