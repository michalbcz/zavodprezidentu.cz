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

        /* admin mapping */
        "/admin/scraperjob/${System.env.RUN_SCRAPER_JOB_SECRET ?: 'run'}"(controller: "adminApi", action: "runScraper")
	}
}
