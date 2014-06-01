package com.iadams.BuildMgmt

import grails.converters.JSON
import grails.converters.XML

class UserRestController {

	def list() {
		
		def body
		body = User.list()
		
		withFormat {
			json { render body as JSON }
			xml { render body as XML }
		}
	}
	
	def show() {
		def body
		body = User.findByName(params.name)
		
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
		def user = new User(params.user)
		if(user.validate() && user.save()) {
			response.status = 201
			body = [id: user.id]
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
