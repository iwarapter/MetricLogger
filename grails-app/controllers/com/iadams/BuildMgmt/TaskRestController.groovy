package com.iadams.BuildMgmt

import grails.converters.JSON
import grails.converters.XML

class TaskRestController {
	
	def list() {
		
		def body
		body = Task.list()
		
		withFormat {
			json { render body as JSON }
			xml { render body as XML }
		}
	}
	
	def show(String name) {
		def body
		body = Task.findByName(name)
		
		withFormat {
			json { render body as JSON }
			xml { render body as XML }
		}
	}
	
	def save() {
		def body
		def task = new Task(params.task)
		if(task.validate() && task.save()) {
			response.status = 201
			body = [id: task.id]
		}
		else {
			response.status = 403
			body = [error: "Invalid data"]
		}

		withFormat {
			json { render body as JSON }
			xml { render body as XML }
		}
	}
}
