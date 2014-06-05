package com.iadams.BuildMgmt

import grails.converters.JSON
import grails.converters.XML

class PluginRestController {

	def list() {
		
		def body
		body = Plugin.list()
		
		withFormat {
			json { render body as JSON }
			xml { render body as XML }
		}
	}
	
	def show() {
		def body
		body = Plugin.findByName(params.name)
		
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
		def plug = new Plugin(params.plugin)
		if(plug.validate() && plug.save()) {
			response.status = 201
			body = [id: plug.id]
			
			plug.addToProjects(Project.get(params.plugin.project))
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
