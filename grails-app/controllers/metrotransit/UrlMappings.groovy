package metrotransit

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?" {
            constraints {
                // apply constraints here
            }
        }

        "/routes"(resources: "route")
        "/directions"(resources: "direction")
        "/stops/$routeId/$directionId/"(controller: "stop", action: "show")
        "/departures/$routeId/$directionId/$stopId"(controller: "departure", action: "show")

        "/"(view: '/index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
