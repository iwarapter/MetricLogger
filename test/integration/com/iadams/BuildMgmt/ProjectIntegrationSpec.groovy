package com.iadams.BuildMgmt

import com.iadams.BuildMgmt.Project

import spock.lang.*
import grails.plugin.spock.*

class ProjectIntegrationSpec extends IntegrationSpec {
	
	def plugin1
	def task1

	def "Saving our first project to the database"() {
		
		given: "A brand new machine"
		def exampleProj = new Project(name: "example", tasks: [task1], plugins: [plugin1], description: "The example project description")
		
		when: "we save the user"
		exampleProj.save()
		
		then: "we find the user in the db"
		exampleProj.errors.errorCount == 0
		exampleProj.id != null
		Project.get(exampleProj.id).name == exampleProj.name
	}
	
	def setup() {
		task1 = new Task(name: "clean").save(failOnError: true)
		plugin1 = new Plugin(name: "Java").save(failOnError: true)
	}
}