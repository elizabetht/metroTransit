//= wrapped

angular
    .module("metrotransit")
    .controller("RouteController", RouteController);

function RouteController($http) {
    var vm = this;

    var getRoutes = function(response) {
        vm.routes = response.data;
    };

    $http.get("/routes")
        .then(getRoutes);

    vm.getDirections = function(routeId) {
        $http.get("/directions/" + routeId)
            .then(function(response) {
                vm.directions = response.data;
            })
    };


    vm.getStops = function(routeId, directionId) {
        $http.get("/stops/" + routeId + "/" + directionId)
            .then(function(response) {
                vm.stops = response.data;
            })
    };

    vm.getDeparture = function(routeId, directionId, stopId) {
        $http.get("/departures/" + routeId + "/" + directionId + "/" + stopId)
            .then(function(response) {
                vm.departures = response.data;
            })
    };
}
