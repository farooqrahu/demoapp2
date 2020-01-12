angular.module('myApp')

.run(function($rootScope) {

})

.controller('NavController', function($http, $scope, AuthService, $state, $rootScope) {
    $scope.user=null;
	$scope.$on('LoginSuccessful', function() {
		$scope.user = AuthService.user;
	});
	$scope.$on('LogoutSuccessful', function() {
		$scope.user = null;
	});


    $scope.loadData = function () {
        $http.get('http://localhost:8080/products').then(function (response) {
            $rootScope.products = response.data;
        });
    };

    $scope.findProduct = function () {
        $http.get('http://localhost:8080/find/' + $scope.find).then(function (response) {

            $rootScope.products = response.data;
        });

        $scope.find = '';
    };
    $scope.toogleThis= function () {
        $(".page-body").toggleClass("sidebar-collpased");
    };
    $scope.logout = function() {
        $http.post('logout', {}).success(function() {
            $rootScope.authenticated = false;
            $state.go("login");
        }).error(function(data) {
            $rootScope.authenticated = false;
        });
    }
});
