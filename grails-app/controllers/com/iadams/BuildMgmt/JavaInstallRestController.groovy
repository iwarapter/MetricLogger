package com.iadams.BuildMgmt

import com.iadams.BuildMgmt.JavaInstall

import grails.converters.JSON
import grails.converters.XML

class JavaInstallRestController {

    def list() {
		
		def body
		body = JavaInstall.list()
		
		withFormat {
			json { render body as JSON }
			xml { render body as XML }
		}
	}
	//, String jvmVer, String runtimeVer
	def show(String ver, int arch ) {
		def body
		body = JavaInstall.findAllByVerAndArch(ver,arch)
		
		withFormat {
			json { render body as JSON }
			xml { render body as XML }
		}
	}
}
