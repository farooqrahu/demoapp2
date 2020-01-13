angular.module('myApp')

.controller('HomeController', function($http, $scope, $state, AuthService, $rootScope) {
    $scope.user = AuthService.user;
});
