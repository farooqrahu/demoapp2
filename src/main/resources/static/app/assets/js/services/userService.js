'use strict';

angular.module('myApp').factory('UserService', ['$http', '$q', '$window', function ($http, $q, $window) {
    var factory = {
        registerUser: registerUser,
    };
    return factory;

    function registerUser(value) {
        return $http.post("api/auth/register", value)
            .then(function (data) {
                return data;
            })
            .catch(function (e) {
                return e;
            });
    }
}]);
