class UrlMappings {

	static mappings = {
		def buildRestArgs = [controller: "BuildRest", parseRequest: true]
        def c = {
            action = [GET: "list", POST: "save", PUT: "unsupported", DELETE: "unsupported"]
        }

        "/api/v${v}/Builds" buildRestArgs, c
        "/api/Builds" buildRestArgs, c

        "/api/Builds/$id" controller: "BuildRest", parseRequest: true, {
            action = [GET: "show", POST: "unsupported", PUT: "update", DELETE: "delete"]
        }
		
		def userRestArgs = [controller: "UserRest", parseRequest: true]
		
		"/api/v${v}/Users" userRestArgs, c
		"/api/Users" userRestArgs, c
		
		"/api/Users/$id" controller: "UserRest", parseRequest: true, {
			action = [GET: "show", POST: "save", PUT: "update", DELETE: "delete"]
		}
		
		def machineRestArgs = [controller: "MachineRest", parseRequest: true]
		
		"/api/v${v}/Machines" machineRestArgs, c
		"/api/Machines" machineRestArgs, c
		
		"/api/Machines/$id" controller: "MachineRest", parseRequest: true, {
			action = [GET: "show", POST: "save", PUT: "update", DELETE: "delete"]
		}
		
		def projectRestArgs = [controller: "ProjectRest", parseRequest: true]
		
		"/api/v${v}/Projects" projectRestArgs, c
		"/api/Projects" projectRestArgs, c
		
		"/api/Projects/$id" controller: "ProjectRest", parseRequest: true, {
			action = [GET: "show", POST: "save", PUT: "update", DELETE: "delete"]
		}
		
		def logRestArgs = [controller: "LogfileRest", parseRequest: true]
		
		"/api/v${v}/Logfiles" logRestArgs, c
		"/api/Logfiles" logRestArgs, c
		
		"/api/Logfiles/$id" controller: "LogfileRest", parseRequest: true, {
			action = [GET: "show", POST: "save", PUT: "update", DELETE: "delete"]
		}
		
		def javaRestArgs = [controller: "JavaInstallRest", parseRequest: true]
		
		"/api/v${v}/JavaInstalls" javaRestArgs, c
		"/api/JavaInstalls" javaRestArgs, c
		
		"/api/JavaInstalls/$id" controller: "JavaInstallRest", parseRequest: true, {
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
