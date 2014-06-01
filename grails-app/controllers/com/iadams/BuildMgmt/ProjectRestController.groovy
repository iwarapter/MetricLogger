package com.iadams.BuildMgmt

import com.iadams.BuildMgmt.Project

import grails.converters.JSON
import grails.converters.XML

class ProjectRestController {

    def list() {
		
		def body
		body = Project.list()
		
		withFormat {
			json { render body as JSON }
			xml { render body as XML }
		}
	}
	
	def show() {
		def body
		body = Project.findByName(params.name)
		
		if(!body){
			response.status = 200
			body = [error: "Invalid data"]
		}
		
		withFormat {
			json { render body as JSON }
			xml { render body as XML }
		}
	}
	
	def save() {
		def body
		def proj = new Project(params.project)
		if(proj.validate() && proj.save()) {
			response.status = 201
			body = [id: proj.id]
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
