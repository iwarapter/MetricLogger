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
	
	def show(String name) {
		def body
		body = Plugin.findByName(name)
		
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
