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
@TestFor(StopController)
class StopControllerSpec extends Specification {

    RestBuilder restBuilder
    StopController stopController
    ResponseEntity responseEntity
    RestResponse restResponse

    def setup() {
        restBuilder = Mock(RestBuilder)
        responseEntity = Mock(ResponseEntity)
        restResponse = Mock(RestResponse)
        stopController = new StopController(
                restBuilder: restBuilder
        )
    }

    def cleanup() {
    }

    void "test stop controller"() {
        def stopJson = "[{\"Text\":\"Mall of America Transit Station\",\"Value\":\"MAAM\"}," +
                "{\"Text\":\"Portland Ave and 77th St\",\"Value\":\"77PO\"}]"

        when:
        stopController.show()
        then:
        1 * restBuilder.get(_) { _ } >> restResponse
        1 * restResponse.responseEntity >> responseEntity
        1 * responseEntity.body >> stopJson
        (JSON.parse(response.text) as List<Stop>)[0].Value == "MAAM"
    }
}
