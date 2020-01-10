angular.module('myApp')

.controller('UsersController', function($http, $scope,$state, AuthService) {
	var edit = false;
	this.userControllerVm=null;

	$scope.findAllUsers = function() {
		$http.get('api/users').success(function(res) {
	console.log(res);
			$scope.users = res;
		}).error(function(error) {
			$scope.message = error.message;
			$state.go('login');
		});
	};

	$scope.registerUser = function() {
		$http.post('register', $scope.user).success(function(res) {
			$scope.user = null;
			$scope.confirmPassword = null;
			$scope.register.$setPristine();
			$('#userModal').modal('hide');
			$rootScope.runSweetAlertMsg('Add New User','User Registration successful !','success');
		}).error(function(error) {
			$rootScope.runSweetAlertMsg('Add New User','User Registration failed!','error');
		});
	};

});
