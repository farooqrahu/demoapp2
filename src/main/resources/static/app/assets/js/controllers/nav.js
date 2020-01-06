angular.module('myApp')

.run(function($rootScope) {

})

.controller('NavController', function($http, $scope, AuthService, $state, $rootScope,$cookieStore) {
	$scope.$on('LoginSuccessful', function() {
		$scope.user = AuthService.user;
	});
	$scope.$on('LogoutSuccessful', function() {
		$scope.user = null;
	});
	$scope.logout = function() {
		AuthService.user = null;
        $cookieStore.put('isLogged',false);
		$rootScope.$broadcast('LogoutSuccessful');
		$state.go('login');
	};

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
    }

});
