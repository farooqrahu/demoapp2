'use strict';

angular.module('myApp').controller('MainCtrl', [ '$http','$window', '$timeout', '$scope', '$rootScope', 'UserService', function ($http,$window, $timeout, $scope, $rootScope, UserService) {
	$scope.windowResize='width: 100%';
	$scope.gridOptions1 = {
		paginationPageSizes: [25, 50, 75],
		paginationPageSize: 25,
		columnDefs: [
			{ name: 'name' },
			{ name: 'gender' },
			{ name: 'company' }
		]
	};

	$scope.gridOptions2 = {
		enableColumnResizing: true,
		enablePaginationControls: true,
		paginationPageSize: 25,
		columnDefs: [
			{ name: 'name' },
			{ name: 'gender' },
			{ name: 'company' }
		]
	};

	$scope.gridOptions2.onRegisterApi = function (gridApi) {
		$scope.gridApi2 = gridApi;
	};

	$http.get('/data/100.json')
		.then(function (response) {
			$scope.gridOptions1.data = response.data;
			$scope.gridOptions2.data = response.data;
		});



}]);

