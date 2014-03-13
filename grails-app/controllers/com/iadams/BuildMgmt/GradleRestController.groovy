package com.iadams.BuildMgmt

import com.iadams.BuildMgmt.Machine
import com.iadams.BuildMgmt.User

import grails.converters.JSON
import grails.converters.XML

class GradleRestController {

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
		def machine
		def user
		request.withFormat {
		    xml {
				machine = new Machine(	name: request.XML?.machine?.name.text(), 
										os: request.XML?.machine?.os.text(), 
										os_ver: request.XML?.machine?.os_ver.text(), 
										os_arch: request.XML?.machine?.os_arch.text())
				user = new User(name: request.XML?.user?.name.text() )
		    }
		    json {
				machine = new Machine(params.machine)
				user = new User(params.user)
		    }
		}
		if(machine.validate() && machine.save()) {
			response.status = 201
			body = [id: machine.id]
		}
		else {
			response.status = 403
			body = [error: 'Invalid data']
		}

		withFormat {
			json { render body as JSON }
			xml { render body as XML }
		}
	}
}
