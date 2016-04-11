package metrotransit

import grails.converters.JSON
import grails.plugins.rest.client.RestBuilder
import grails.rest.RestfulController

class RouteController extends RestfulController {

    RestBuilder restBuilder
    static responseFormats = ['json', 'xml']

    RouteController() {
        super(Route)
    }

    def index() {
        String url = "http://svc.metrotransit.org/NexTrip/Routes"
        def resp = restBuilder.get(url) {
            accept "application/json"
        }

        List<Route> routes = JSON.parse(resp.responseEntity.body) as List<Route>
        respond routes
    }
}
