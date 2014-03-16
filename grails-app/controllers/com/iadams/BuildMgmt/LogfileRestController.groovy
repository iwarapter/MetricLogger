package com.iadams.BuildMgmt

import com.iadams.BuildMgmt.Logfile

import grails.converters.JSON
import grails.converters.XML

class LogfileRestController {

	def list() {
		
		def body
		body = Logfile.list()
		
		withFormat {
			json { render body as JSON }
			xml { render body as XML }
		}
	}
	
	def show(Long id) {
		def body
		body = Logfile.get(id)
		
		withFormat {
			json { render body as JSON }
			xml { render body as XML }
		}
	}
	
	def save() {
		def body
		def log = new Logfile(params.log)
		if(log.myFile != null){
			log.myFile =  new String(log.myFile.decodeBase64())
		}
		if(log.validate() && log.save()) {
			response.status = 201
			body = [id: log.id]
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
