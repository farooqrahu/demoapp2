'use strict';

angular.module('myApp').factory('UserService', ['$http', '$q','$window', function ($http, $q,$window) {
    var myContext = $window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));

    var factory = {
        findAllUsers:findAllUsers,
        registerUser:registerUser,
        deleteUser:deleteUser,
        editUser:editUser

    };
    return factory;


    function findAllUsers(value) {
        return $http.get("api/users",value)
            .then(function (data) {
                return data;
            })
            .catch(function (e) {
                return e;
            });
    }

    function registerUser(value) {
        return $http.post("api/register",value)
            .then(function (data) {
                return data;
            })
            .catch(function (e) {
                return e;
            });
    }


    function deleteUser(value) {
        return $http.post("api/deleteUser",value)
            .then(function (data) {
                return data;
            })
            .catch(function (e) {
                return e;
            });
    }
    function editUser(value) {
        return $http.post("api/editUser",value)
            .then(function (data) {
                return data;
            })
            .catch(function (e) {
                return e;
            });
    }



}]);
