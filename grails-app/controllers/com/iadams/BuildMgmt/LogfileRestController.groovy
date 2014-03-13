package com.iadams.BuildMgmt

import com.iadams.BuildMgmt.Logfile

import grails.converters.JSON
import grails.converters.XML

class LogfileRestController {

	def save() {
		def body
		def log = new Logfile(params.log)
		log.myFile =  new String(log.myFile.decodeBase64())
		println log
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
