package metrotransit

import grails.converters.JSON
import grails.plugins.rest.client.RestBuilder

class DepartureController {

    RestBuilder restBuilder
    static responseFormats = ['json', 'xml']

    def show() {
        String url = "http://svc.metrotransit.org/NexTrip/" + params.routeId + "/" + params.directionId + "/" + params.stopId
        def resp = restBuilder.get(url) {
            accept "application/json"
        }

        List<Departure> departures = JSON.parse(resp.responseEntity.body) as List<Departure>
        respond departures
    }
}
