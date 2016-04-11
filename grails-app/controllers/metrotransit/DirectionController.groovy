package metrotransit

import grails.converters.JSON
import grails.plugins.rest.client.RestBuilder
import grails.rest.RestfulController

class DirectionController extends RestfulController {

    RestBuilder restBuilder
    static responseFormats = ['json', 'xml']

    DirectionController() {
        super(Direction)
    }

    def show() {
        String url = "http://svc.metrotransit.org/NexTrip/Directions/" + params.id
        def resp = restBuilder.get(url) {
            accept "application/json"
        }

        List<Direction> directions = JSON.parse(resp.responseEntity.body) as List<Direction>
        respond directions
    }
}
