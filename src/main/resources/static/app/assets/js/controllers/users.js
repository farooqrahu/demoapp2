'use strict';

angular.module('myApp').controller('UsersController', ['$window', '$timeout', '$scope', '$rootScope', 'UserService', 'AuthService', function ($window, $timeout, $scope, $rootScope, UserService, AuthService) {

    $scope.registerUser = function () {
        UserService.registerUser($scope.user).then(function (result) {
            if (result.status =="201") { // check if we get the data back
                $scope.confirmPassword = null;
                $scope.user = null;
                $rootScope.runSweetAlertMsg('Registration', 'User Registration successful !', 'success');
            } else {
                $rootScope.runSweetAlertMsg('Registration', result.data.message, 'error');
            }
        });
    };

}]);

