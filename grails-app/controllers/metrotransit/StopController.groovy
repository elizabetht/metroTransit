package metrotransit

import grails.converters.JSON
import grails.plugins.rest.client.RestBuilder
import grails.rest.RestfulController

class StopController extends RestfulController {

    RestBuilder restBuilder
    static responseFormats = ['json', 'xml']

    StopController() {
        super(Stop)
    }

    def show() {
        String url = "http://svc.metrotransit.org/NexTrip/Stops/" + params.routeId + "/" + params.directionId
        def resp = restBuilder.get(url) {
            accept "application/json"
        }

        List<Stop> stops = JSON.parse(resp.responseEntity.body) as List<Stop>
        respond stops
    }
}
