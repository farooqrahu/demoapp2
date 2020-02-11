'use strict';

angular.module('myApp').factory('CustomerService', ['$http', '$q','$window', function ($http, $q,$window) {
    var myContext = $window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));

    var factory = {
        findAllCustomers:findAllCustomers,
        addCustomer:addCustomer,
        deleteCustomer:deleteCustomer,
        editCustomer:editCustomer,
        findAllCustomerCategories:findAllCustomerCategories,
        findAllCustomerCompanies:findAllCustomerCompanies,
        getStockBranchWise:getStockBranchWise,
        addCustomerStock:addCustomerStock
    };
    return factory;


    function findAllCustomers(value) {
        return $http.get("customers",value)
            .then(function (data) {
                return data;
            })
            .catch(function (e) {
                return e;
            });
    }
    function addCustomerStock(value) {
        return $http.post("product/addstock",value)
            .then(function (data) {
                return data;
            })
            .catch(function (e) {
                return e;
            });
    }

    function findAllCustomerCategories(value) {
        return $http.get("api/productcategories",value)
            .then(function (data) {
                return data;
            })
            .catch(function (e) {
                return e;
            });
    }
    function findAllCustomerCompanies(value) {
        return $http.get("api/productcompanies",value)
            .then(function (data) {
                return data;
            })
            .catch(function (e) {
                return e;
            });
    }

    function addCustomer(value) {
        return $http.post("/product/add",value)
            .then(function (data) {
                return data;
            })
            .catch(function (e) {
                return e;
            });
    }


    function deleteCustomer(value) {
        return $http.post("/product/deleteCustomer",value)
            .then(function (data) {
                return data;
            })
            .catch(function (e) {
                return e;
            });
    }
    function editCustomer(value) {
        return $http.post("product/update",value)
            .then(function (data) {
                return data;
            })
            .catch(function (e) {
                return e;
            });
    }

    function getStockBranchWise(value) {
        return $http.post("product/findstock",value)
            .then(function (data) {
                return data;
            })
            .catch(function (e) {
                return e;
            });
    }

}]);
