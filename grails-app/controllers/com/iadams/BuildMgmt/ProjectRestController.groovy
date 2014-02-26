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
	
	def show(String name) {
		def body
		body = Project.findByName(name)
		
		withFormat {
			json { render body as JSON }
			xml { render body as XML }
		}
	}
}
