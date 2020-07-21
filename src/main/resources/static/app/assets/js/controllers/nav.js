angular.module('myApp')

.run(function($rootScope) {

})

.controller('NavController', function($http, $scope, AuthService, $state, $rootScope) {

	$scope.$on('LoginSuccessful', function() {
        $rootScope.user = AuthService.user;
	});

	$scope.$on('LogoutSuccessful', function() {
        $rootScope.user = null;
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
        $http.post('logout', {}).success(function(result) {
        $("#logout").show("true");
            $rootScope.authenticated = false;
            $rootScope.user=null;
            $state.go("login");
        }).error(function(data) {
            $rootScope.authenticated = false;
        });
    }
});
