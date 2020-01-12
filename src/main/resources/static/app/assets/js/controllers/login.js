angular.module('myApp')

.controller('LoginController', function($http, $scope, $state, AuthService, $rootScope) {

	var authenticate = function(callback) {
		$http.get('api/user').success(function(data) {
			if (data.name) {
				$rootScope.authenticated = true;
			} else {
				$rootScope.authenticated = false;
			}
			callback && callback();
		}).error(function() {
			$rootScope.authenticated = false;
			callback && callback();
		});
	};

	authenticate();
    $scope.credentials={};
	$scope.login = function() {
		$http.post('login',$.param($scope.credentials), {
			headers : {
				"content-type" : "application/x-www-form-urlencoded"
			}
		}).success(function(data) {
			authenticate(function() {
				if ($rootScope.authenticated) {
					$state.go("home");
					$scope.error = false;
				} else {
					$state.go("login");
					$scope.error = true;
				}
			});
		}).error(function(data) {
			$state.go("login");
			$scope.error = true;
			$rootScope.authenticated = false;
		})
	};





	});
