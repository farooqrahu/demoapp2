'use strict';

angular.module('myApp').factory('ProductCatService', ['$http', '$q','$window', function ($http, $q,$window) {
    var myContext = $window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));

    var factory = {
        findAllProducts:findAllProducts,
        addProductCat:addProductCat,
        deleteProduct:deleteProduct,
        editProduct:editProduct,
        findAllProductCategories:findAllProductCategories,
        findAllProductCompanies:findAllProductCompanies,
        getStockBranchWise:getStockBranchWise,
        addProductStock:addProductStock,
        saleProductStockToBranch:saleProductStockToBranch
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

    function saleProductStockToBranch(value) {
        return $http.post("product/salestocktobranch",value)
            .then(function (data) {
                return data;
            })
            .catch(function (e) {
                return e;
            });
    }


    function findAllProductCategories(value) {
        return $http.get("api/cat/productcategories",value)
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

    function addProductCat(value) {
        return $http.post("/product/cat/add",value)
            .then(function (data) {
                return data;
            })
            .catch(function (e) {
                return e;
            });
    }


    function deleteProduct(value) {
        return $http.post("/product/cat/deleteProduct",value)
            .then(function (data) {
                return data;
            })
            .catch(function (e) {
                return e;
            });
    }
    function editProduct(value) {
        return $http.post("product/cat/update",value)
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
