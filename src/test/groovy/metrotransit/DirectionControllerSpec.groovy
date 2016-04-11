package metrotransit

import grails.converters.JSON
import grails.plugins.rest.client.RestBuilder
import grails.plugins.rest.client.RestResponse
import grails.test.mixin.TestFor
import org.springframework.http.ResponseEntity
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(DirectionController)
class DirectionControllerSpec extends Specification {

    RestBuilder restBuilder
    DirectionController directionController
    ResponseEntity responseEntity
    RestResponse restResponse

    def setup() {
        restBuilder = Mock(RestBuilder)
        responseEntity = Mock(ResponseEntity)
        restResponse = Mock(RestResponse)
        directionController = new DirectionController(
                restBuilder: restBuilder
        )
    }

    def cleanup() {
    }

    void "test direction controller"() {
        def directionJson = "[{\"Text\":\"NORTHBOUND\",\"Value\":\"4\"}," +
                "{\"Text\":\"SOUTHBOUND\",\"Value\":\"1\"}]"

        when:
        directionController.show()
        then:
        1 * restBuilder.get(_) { _ } >> restResponse
        1 * restResponse.responseEntity >> responseEntity
        1 * responseEntity.body >> directionJson
        (JSON.parse(response.text) as List<Direction>)[0].Text == "NORTHBOUND"
    }
}
