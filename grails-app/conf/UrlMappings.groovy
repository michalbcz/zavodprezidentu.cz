class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(controller: "index")
		"500"(view: "/error")
        "404"(controller: "index")
	}
}
