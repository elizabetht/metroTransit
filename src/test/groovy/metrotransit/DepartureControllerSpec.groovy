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
@TestFor(DepartureController)
class DepartureControllerSpec extends Specification {

    RestBuilder restBuilder
    DepartureController departureController
    ResponseEntity responseEntity
    RestResponse restResponse


    def setup() {
        restBuilder = Mock(RestBuilder)
        responseEntity = Mock(ResponseEntity)
        restResponse = Mock(RestResponse)
        departureController = new DepartureController(
                restBuilder: restBuilder
        )
    }

    def cleanup() {
    }

    void "test departure controller"() {
        def departureJson = "[{\"Actual\":true,\"BlockNumber\":1058,\"DepartureText\":\"20 Min\",\"DepartureTime\":\"/Date(1460340540000-0500)/\"," +
                "\"Description\":\"Fremont Av/Brklyn Ctr/Transit Ctr\",\"Gate\":\"\",\"Route\":\"5\",\"RouteDirection\":\"NORTHBOUND\"," +
                "\"Terminal\":\"M\",\"VehicleHeading\":0,\"VehicleLatitude\":44.949111,\"VehicleLongitude\":-93.262614}]"

        when:
        departureController.show()
        then:
        1 * restBuilder.get(_) { _ } >> restResponse
        1 * restResponse.responseEntity >> responseEntity
        1 * responseEntity.body >> departureJson
        (JSON.parse(response.text) as List<Departure>)[0].DepartureText == "20 Min"
    }
}
