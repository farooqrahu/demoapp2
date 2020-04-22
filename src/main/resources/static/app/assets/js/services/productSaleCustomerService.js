'use strict';

angular.module('myApp').factory('ProductSaleCusService', ['$http', '$q','$window', function ($http, $q,$window) {
    var myContext = $window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));

    var factory = {
        findAllProducts:findAllProducts,
        addProduct:addProduct,
        deleteProduct:deleteProduct,
        editProduct:editProduct,
        findAllProductCompanies:findAllProductCompanies,
        getStockBranchWise:getStockBranchWise,
        addProductStock:addProductStock,
        saleProductSaleToCustomer:saleProductSaleToCustomer
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
    function addProductStock(value) {
        return $http.post("product/addstock",value)
            .then(function (data) {
                return data;
            })
            .catch(function (e) {
                return e;
            });
    }

    function saleProductSaleToCustomer(value) {
        return $http.post("product/salestocktocustomer",value)
            .then(function (data) {
                return data;
            })
            .catch(function (e) {
                return e;
            });
    }


    function findAllProductCategories(value) {
        return $http.get("api/productcategories",value)
            .then(function (data) {
                return data;
            })
            .catch(function (e) {
                return e;
            });
    }
    function findAllProductCompanies(value) {
        return $http.get("api/productcompanies",value)
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


    function deleteProduct(value) {
        return $http.post("/product/deleteProduct",value)
            .then(function (data) {
                return data;
            })
            .catch(function (e) {
                return e;
            });
    }
    function editProduct(value) {
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
