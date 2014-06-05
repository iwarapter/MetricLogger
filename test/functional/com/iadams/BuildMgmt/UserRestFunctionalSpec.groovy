package com.iadams.BuildMgmt

import geb.spock.GebReportingSpec
import spock.lang.Stepwise

import groovyx.net.http.RESTClient
import static groovyx.net.http.ContentType.*
import groovy.json.*

class UserRestFunctionalSpec extends GebReportingSpec {
	
	private def restUrl = "${ browser.baseUrl }"

	private def client = new RESTClient(restUrl)
	private def json = new JsonBuilder()

	def "Can i Post a new User?"() {
		given: "A json document"
		json {
			user {
				name( "Elvis" )
			}
		}
		
		when: "We POST it to the rest controller"
		def resp = client.post(
			path : 'api/Users',
			body : json.toString(),
			requestContentType : JSON )

		then: "We find the new user"
		resp.status == 201
	}
	
	//TODO: Add an update user test
	
	//TODO: Add a delete user test
}
