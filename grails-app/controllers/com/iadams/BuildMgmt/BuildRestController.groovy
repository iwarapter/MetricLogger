package com.iadams.BuildMgmt

import com.iadams.BuildMgmt.Build

import grails.converters.JSON
import grails.converters.XML

class BuildRestController {

	def list() {
		
		def body
		body = Build.list()
		
		withFormat {
			json { render body as JSON }
			xml { render body as XML }
		}
	}
	
	def save() {
		def body
		def build = new Build(params.build)
		if(build.validate() && build.save()) {
			response.status = 201
			body = [id: build.id]
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
