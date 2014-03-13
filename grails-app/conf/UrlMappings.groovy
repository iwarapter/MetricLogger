class UrlMappings {

	static mappings = {
		def args1 = [controller: "BuildRest", parseRequest: true]
        def c = {
            action = [GET: "list", POST: "save", PUT: "unsupported", DELETE: "unsupported"]
        }

        "/api/v${v}/Builds" args1, c
        "/api/Builds" args1, c

        "/api/Builds/$id" controller: "BuildRest", parseRequest: true, {
            action = [GET: "show", POST: "unsupported", PUT: "update", DELETE: "delete"]
        }
		
		def args2 = [controller: "UserRest", parseRequest: true]
		
		"/api/v${v}/Users" args2, c
		"/api/Users" args2, c
		
		"/api/Users/$id" controller: "UserRest", parseRequest: true, {
			action = [GET: "show", POST: "save", PUT: "update", DELETE: "delete"]
		}
		
		def args3 = [controller: "MachineRest", parseRequest: true]
		
		"/api/v${v}/Machines" args3, c
		"/api/Machines" args3, c
		
		"/api/Machines/$id" controller: "MachineRest", parseRequest: true, {
			action = [GET: "show", POST: "save", PUT: "update", DELETE: "delete"]
		}
		
		def args4 = [controller: "LogfileRest", parseRequest: true]
		
		"/api/v${v}/Logfiles" args4, c
		"/api/Logfiles" args4, c
		
		"/api/Logfiles/$id" controller: "LogfileRest", parseRequest: true, {
			action = [GET: "show", POST: "save", PUT: "update", DELETE: "delete"]
		}

		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
