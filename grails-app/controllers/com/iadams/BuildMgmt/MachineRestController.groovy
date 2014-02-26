package com.iadams.BuildMgmt

import com.iadams.BuildMgmt.Machine

import grails.converters.JSON
import grails.converters.XML

class MachineRestController {

    def list() {
		
		def body
		body = Machine.list()
		
		withFormat {
			json { render body as JSON }
			xml { render body as XML }
		}
	}
	
	def show(String name) {
		def body
		body = Machine.findByName(name)
		
		withFormat {
			json { render body as JSON }
			xml { render body as XML }
		}
	}
	
	def save() {
		def body
		def machine = new Machine(params.machine)
		if(machine.validate() && machine.save()) {
			response.status = 201
			body = [id: machine.id]
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
