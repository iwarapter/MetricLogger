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
}
