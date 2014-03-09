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

	def show(String ver, int arch, String jvmVer ) {
		def body
		body = JavaInstall.findByVerAndArchAndJvmVer(ver, arch, jvmVer)
		
		withFormat {
			json { render body as JSON }
			xml { render body as XML }
		}
	}
}
