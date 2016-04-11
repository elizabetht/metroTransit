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
@TestFor(RouteController)
class RouteControllerSpec extends Specification {

    RestBuilder restBuilder
    RouteController routeController
    ResponseEntity responseEntity
    RestResponse restResponse

    def setup() {
        restBuilder = Mock(RestBuilder)
        responseEntity = Mock(ResponseEntity)
        restResponse = Mock(RestResponse)
        routeController = new RouteController(
                restBuilder: restBuilder
        )
    }

    def cleanup() {
    }

    void "test route controller"() {
        def routeJson = "[{\"Description\":\"METRO Blue Line\",\"ProviderID\":\"8\",\"Route\":\"901\"}," +
                "{\"Description\":\"METRO Green Line\",\"ProviderID\":\"8\",\"Route\":\"902\"}]"

        when:
        routeController.index()
        then:
        1 * restBuilder.get(_) { _ } >> restResponse
        1 * restResponse.responseEntity >> responseEntity
        1 * responseEntity.body >> routeJson
        (JSON.parse(response.text) as List<Route>)[0].Route == "901"
    }
}
