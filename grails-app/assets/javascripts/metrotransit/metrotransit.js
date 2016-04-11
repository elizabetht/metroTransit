//= wrapped
//= require /angular/angular
//= require /metrotransit/core/metrotransit.core
//= require /metrotransit/index/metrotransit.index
//= require_self
//= require_tree .
//= require_full_tree .

angular.module("metrotransit", [
    "metrotransit.core",
    "metrotransit.index"
]);