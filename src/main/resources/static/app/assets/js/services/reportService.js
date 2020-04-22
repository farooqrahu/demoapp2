'use strict';

angular.module('myApp').factory('ReportService', ['$http', '$q','$window', function ($http, $q,$window) {
    var myContext = $window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));

    var factory = {
        findAllProducts:findAllProducts,
        addProduct:addProduct,
        deleteProduct:deleteProduct,
        editProduct:editProduct,
        findAllProductCategories:findAllProductCategories,
        findAllProductCompanies:findAllProductCompanies,
        getStockBranchWise:getStockBranchWise,
        addProductStock:addProductStock,
        saleProductStockToBranch:saleProductStockToBranch,
        findGLData:findGLData,
        getPurchaseData:getPurchaseData,
        getBranchSaleData:getBranchSaleData,
        getCustomerSaleData:getCustomerSaleData,
        getPurchaseProduct:getPurchaseProduct,
        addProductReturn:addProductReturn,
        getReturnedProducts:getReturnedProducts
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
    function findGLData(value) {
        return $http.get("findgldata",value)
            .then(function (data) {
                return data;
            })
            .catch(function (e) {
                return e;
            });
    }

    function getPurchaseData(value) {
        return $http.get("getpurchasedata",value)
            .then(function (data) {
                return data;
            })
            .catch(function (e) {
                return e;
            });
    }

    function getBranchSaleData(value) {
        return $http.get("getbranchsaledata",value)
            .then(function (data) {
                return data;
            })
            .catch(function (e) {
                return e;
            });
    }
    function getCustomerSaleData(value) {
        return $http.get("getcustomersaledata",value)
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

    function getPurchaseProduct(value) {
        return $http.get("getpurchaseproduct",value)
            .then(function (data) {
                return data;
            })
            .catch(function (e) {
                return e;
            });
    }


    function addProductReturn(value) {
        return $http.post("addproductreturn",value)
            .then(function (data) {
                return data;
            })
            .catch(function (e) {
                return e;
            });
    }

    function getReturnedProducts(value) {
        return $http.post("getreturnedproducts",value)
            .then(function (data) {
                return data;
            })
            .catch(function (e) {
                return e;
            });
    }


}]);
