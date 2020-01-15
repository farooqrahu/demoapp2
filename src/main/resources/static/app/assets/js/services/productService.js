'use strict';

angular.module('myApp').factory('ProductService', ['$http', '$q','$window', function ($http, $q,$window) {
    var myContext = $window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));

    var factory = {
        findAllProducts:findAllProducts,
        addProduct:addProduct,
        deleteUser:deleteUser,
        editUser:editUser,
        findAllProductCategory:findAllProductCategory

    };
    return factory;


    function findAllProducts(value) {
        return $http.get("products",value)
            .then(function (data) {
                return data;
            })
            .catch(function (e) {
                return e;
            });
    }
    function findAllProductCategory(value) {
        return $http.get("api/productcategories",value)
            .then(function (data) {
                return data;
            })
            .catch(function (e) {
                return e;
            });
    }


    function addProduct(value) {
        return $http.post("/product/add",value)
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
